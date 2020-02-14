package model.service;

import model.domain.entity.User;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService extends Service {
    /**
     * Gets all.
     *
     * @return the all
     */
    List<User> getAll();

    /**
     * Update boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean update(User user);

    /**
     * Delete boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean delete(User user);

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    User getById(long id);

    /**
     * Register boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean register(User user);

    /**
     * Is exist boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean isExist(User user);

    List<User> getAllByActivity(long activityId);

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     */
    User getByEmail(String email);

    /**
     * Validate user user.
     *
     * @param email    the email
     * @param password the password
     * @return the user
     */
    User validateUser(String email, String password);
}
