package com.emi.model;

import java.util.List;

public interface Dao<T> {
    void save(T t);
    T getById(int id);
    List<T> getAll();
    boolean updateById(int id, T t);
    boolean deleteById(int id);

}
