package model.service;

import model.domain.entity.Activity;
import model.domain.entity.User;

import java.util.List;

/**
 * The interface Activity service.
 */
public interface ActivityService extends Service {
    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    Activity getById(long id);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<Activity> getAll();

    /**
     * Create boolean.
     *
     * @param activity the activity
     * @param userId   the user id
     * @return the boolean
     */
    boolean create(Activity activity, long userId);

    /**
     * Gets active activity by user.
     *
     * @param userId the user id
     * @return the active activity by user
     */
    List<Activity> getActiveActivityByUser(long userId);

    /**
     * Gets active activity by user.
     *
     * @param userId      the user id
     * @param currentPage the current page
     * @return the active activity by user
     */
    List<Activity> getActiveActivityByUser(long userId, String currentPage);

    /**
     * Gets all activity by user.
     *
     * @param userId      the user id
     * @param currentPage the current page
     * @return the all activity by user
     */
    List<Activity> getAllActivityByUser(long userId, String currentPage);

    /**
     * Gets all activity by user.
     *
     * @param userId the user id
     * @return the all activity by user
     */
    List<Activity> getAllActivityByUser(long userId);

    /**
     * Create and return activity.
     *
     * @param activity the activity
     * @param userId   the user id
     * @return the activity
     */
    Activity createAndReturn(Activity activity, long userId);

    /**
     * Update boolean.
     *
     * @param activity the activity
     * @return the boolean
     */
    boolean update(Activity activity);

    /**
     * Update and return activity.
     *
     * @param activity the activity
     * @return the activity
     */
    Activity updateAndReturn(Activity activity);

    /**
     * Delete boolean.
     *
     * @param activity the activity
     * @return the boolean
     */
    boolean delete(Activity activity);

    /**
     * Add user to activity boolean.
     *
     * @param activity the activity
     * @param user     the user
     * @return the boolean
     */
    boolean addUserToActivity(Activity activity, User user);

    /**
     * Delete user from activity boolean.
     *
     * @param activity the activity
     * @param user     the user
     * @return the boolean
     */
    boolean deleteUserFromActivity(Activity activity, User user);

    /**
     * Gets user activities pages.
     *
     * @param userId the user id
     * @return the user activities pages
     */
    int getUserActivitiesPages(long userId);

    /**
     * Gets active activities pages.
     *
     * @param userId the user id
     * @return the active activities pages
     */
    int getActiveActivitiesPages(long userId);

}
