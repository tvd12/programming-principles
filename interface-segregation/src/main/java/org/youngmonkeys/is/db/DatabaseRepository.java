package org.youngmonkeys.is.db;

import java.util.Collection;

public interface DatabaseRepository<I, E> {

    void save(E entity);

    void save(Collection<E> entities);

    E findById(I id);

    void deleteById(I id);
}
