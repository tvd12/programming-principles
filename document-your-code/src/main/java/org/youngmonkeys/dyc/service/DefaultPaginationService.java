package org.youngmonkeys.dyc.service;

import org.youngmonkeys.dyc.repo.PaginationRepository;

/**
 * For pagination business.
 *
 * @param <T> the output type
 * @param <F> the filter value type
 * @param <P> the pagination value type (inclusive or exclusive value type)
 * @param <I> the id type
 * @param <E> the entity type
 */
public abstract class DefaultPaginationService<T, F, P, I, E>
    extends DefaultPaginationResultService<T, F, P, I, E, E> {

    public DefaultPaginationService(
        PaginationRepository<F, P, I, E> repository
    ) {
        super(repository);
    }
}
