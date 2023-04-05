package org.youngmonkeys.dyc.repo;

import com.tvd12.ezydata.database.query.EzyQueryData;
import com.tvd12.ezydata.database.query.EzyQueryMethodType;
import com.tvd12.ezydata.jpa.repository.EzyJpaRepository;
import com.tvd12.ezyfox.reflect.EzyGenerics;

import java.util.*;

import static com.tvd12.ezyfox.io.EzyCollections.isEmpty;
import static com.tvd12.ezyfox.io.EzyStrings.EMPTY_STRING;
import static com.tvd12.ezyfox.io.EzyStrings.isNotBlank;

import org.youngmonkeys.dyc.pagination.PaginationParameter;
import org.youngmonkeys.dyc.pagination.StorageFilter;

/**
 * For pagination business.
 *
 * @param <F> the filter value type
 * @param <P> the pagination value type (inclusive or exclusive value type)
 * @param <I> the id type
 * @param <E> the entity type
 * @param <R> the query result type
 */
public class PaginationResultRepository<F, P, I, E, R> extends EzyJpaRepository<I, E> {

    protected final Class<R> resultType = getResultType();

    public final List<R> findFirstElements(
        F filter,
        int limit
    ) {
        return findNextElements(
            filter,
            null,
            limit
        );
    }

    public final List<R> findNextElements(
        F filter,
        P paginationParameter,
        int limit
    ) {
        return findElements(
            filter,
            paginationParameter,
            limit,
            true
        );
    }

    public final List<R> findLastElements(
        F filter,
        int limit
    ) {
        return findPreviousElements(
            filter,
            null,
            limit
        );
    }

    public final List<R> findPreviousElements(
        F filter,
        P paginationParameter,
        int limit
    ) {
        return findElements(
            filter,
            paginationParameter,
            limit,
            false
        );
    }

    public long countElements(F filter) {
        EzyQueryData queryData = makeQuery(
            EzyQueryMethodType.COUNT,
            filter,
            null,
            true
        );
        return countByQueryString(
            queryData.getQuery(),
            queryData.getParameterMap()
        );
    }

    @SuppressWarnings("unchecked")
    private List<R> findElements(
        F filter,
        P paginationParameter,
        int limit,
        boolean nextPage
    ) {
        EzyQueryData query = makeQuery(
            EzyQueryMethodType.FIND,
            filter,
            paginationParameter,
            nextPage
        );
        return resultType != entityType
            ? fetchListByQueryString(
                query.getQuery(),
                query.getParameterMap(),
                resultType,
                0,
                limit
            )
            : (List<R>) findListByQueryString(
                query.getQuery(),
                query.getParameterMap(),
                0,
                limit
            );
    }

    @SuppressWarnings("MethodLength")
    private EzyQueryData makeQuery(
        EzyQueryMethodType methodType,
        F filter,
        P paginationParameter,
        boolean nextPage
    ) {
        StringBuilder queryString = new StringBuilder()
            .append("SELECT");
        boolean distinct = isDistinct();
        if (methodType == EzyQueryMethodType.COUNT) {
            queryString.append(" COUNT(");
            if (distinct) {
                queryString.append(" DISTINCT ");
            }
            queryString.append(getCountField()).append(")");
        } else {
            if (distinct) {
                queryString.append(" DISTINCT");
            }
            List<String> selectionFields = getSelectionFields();
            if (isEmpty(selectionFields)) {
                queryString.append(" e");
            } else {
                queryString
                    .append(" ")
                    .append(String.join(",", selectionFields));
            }
        }
        queryString.append(" FROM ")
                   .append(getFromName())
                   .append(" e");
        decorateQueryStringBeforeWhere(
            queryString,
            filter,
            paginationParameter
        );
        String matchingCondition = makeMatchingCondition(filter);
        String paginationCondition =
            (methodType == EzyQueryMethodType.COUNT || paginationParameter == null)
            ? EMPTY_STRING
            : makePaginationCondition(paginationParameter, nextPage);
        boolean matchingConditionNotBlank = isNotBlank(matchingCondition);
        boolean paginationConditionNotBlank = isNotBlank(paginationCondition);
        if (matchingConditionNotBlank || paginationConditionNotBlank) {
            queryString.append(" WHERE ");
            if (matchingConditionNotBlank) {
                queryString.append(matchingCondition);
            }
            if (matchingConditionNotBlank && paginationConditionNotBlank) {
                queryString.append(" AND ");
            }
            if (paginationConditionNotBlank) {
                queryString
                    .append("(")
                    .append(paginationCondition)
                    .append(")");
            }
        }
        String groupBy = makeGroupBy(filter, paginationParameter);
        if (isNotBlank(groupBy)) {
            queryString.append(" GROUP BY ").append(groupBy);
        }
        if (methodType == EzyQueryMethodType.FIND) {
            String orderBy = makeOrderBy(filter, paginationParameter, nextPage);
            if (isNotBlank(orderBy)) {
                queryString.append(" ORDER BY ").append(orderBy);
            }
        }
        return EzyQueryData
            .builder()
            .parameters(getQueryParameters(methodType, filter, paginationParameter))
            .query(queryString.toString())
            .build();
    }

