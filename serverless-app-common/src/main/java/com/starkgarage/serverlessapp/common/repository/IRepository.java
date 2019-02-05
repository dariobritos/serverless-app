package com.starkgarage.serverlessapp.common.repository;

import java.util.Collection;

public interface IRepository<T> {
    T add(T item);

    Collection<T> getAll();

    Collection<T> getAllUserCreatedRecords(String var1);

    void remove(T var1);

    T update(T item);

    T findById(String id);

    Collection<T> query(QueryCallback<T> var1);
}