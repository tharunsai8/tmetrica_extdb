package model.dao.impl;

import model.dao.AbstractJDBCDao;
import model.dao.EntityMapper;
import model.domain.entity.Activity;
import model.domain.entity.User;
import model.domain.enums.ActivityStatus;

import java.util.List;
import java.util.ResourceBundle;

/**
 * The type Activity dao.
 */
public class ActivityDao extends AbstractJDBCDao<Activity> {
    private final String ID = "id";
    private final String NAME = "name";
    private final String OPENING_TIME = "opening_time";
    private final String CLOSING_TIME = "closing_time";
    private final String STATUS = "status";
    private static ResourceBundle bundle = ResourceBundle.getBundle("database/queries");


    @Override
    public Activity getById(long id) {
        return getById(bundle.getString("activity.get.id"),
                ps -> ps.setLong(1, id),
                getMapper());
    }

    @Override
    public List<Activity> getAll() {
        return getAll(bundle.getString("activity.get.all"),
                getMapper());
    }

    @Override
    public boolean create(Activity activity) {
        return createUpdate(bundle.getString("activity.create"),
                ps -> {
                    ps.setString(1, activity.getName());
                    ps.setObject(2, activity.getOpeningTime());
                    ps.setObject(3, activity.getClosingTime());
                    ps.setObject(4, activity.getStatus());
                });
    }

    /**
     * Create and return activity.
     *
     * @param activity the activity
     * @return the activity
     */
    public Activity createAndReturn(Activity activity) {
        long id = createUpdateWithReturn(bundle.getString("activity.create"),
                ps -> {
                    ps.setString(1, activity.getName());
                    ps.setObject(2, new java.sql.Timestamp(activity.getOpeningTime().getTime()));
                    ps.setObject(3, activity.getStatus().toString());
                });
        return (id > 0) ? getById(id) : null;
    }

    @Override
    public boolean update(Activity activity) {
        return createUpdate(bundle.getString("activity.update"),
                ps -> {
                    ps.setObject(1, activity.getStatus());
                    ps.setLong(2, activity.getId());
                });
    }

    /**
     * Add user to activity boolean.
     *
     * @param activity the activity
     * @param user     the user
     * @return the boolean
     */
    public boolean addUserToActivity(Activity activity, User user) {
        return createUpdate(bundle.getString("activity.add.user"),
                ps -> {
                    ps.setLong(1, user.getId());
                    ps.setLong(2, activity.getId());
                });
    }

    @Override
    public boolean remove(Activity activity) {
        return createUpdate(bundle.getString("activity.delete"),
                ps -> {
                    ps.setLong(1, activity.getId());
                });
    }

    @Override
    public EntityMapper<Activity> getMapper() {
        return resultSet -> {
            return new Activity(
                    resultSet.getLong(ID),
                    resultSet.getString(NAME),
                    resultSet.getTimestamp(OPENING_TIME),
                    resultSet.getTimestamp(CLOSING_TIME),
                    ActivityStatus.valueOf(resultSet.getString(STATUS))
            );
        };
    }

    /**
     * Delete user from activity boolean.
     *
     * @param activity the activity
     * @param user     the user
     * @return the boolean
     */
    public boolean deleteUserFromActivity(Activity activity, User user) {
        return createUpdate(bundle.getString("activity.delete.user"),
                ps -> {
                    ps.setLong(1, user.getId());
                    ps.setLong(2, activity.getId());
                });
    }

    /**
     * Gets in range.
     *
     * @param currentPageInt the current page int
     * @param postOnPage     the post on page
     * @param userEmail      the user email
     * @return the in range
     */
    public List<Activity> getInRange(int currentPageInt, int postOnPage, String userEmail) {
        return null;
    }

    /**
     * Gets in available range.
     *
     * @param currentPageInt the current page int
     * @param postOnPage     the post on page
     * @param userEmail      the user email
     * @return the in available range
     */
    public List<Activity> getInAvailableRange(int currentPageInt, int postOnPage, String userEmail) {
        return null;
    }

    /**
     * Gets active activity by user id.
     *
     * @param id the id
     * @return the active activity by user id
     */
    public List<Activity> getActiveActivityByUserId(long id) {
        return getAllWithCondition(bundle.getString("activity.get.all.active"), getMapper(), ps -> {
            ps.setLong(1, id);
        });
    }

    /**
     * Gets all users activity.
     *
     * @param id the id
     * @return the all users activity
     */
    public List<Activity> getAllUsersActivity(long id) {
        return getAllWithCondition(bundle.getString("activity.get.all"), getMapper(), ps -> {
            ps.setLong(1, id);
        });
    }

    /**
     * Gets page count.
     *
     * @param userEmail the user email
     * @return the page count
     */
    public int getPageCount(String userEmail) {
        return 0;
    }
}
