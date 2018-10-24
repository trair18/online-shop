package com.gmail.trair8.dao;

import com.gmail.trair8.entity.Entity;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDAO<T extends Entity> {

    public AbstractDAO() {
    }

    public abstract List<T> findAll(Connection connection);

    public abstract T findEntityById(Connection connection, int id);

    public abstract void insert(Connection connection, T entity);

    public abstract void update(Connection connection, int id, T entity);

}
