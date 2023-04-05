package org.youngmonkeys.dyc.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.youngmonkeys.dyc.model.Continuation;
import org.youngmonkeys.dyc.model.PageToken;
import org.youngmonkeys.dyc.model.PaginationModel;
import org.youngmonkeys.dyc.pagination.PaginationParameter;

import com.tvd12.ezyfox.io.EzyLists;
import com.tvd12.ezyfox.security.EzyBase64;
import com.tvd12.ezyfox.util.EzyEntry;

/**
 * For pagination business.
 *
 * @param <T> the item type
 * @param <F> the filter value type
 * @param <P> the pagination value type (inclusive or exclusive value type)
 */
public abstract class PaginationService<T, F, P> {

    public final PaginationModel<T> getFirstPage(int limit) {
        return getFirstPage(null, limit);
    }

    public final PaginationModel<T> getFirstPage(
        F filter,
        int limit
    ) {
        return getNextPage(filter, null, limit);
    }

    public final PaginationModel<T> getNextPage(
        String pageToken,
        int limit
    ) {
        return getNextPage(null, pageToken, limit);
    }

    public final PaginationModel<T> getNextPage(
        F filter,
        String pageToken,
        int limit
    ) {
        P paginationParameter = doDeserializePageToken(pageToken);
        int limitPlusOne = limit + 1;
        Map<String, Object> valueMap = Stream
            .of(
                EzyEntry.<String, Supplier<List<T>>>of (
                    "listPlusOne",
                    () -> getNextItems(filter, paginationParameter, limitPlusOne)
                ),
                EzyEntry.<String, Supplier<Long>>of (
                    "total",
                    () -> getTotalItems(filter)
                )
            )
            .parallel()
            .collect(
                Collectors.toMap(
                    EzyEntry::getKey,
                    entry -> entry.getValue().get()
                )
            );
        return toNextPageModel(valueMap, paginationParameter, limit);
    }

    public final PaginationModel<T> getLastPage(int limit) {
        return getLastPage(null, limit);
    }

    public final PaginationModel<T> getLastPage(
        F filter,
        int limit
    ) {
        return getPreviousPage(filter, null, limit);
    }

    public final PaginationModel<T> getPreviousPage(
        String pageToken,
        int limit
    ) {
        return getPreviousPage(null, pageToken, limit);
    }

    public final PaginationModel<T> getPreviousPage(
        F filter,
        String pageToken,
        int limit
    ) {
        P paginationParameter = doDeserializePageToken(pageToken);
        int limitPlusOne = limit + 1;
        Map<String, Object> valueMap = Stream
            .of(
                EzyEntry.<String, Supplier<List<T>>>of (
                    "listPlusOne",
                    () -> getPreviousItems(filter, paginationParameter, limitPlusOne)
                ),
                EzyEntry.<String, Supplier<Long>>of (
                    "total",
                    () -> getTotalItems(filter)
                )
            )
            .parallel()
            .collect(
                Collectors.toMap(
                    EzyEntry::getKey,
                    entry -> entry.getValue().get()
                )
            );
        return toPreviousPageModel(valueMap, paginationParameter, limit);
    }

    @SuppressWarnings("unchecked")
    private PaginationModel<T> toNextPageModel(
        Map<String, Object> map,
        P paginationParameter,
        int limit
    ) {
        List<T> listPlusOne = (List<T>) map.get("listPlusOne");
        List<T> list = EzyLists.take(listPlusOne, limit);
        boolean hasNext = listPlusOne.size() > limit;
        boolean hasPrev = !isEmptyPaginationParameter(
            paginationParameter
        ) && list.size() > 0;
        T nextPageTokenItem = hasNext ? EzyLists.last(list) : null;
        T lastPageTokenItem =  hasPrev ? EzyLists.first(list) : null;
        return PaginationModel
            .<T>builder()
            .items(list)
            .count(list.size())
            .total((Long) map.get("total"))
            .pageToken(
                PageToken
                    .builder()
                    .next(doSerializeToPageToken(paginationParameter, nextPageTokenItem))
                    .prev(doSerializeToPageToken(paginationParameter, lastPageTokenItem))
                    .build()
            )
            .continuation(
                Continuation
                    .builder()
                    .hasNext(hasNext)
                    .hasPrevious(hasPrev)
                    .build()
            )
            .build();
    }

    @SuppressWarnings("unchecked")
    private PaginationModel<T> toPreviousPageModel(
        Map<String, Object> map,
        P paginationParameter,
        int limit
    ) {
        List<T> listPlusOne = (List<T>) map.get("listPlusOne");
        List<T> list = EzyLists.take(listPlusOne, limit);
        boolean hasNext = paginationParameter != null && list.size() > 0;
        boolean hasPrev = listPlusOne.size() > limit;
        T nextPageTokenItem = hasNext ? EzyLists.first(list) : null;
        T lastPageTokenItem =  hasPrev ? EzyLists.last(list) : null;
        Collections.reverse(list);
        return PaginationModel
            .<T>builder()
            .items(list)
            .count(list.size())
            .total((Long) map.get("total"))
            .pageToken(
                PageToken.builder()
                    .next(doSerializeToPageToken(paginationParameter, nextPageTokenItem))
                    .prev(doSerializeToPageToken(paginationParameter, lastPageTokenItem))
                    .build()
            )
            .continuation(
                Continuation.builder()
                    .hasNext(hasNext)
                    .hasPrevious(hasPrev)
                    .build()
            )
            .build();
    }

    private List<T> getNextItems(
        F filter,
        P paginationParameter,
        int limit
    ) {
        return paginationParameter == null
               ? getFirstItems(filter, limit)
               : getNextItemsExclusive(filter, paginationParameter, limit);
    }

    protected abstract List<T> getFirstItems(F filter, int limit);

    protected abstract List<T> getNextItemsExclusive(
        F filter,
        P paginationParameter,
        int limit
    );

    protected abstract List<T> getPreviousItemsExclusive(
        F filter,
        P paginationParameter,
        int limit
    );

    private List<T> getPreviousItems(
        F filter,
        P paginationParameter,
        int limit
    ) {
        return paginationParameter == null
               ? getLastItems(filter, limit)
               : getPreviousItemsExclusive(filter, paginationParameter, limit);
    }

    protected abstract List<T> getLastItems(F filter, int limit);

    protected abstract long getTotalItems(F filter);

    private String doSerializeToPageToken(
        P paginationParameter,
        T value
    ) {
        return value == null
               ? null
               : EzyBase64.encodeUtf(serializeToPageToken(paginationParameter, value));
    }

    protected String serializeToPageToken(
        P paginationParameter,
        T value
    ) {
        return serializeToPageToken(value);
    }

    protected String serializeToPageToken(T value) {
        return null;
    }

    private P doDeserializePageToken(String pageToken) {
        return pageToken == null
               ? null
               : deserializePageToken(EzyBase64.decodeUtf(pageToken));
    }

    protected abstract P deserializePageToken(String pageToken);

    protected boolean isEmptyPaginationParameter(
        Object paginationParameter
    ) {
        if (paginationParameter == null) {
            return true;
        }
        if (paginationParameter instanceof PaginationParameter) {
            return ((PaginationParameter) paginationParameter)
                .isEmpty();
        }
        return false;
    }
}
