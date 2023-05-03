package org.example.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO <T> {
    Optional<T> findById (int id) throws SQLException;
    //Optional<List<T>> findAll() throws SQLException;
    void create(T t) throws SQLException ;
    //void update(T t) throws SQLException;
    //void delete(T t) throws SQLException;
}
