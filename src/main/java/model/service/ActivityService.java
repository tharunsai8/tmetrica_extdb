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
     * Gets activity list.
     *
     * @param currentPage the current page
     * @param userEmail   the user email
     * @param postOnPage  the post on page
     * @return the activity list
     */
    List<Activity> getActivityList(String currentPage, String userEmail, int postOnPage);

    /**
     * Gets available activity list.
     *
     * @param currentPage the current page
     * @param userEmail   the user email
     * @param postOnPage  the post on page
     * @return the available activity list
     */
    List<Activity> getAvailableActivityList(String currentPage, String userEmail, int postOnPage);

    /**
     * Gets page count.
     *
     * @param userEmail  the user email
     * @param postOnPage the post on page
     * @return the page count
     */
    int getPageCount(String userEmail, int postOnPage);
}
