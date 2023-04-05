/*
 * Copyright 2022 youngmonkeys.org
 *
 * Licensed under the ezyplatform, Version 1.0.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://youngmonkeys.org/licenses/ezyplatform-1.0.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.youngmonkeys.dyc.service;

import static com.tvd12.ezyfox.io.EzyLists.newArrayList;

import java.util.List;

import org.youngmonkeys.dyc.repo.PaginationResultRepository;

import lombok.AllArgsConstructor;

/**
 * For pagination business.
 *
 * @param <T> the output type
 * @param <F> the filter value type
 * @param <P> the pagination value type (inclusive or exclusive value type)
 * @param <I> the id type
 * @param <E> the entity type
 * @param <R> the query result type
 */
@AllArgsConstructor
public abstract class DefaultPaginationResultService<T, F, P, I, E, R>
    extends PaginationService<T, F, P> {

    protected final PaginationResultRepository<F, P, I, E, R> repository;

    @Override
    protected List<T> getFirstItems(F filter, int limit) {
        return convertEntities(
            repository.findFirstElements(filter, limit)
        );
    }

    @Override
    protected List<T> getNextItemsExclusive(
        F filter,
        P paginationParameter,
        int limit
    ) {
        return convertEntities(
            repository.findNextElements(filter, paginationParameter, limit)
        );
    }

    @Override
    protected List<T> getLastItems(F filter, int limit) {
        return convertEntities(
            repository.findLastElements(filter, limit)
        );
    }

    @Override
    protected List<T> getPreviousItemsExclusive(
        F filter,
        P paginationParameter,
        int limit
    ) {
        return convertEntities(
            repository.findPreviousElements(filter, paginationParameter, limit)
        );
    }

    @Override
    protected long getTotalItems(F filter) {
        return repository.countElements(filter);
    }

    protected List<T> convertEntities(List<R> entities) {
        return newArrayList(entities, this::convertEntity);
    }

    @SuppressWarnings("unchecked")
    protected T convertEntity(R entity) {
        return (T) entity;
    }
}
