package model.dao.impl;

import model.dao.AbstractJDBCDao;
import model.dao.EntityMapper;
import model.domain.entity.Activity;
import model.domain.entity.ActivityLog;
import model.domain.entity.User;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.ActivityService;
import model.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

/**
 * The type Activity log dao.
 */
public class ActivityLogDao extends AbstractJDBCDao<ActivityLog> {
    private static final Logger LOG = Logger.getLogger(ActivityDao.class);
    private static final String USER_ID = "user_id";
    private static final String ACTIVITY_ID = "activity_id";
    private static final String ID = "id";
    private static final String TIME_FROM = "start_time";
    private static final String TIME_TO = "end_time";
    private static ResourceBundle bundle = ResourceBundle.getBundle("database/queries");
    private ActivityService activityService;
    private UserService userService;

    /**
     * Instantiates a new Activity log dao.
     */
    public ActivityLogDao() {
        activityService = (ActivityService) ServiceFactory.getService(ServiceType.ACTIVITY);
        userService = (UserService) ServiceFactory.getService(ServiceType.USERS);
    }

    @Override
    public ActivityLog getById(long id) {
        LOG.info("Trying execute " + bundle.getString("log.get.id") + "id: " + id);
        return getById(bundle.getString("log.get.id"),
                ps -> ps.setLong(1, id),
                getMapper());
    }

    @Override
    public List<ActivityLog> getAll() {
        LOG.info("Trying execute " + bundle.getString("log.get.all"));
        return getAll(bundle.getString("log.get.all"),
                getMapper());
    }

    @Override
    public boolean create(ActivityLog entity) {
        LOG.info("Trying execute " + bundle.getString("log.add") + " log: " + entity);
        return createUpdate(bundle.getString("log.add"),
                ps -> {
                    ps.setLong(1, entity.getActivity().getId());
                    ps.setLong(2, entity.getUser().getId());
                    ps.setObject(3, new java.sql.Timestamp(entity.getStartTime().getTime()));
                    ps.setObject(4, new java.sql.Timestamp(entity.getEndTime().getTime()));
                });
    }

    @Override
    public boolean update(ActivityLog entity) {
        LOG.info("Trying execute " + bundle.getString("log.update") + " log: " + entity);
        return createUpdate(bundle.getString("log.update"),
                ps -> {
                    ps.setLong(1, entity.getActivity().getId());
                    ps.setLong(2, entity.getUser().getId());
                    ps.setObject(3, new java.sql.Timestamp(entity.getStartTime().getTime()));
                    ps.setObject(4, new java.sql.Timestamp(entity.getEndTime().getTime()));
                    ps.setLong(5, entity.getId());
                });
    }

    @Override
    public boolean remove(ActivityLog entity) {
        LOG.info("Trying execute " + bundle.getString("log.remove") + " log: " + entity);
        return createUpdate(bundle.getString("log.remove"),
                ps -> ps.setLong(1, entity.getId()));
    }

    @Override
    public EntityMapper<ActivityLog> getMapper() {
        LOG.info("Map statement");
        return rs -> {
            Activity activity = activityService.getById(rs.getLong(ACTIVITY_ID));
            User user = userService.getById(rs.getLong(USER_ID));
            return new ActivityLog(
                    rs.getLong(ID),
                    activity,
                    user,
                    rs.getTimestamp(TIME_FROM),
                    rs.getTimestamp(TIME_TO));
        };
    }

    /**
     * Gets all by user.
     *
     * @param userId the user id
     * @return the all by user
     */
    public List<ActivityLog> getAllByUser(long userId) {
        LOG.info("Trying execute " + bundle.getString("log.get.by.user") + " userId: " + userId);
        return getAllWithCondition(bundle.getString("log.get.by.user"), getMapper(), ps -> {
            ps.setLong(1, userId);
        });
    }

    /**
     * Gets all by user and activity.
     *
     * @param userId     the user id
     * @param activityId the activity id
     * @return the all by user and activity
     */
    public List<ActivityLog> getAllByUserAndActivity(long userId, long activityId) {
        LOG.info("Trying execute " + bundle.getString("log.get.by.user") + " userId: " + userId + " activityId: " + activityId);
        return getAllWithCondition(bundle.getString("log.get.by.user.and.activity"), getMapper(), ps -> {
            ps.setLong(1, userId);
            ps.setLong(2, activityId);
        });
    }

    /**
     * Gets all by user pages.
     *
     * @param userId the user id
     * @return the all by user pages
     */
    public int getAllByUserPages(long userId) {
        LOG.info("Trying execute " + bundle.getString("log.get.by.user.count") + " userId: " + userId);
        return count(bundle.getString("log.get.by.user.count"), ps -> ps.setLong(1, userId)) / OBJECT_ON_PAGE + 1;
    }

    /**
     * Gets all by user and activity pages.
     *
     * @param userId     the user id
     * @param activityId the activity id
     * @return the all by user and activity pages
     */
    public int getAllByUserAndActivityPages(long userId, long activityId) {
        LOG.info("Trying execute " + bundle.getString("log.get.by.user.and.activity.count") + " userId: " + userId + " activityId: " + activityId);
        return count(bundle.getString("log.get.by.user.and.activity.count"), ps -> {
            ps.setLong(1, userId);
            ps.setLong(2, activityId);
        }) / OBJECT_ON_PAGE + 1;
    }

    /**
     * Gets all by user.
     *
     * @param userId      the user id
     * @param currentPage the current page
     * @return the all by user
     */
    public List<ActivityLog> getAllByUser(long userId, String currentPage) {
        int currentPageInt = currentPage != null ? Integer.parseInt(currentPage) : 1;
        currentPageInt = currentPageInt <= 0 ? 0 : (currentPageInt - 1) * OBJECT_ON_PAGE;
        int offset = currentPageInt;
        LOG.info("Trying execute " + bundle.getString("log.get.by.user.pages") + "userId: " + userId + " LIMIT: " + OBJECT_ON_PAGE + " OFFSET: " + offset);
        return getAllWithCondition(bundle.getString("log.get.by.user.pages"), getMapper(), ps -> {
            ps.setLong(1, userId);
            ps.setInt(2, OBJECT_ON_PAGE);
            ps.setInt(3, offset);
        });
    }

    /**
     * Gets all by user and activity.
     *
     * @param userId      the user id
     * @param activityId  the activity id
     * @param currentPage the current page
     * @return the all by user and activity
     */
    public List<ActivityLog> getAllByUserAndActivity(long userId, long activityId, String currentPage) {
        int currentPageInt = currentPage != null ? Integer.parseInt(currentPage) : 1;
        currentPageInt = currentPageInt <= 0 ? 0 : (currentPageInt - 1) * OBJECT_ON_PAGE;
        int offset = currentPageInt;
        LOG.info("Trying execute " + bundle.getString("log.get.by.user.and.activity.pages") + "userId: " + userId + " LIMIT: " + OBJECT_ON_PAGE + " OFFSET: " + offset);
        return getAllWithCondition(bundle.getString("log.get.by.user.and.activity.pages"), getMapper(), ps -> {
            ps.setLong(1, userId);
            ps.setLong(2, activityId);
            ps.setInt(3, OBJECT_ON_PAGE);
            ps.setInt(4, offset);
        });
    }
}
