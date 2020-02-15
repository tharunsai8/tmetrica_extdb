package model.dao;

import java.util.List;

public interface EntityDao<T> {

    T getById(long id);

    List<T> getAll();

    boolean create(T entity);

    boolean update(T entity);

    boolean remove(T entity);

}
