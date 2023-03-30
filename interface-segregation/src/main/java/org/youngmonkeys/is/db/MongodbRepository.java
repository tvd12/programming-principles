package org.youngmonkeys.is.db;

import java.util.List;

public interface MongodbRepository<I, E> extends DatabaseRepository<I, E> {

    long increment(String field, long value);

    <R> List<R> aggregateListWithQuery(
        String query,
        Class<R> result
    );
}
