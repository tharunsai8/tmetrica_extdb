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
                    rs.getTime(TIME_FROM),
                    rs.getTime(TIME_TO));
        };
    }
}
