package model.service;

import model.domain.entity.ActivityLog;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Activity log service.
 */
public interface ActivityLogService extends Service {
    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    ActivityLog getById(long id);

    /**
     * Create boolean.
     *
     * @param log the log
     * @return the boolean
     */
    boolean create(ActivityLog log);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<ActivityLog> getAll();

    /**
     * Gets all by user.
     *
     * @param userId the user id
     * @return the all by user
     */
    List<ActivityLog> getAllByUser(long userId);

    /**
     * Gets all by user.
     *
     * @param userId      the user id
     * @param currentPage the current page
     * @return the all by user
     */
    List<ActivityLog> getAllByUser(long userId, String currentPage);

    /**
     * Gets all by user and activity.
     *
     * @param userId     the user id
     * @param activityId the activity id
     * @return the all by user and activity
     */
    List<ActivityLog> getAllByUserAndActivity(long userId, long activityId);

    /**
     * Gets all by user and activity.
     *
     * @param userId      the user id
     * @param activityId  the activity id
     * @param currentPage the current page
     * @return the all by user and activity
     */
    List<ActivityLog> getAllByUserAndActivity(long userId, long activityId, String currentPage);

    /**
     * Create boolean.
     *
     * @param startDate  the start date
     * @param endDate    the end date
     * @param email      the email
     * @param activityId the activity id
     * @return the boolean
     * @throws ParseException the parse exception
     */
    boolean create(String startDate, String endDate, String email, long activityId) throws ParseException;

    /**
     * Update boolean.
     *
     * @param log the log
     * @return the boolean
     */
    boolean update(ActivityLog log);

    /**
     * Update boolean.
     *
     * @param id         the id
     * @param startDate  the start date
     * @param endDate    the end date
     * @param email      the email
     * @param activityId the activity id
     * @return the boolean
     * @throws ParseException the parse exception
     */
    boolean update(long id, String startDate, String endDate, String email, long activityId) throws ParseException;

    /**
     * Delete boolean.
     *
     * @param logId the log id
     * @return the boolean
     */
    boolean delete(long logId);

    /**
     * Gets all by user pages.
     *
     * @param userId the user id
     * @return the all by user pages
     */
    int getAllByUserPages(long userId);

    /**
     * Gets all by user and activity pages.
     *
     * @param userId     the user id
     * @param actovotyId the actovoty id
     * @return the all by user and activity pages
     */
    int getAllByUserAndActivityPages(long userId, long actovotyId);
}
