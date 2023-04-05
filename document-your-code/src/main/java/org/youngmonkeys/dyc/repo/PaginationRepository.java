package org.youngmonkeys.dyc.repo;

import com.tvd12.ezyfox.reflect.EzyGenerics;

/**
 * For pagination business.
 *
 * @param <F> the filter value type
 * @param <P> the pagination value type (inclusive or exclusive value type)
 * @param <I> the id type
 * @param <E> the entity type
 */
public class PaginationRepository<F, P, I, E>
    extends PaginationResultRepository<F, P, I, E, E> {

    @Override
    protected Class<E> getResultType() {
        return getEntityType();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<E> getEntityType() {
        return EzyGenerics.getGenericClassArguments(
            getClass().getGenericSuperclass(),
            4
        )[3];
    }
}
