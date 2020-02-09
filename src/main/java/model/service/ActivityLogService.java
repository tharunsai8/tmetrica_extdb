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
}