    protected String getFromName() {
        return entityType.getSimpleName();
    }

    protected String getCountField() {
        return "e";
    }

    protected List<String> getSelectionFields() {
        return Collections.emptyList();
    }

    protected boolean isDistinct() {
        return false;
    }

    protected void decorateQueryStringBeforeWhere(
        StringBuilder queryString,
        F filter,
        P paginationParameter
    ) {
        decorateQueryStringBeforeWhere(queryString, filter);
    }

    protected void decorateQueryStringBeforeWhere(
        StringBuilder queryString,
        F filter
    ) {
        decorateQueryStringBeforeWhere(queryString);
    }

    protected void decorateQueryStringBeforeWhere(StringBuilder queryString) {}

    protected String makeMatchingCondition() {
        return EMPTY_STRING;
    }

    protected String makeMatchingCondition(F filter) {
        return makeMatchingCondition();
    }

    protected String makePaginationCondition(boolean nextPage) {
        return EMPTY_STRING;
    }

    protected String makePaginationCondition(P paginationParameter, boolean nextPage) {
        return makePaginationCondition(nextPage);
    }

    protected String makeGroupBy(F filter, P paginationParameter) {
        return makeGroupBy(filter);
    }

    protected String makeGroupBy(F filter) {
        return makeGroupBy();
    }

    protected String makeGroupBy() {
        return EMPTY_STRING;
    }

    protected String makeOrderBy(F filter, P paginationParameter, boolean nextPage) {
        return makeOrderBy(paginationParameter, nextPage);
    }

    protected String makeOrderBy(P paginationParameter, boolean nextPage) {
        return makeOrderBy(nextPage);
    }

    protected String makeOrderBy(boolean nextPage) {
        return EMPTY_STRING;
    }

    private Map<String, Object> getQueryParameters(
        EzyQueryMethodType methodType,
        F filter,
        P paginationParameter
    ) {
        Map<String, Object> parameters = new HashMap<>(getFilterQueryParameters(filter));
        if (methodType != EzyQueryMethodType.COUNT) {
            parameters.putAll(getPaginationQueryParameters(paginationParameter));
        }
        return parameters;
    }

    protected Map<String, Object> getFilterQueryParameters(F filter) {
        if (filter instanceof StorageFilter) {
            return ((StorageFilter) filter).getParameters();
        }
        return filter == null
               ? Collections.emptyMap()
               : Collections.singletonMap("filter", filter);
    }

    protected Map<String, Object> getPaginationQueryParameters(P paginationParameter) {
        if (paginationParameter instanceof PaginationParameter) {
            return ((PaginationParameter) paginationParameter).getParameters();
        }
        return paginationParameter == null
               ? Collections.emptyMap()
               : Collections.singletonMap("parameter", paginationParameter);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<E> getEntityType() {
        return EzyGenerics.getGenericClassArguments(
            getClass().getGenericSuperclass(),
            5
        )[3];
    }

    @SuppressWarnings("unchecked")
    protected Class<R> getResultType() {
        return EzyGenerics.getGenericClassArguments(
            getClass().getGenericSuperclass(),
            5
        )[4];
    }
}