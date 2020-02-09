package model.dao;

import java.util.List;

/**
 * The interface Entity dao.
 *
 * @param <T> the type parameter
 */
public interface EntityDao<T> {

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    T getById(long id);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<T> getAll();

    /**
     * Create boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    boolean create(T entity);

    /**
     * Update boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    boolean update(T entity);

    /**
     * Remove boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    boolean remove(T entity);

}
