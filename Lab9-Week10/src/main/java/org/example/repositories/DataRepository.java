package org.example.repositories;

import java.util.Optional;

public interface DataRepository <T, IdType> {
    void create (T entity);
    void delete (T entity);
    Optional<T> findById (Class<T> classEntity, IdType id);
}
