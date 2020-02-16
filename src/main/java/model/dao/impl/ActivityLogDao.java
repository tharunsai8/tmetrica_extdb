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

import java.util.List;
import java.util.ResourceBundle;

public class ActivityLogDao extends AbstractJDBCDao<ActivityLog> {
    private static final String USER_ID = "user_id";
    private static final String ACTIVITY_ID = "activity_id";
    private static final String ID = "id";
    private static final String TIME_FROM = "start_time";
    private static final String TIME_TO = "end_time";
    private static ResourceBundle bundle = ResourceBundle.getBundle("database/queries");
    private ActivityService activityService;
    private UserService userService;

    public ActivityLogDao() {
        activityService = (ActivityService) ServiceFactory.getService(ServiceType.ACTIVITY);
        userService = (UserService) ServiceFactory.getService(ServiceType.USERS);
    }

    @Override
    public ActivityLog getById(long id) {
        return getById(bundle.getString("log.get.id"),
                ps -> ps.setLong(1, id),
                getMapper());
    }

    @Override
    public List<ActivityLog> getAll() {
        return getAll(bundle.getString("log.get.all"),
                getMapper());
    }

    @Override
    public boolean create(ActivityLog entity) {
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
        return createUpdate(bundle.getString("log.remove"),
                ps -> ps.setLong(1, entity.getId()));
    }

    @Override
    public EntityMapper<ActivityLog> getMapper() {
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

    public List<ActivityLog> getAllByUser(long userId) {
        return getAllWithCondition(bundle.getString("log.get.by.user"), getMapper(), ps -> {
            ps.setLong(1, userId);
        });
    }

    public List<ActivityLog> getAllByUserAndActivity(long userId, long activityId) {
        return getAllWithCondition(bundle.getString("log.get.by.user.and.activity"), getMapper(), ps -> {
            ps.setLong(1, userId);
            ps.setLong(2, activityId);
        });
    }

    public int getAllByUserPages(long userId) {
        return countPages(bundle.getString("log.get.by.user.count"), ps -> ps.setLong(1, userId)) / OBJECT_ON_PAGE + 1;
    }

    public int getAllByUserAndActivityPages(long userId, long activityId) {
        return countPages(bundle.getString("log.get.by.user.and.activity.count"), ps -> {
            ps.setLong(1, userId);
            ps.setLong(2, activityId);
        }) / OBJECT_ON_PAGE + 1;
    }

    public List<ActivityLog> getAllByUser(long userId, String currentPage) {
        int currentPageInt = currentPage != null ? Integer.parseInt(currentPage) : 1;
        currentPageInt = currentPageInt <= 0 ? 0 : (currentPageInt - 1) * OBJECT_ON_PAGE;
        int offset = currentPageInt;
        return getAllWithCondition(bundle.getString("log.get.by.user.pages"), getMapper(), ps -> {
            ps.setLong(1, userId);
            ps.setInt(2, OBJECT_ON_PAGE);
            ps.setInt(3, offset);
        });
    }

    public List<ActivityLog> getAllByUserAndActivity(long userId, long activityId, String currentPage) {
        int currentPageInt = currentPage != null ? Integer.parseInt(currentPage) : 1;
        currentPageInt = currentPageInt <= 0 ? 0 : (currentPageInt - 1) * OBJECT_ON_PAGE;
        int offset = currentPageInt;
        return getAllWithCondition(bundle.getString("log.get.by.user.and.activity.pages"), getMapper(), ps -> {
            ps.setLong(1, userId);
            ps.setLong(2, activityId);
            ps.setInt(3, OBJECT_ON_PAGE);
            ps.setInt(4, offset);
        });
    }
}
