package com.gmail.trair8.dao;

import com.gmail.trair8.entity.Entity;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDAO<T extends Entity> {

    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> findAll();

    public abstract T findEntityById(int id);

    public abstract void insert(T entity);

    public abstract void update(int id, T entity);
}
