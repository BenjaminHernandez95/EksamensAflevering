package org.example.daos;

import java.util.List;

public interface IDAO <T> {
    T create(T dto);
    List<T> getAll();
    T getById(Integer id);
    T update(T dto);
    void delete(Integer id);
}