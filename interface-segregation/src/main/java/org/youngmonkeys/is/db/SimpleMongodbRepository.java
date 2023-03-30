package org.youngmonkeys.is.db;

import java.util.Collection;
import java.util.List;

public class SimpleMongodbRepository<I, E> implements MongodbRepository<I, E> {
    @Override
    public void save(E entity) {}

    @Override
    public void save(Collection<E> entities) {}

    @Override
    public E findById(I id) {
        return null;
    }

    @Override
    public void deleteById(I id) {}

    @Override
    public long increment(String field, long value) {
        return 0;
    }

    @Override
    public <R> List<R> aggregateListWithQuery(String query, Class<R> result) {
        return null;
    }
}
