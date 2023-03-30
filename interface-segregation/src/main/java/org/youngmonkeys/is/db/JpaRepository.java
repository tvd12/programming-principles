package org.youngmonkeys.is.db;

import java.util.Collection;

public class JpaRepository<I, E> implements DatabaseRepository<I, E> {
    @Override
    public void save(E entity) {}

    @Override
    public void save(Collection<E> entities) {}

    @Override
    public E findById(I id) {
        return null;
    }

    @Override
    public void deleteById(I id) {
    }
}
