package model.dao.impl;

import model.dao.AbstractJDBCDao;
import model.dao.EntityMapper;
import model.domain.entity.Activity;
import model.domain.entity.User;
import model.domain.enums.ActivityStatus;

import java.util.List;
import java.util.ResourceBundle;

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
            return Activity.newBuilder().setId(resultSet.getLong(ID))
                    .setClosingTime(resultSet.getTimestamp(CLOSING_TIME))
                    .setOpeningTime(resultSet.getTimestamp(OPENING_TIME))
                    .setName(resultSet.getString(NAME))
                    .setStatus(ActivityStatus.valueOf(resultSet.getString(STATUS)))
                    .build();
        };
    }

    public boolean deleteUserFromActivity(Activity activity, User user) {
        return createUpdate(bundle.getString("activity.delete.user"),
                ps -> {
                    ps.setLong(1, user.getId());
                    ps.setLong(2, activity.getId());
                });
    }

    public List<Activity> getInRange(int currentPageInt, int postOnPage, String userEmail) {
        return null;
    }

    public List<Activity> getInAvailableRange(int currentPageInt, int postOnPage, String userEmail) {
        return null;
    }

    public List<Activity> getActiveActivityByUserId(long id) {
        return getAllWithCondition(bundle.getString("activity.get.all.active"), getMapper(), ps -> {
            ps.setLong(1, id);
        });
    }

    public List<Activity> getAllUsersActivity(long id) {
        return getAllWithCondition(bundle.getString("activity.get.all"), getMapper(), ps -> {
            ps.setLong(1, id);
        });
    }

    public int getPageCount(String userEmail) {
        return 0;
    }
}
