package model.dao.impl;

import model.dao.AbstractJDBCDao;
import model.dao.EntityMapper;
import model.domain.entity.Activity;
import model.domain.entity.User;
import model.domain.enums.ActivityStatus;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

/**
 * The type Activity dao.
 */
public class ActivityDao extends AbstractJDBCDao<Activity> {
    private static final Logger LOG = Logger.getLogger(ActivityDao.class);
    private final String ID = "id";
    private final String NAME = "name";
    private final String OPENING_TIME = "opening_time";
    private final String CLOSING_TIME = "closing_time";
    private final String STATUS = "status";
    private static ResourceBundle bundle = ResourceBundle.getBundle("database/queries");


    @Override
    public Activity getById(long id) {
        LOG.info("Trying execute " + bundle.getString("activity.get.id"));
        return getById(bundle.getString("activity.get.id"),
                ps -> ps.setLong(1, id),
                getMapper());
    }

    @Override
    public List<Activity> getAll() {
        LOG.info("Trying execute " + bundle.getString("activity.get.all"));
        return getAll(bundle.getString("activity.get.all"),
                getMapper());
    }

    @Override
    public boolean create(Activity activity) {
        LOG.info("Trying execute " + bundle.getString("activity.create") + " " + activity);
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
        LOG.info("Trying execute " + bundle.getString("activity.create") + " " + activity);
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
        LOG.info("Trying execute " + bundle.getString("activity.update") + " " + activity);
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
        LOG.info("Trying execute " + bundle.getString("activity.add.user") + " " + activity + " " + user);
        return createUpdate(bundle.getString("activity.add.user"),
                ps -> {
                    ps.setLong(1, user.getId());
                    ps.setLong(2, activity.getId());
                });
    }

    @Override
    public boolean remove(Activity activity) {
        LOG.info("Trying execute " + bundle.getString("activity.delete") + " " + activity);
        return createUpdate(bundle.getString("activity.delete"),
                ps -> {
                    ps.setLong(1, activity.getId());
                });
    }

    @Override
    public EntityMapper<Activity> getMapper() {
        LOG.info("Map prepared statement");
        return resultSet -> {
            return Activity.newBuilder().setId(resultSet.getLong(ID))
                    .setClosingTime(resultSet.getTimestamp(CLOSING_TIME))
                    .setOpeningTime(resultSet.getTimestamp(OPENING_TIME))
                    .setName(resultSet.getString(NAME))
                    .setStatus(ActivityStatus.valueOf(resultSet.getString(STATUS)))
                    .build();
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
        LOG.info("Trying execute " + bundle.getString("activity.delete.user") + " " + activity + " " + user);
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


    /**
     * Gets active activity by user id.
     *
     * @param id the id
     * @return the active activity by user id
     */
    public List<Activity> getActiveActivityByUserId(long id) {
        LOG.info("Trying execute " + bundle.getString("activity.get.all.active.full") + " userId: " + id);
        return getAllWithCondition(bundle.getString("activity.get.all.active.full"), getMapper(), ps -> {
            ps.setLong(1, id);
        });
    }

    /**
     * Gets all users activity.
     *
     * @param userId      the user id
     * @param currentPage the current page
     * @return the all users activity
     */
    public List<Activity> getAllUsersActivity(long userId, String currentPage) {
        int currentPageInt = currentPage != null ? Integer.parseInt(currentPage) : 1;
        currentPageInt = currentPageInt <= 0 ? 0 : (currentPageInt - 1) * OBJECT_ON_PAGE;
        int offset = currentPageInt;
        LOG.info("Trying execute " + bundle.getString("activity.get.all") + "userId: " + userId + " LIMIT: " + OBJECT_ON_PAGE + " OFFSET: " + offset);
        return getAllWithCondition(bundle.getString("activity.get.all"), getMapper(), ps -> {
            ps.setLong(1, userId);
            ps.setInt(2, OBJECT_ON_PAGE);
            ps.setInt(3, offset);
        });
    }

    /**
     * Gets active activity by user id.
     *
     * @param userId      the user id
     * @param currentPage the current page
     * @return the active activity by user id
     */
    public List<Activity> getActiveActivityByUserId(long userId, String currentPage) {
        int currentPageInt = currentPage != null ? Integer.parseInt(currentPage) : 1;
        currentPageInt = currentPageInt <= 0 ? 0 : (currentPageInt - 1) * OBJECT_ON_PAGE;
        int offset = currentPageInt;
        LOG.info("Trying execute " + bundle.getString("activity.get.all.active") + "userId: " + userId + " LIMIT: " + OBJECT_ON_PAGE + " OFFSET: " + offset);
        return getAllWithCondition(bundle.getString("activity.get.all.active"), getMapper(), ps -> {
            ps.setLong(1, userId);
            ps.setInt(2, OBJECT_ON_PAGE);
            ps.setInt(3, offset);
        });
    }

    /**
     * Gets page count for active activities.
     *
     * @param id the id
     * @return the page count for active activities
     */
    public int getPageCountForActiveActivities(long id) {
        LOG.info("Trying execute " + bundle.getString("activity.active.count") + " userId: " + id);
        return count(bundle.getString("activity.active.count"), ps -> ps.setLong(1, id)) / OBJECT_ON_PAGE + 1;
    }

    /**
     * Gets page count for user activities.
     *
     * @param id the id
     * @return the page count for user activities
     */
    public int getPageCountForUserActivities(long id) {
        LOG.info("Trying execute " + bundle.getString("activity.all.count") + " userId: " + id);
        return count(bundle.getString("activity.all.count"), ps -> ps.setLong(1, id)) / OBJECT_ON_PAGE + 1;
    }

    /**
     * Gets all users activity.
     *
     * @param userId the user id
     * @return the all users activity
     */
    public List<Activity> getAllUsersActivity(long userId) {
        LOG.info("Trying execute " + bundle.getString("activity.get.all.full") + " userId: " + userId);
        return getAllWithCondition(bundle.getString("activity.get.all.full"), getMapper(), ps -> {
            ps.setLong(1, userId);
        });
    }


}
