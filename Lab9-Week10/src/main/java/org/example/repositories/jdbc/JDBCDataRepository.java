package org.example.repositories.jdbc;

import org.example.entities.AbstractEntity;
import org.example.repositories.DataRepository;

import java.io.Serializable;
import java.util.Optional;

public abstract class JDBCDataRepository
        <T extends AbstractEntity, IdType extends Serializable>
            implements DataRepository<T, IdType>{

    public abstract void create(T entity);

    public abstract void delete(T entity);
    public abstract Optional<T> findById(Class<T> classEntity, IdType id);


}
