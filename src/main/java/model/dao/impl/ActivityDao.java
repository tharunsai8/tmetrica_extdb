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
        return getAllWithCondition(bundle.getString("activity.get.all.active.full"), getMapper(), ps -> {
            ps.setLong(1, id);
        });
    }

    public List<Activity> getAllUsersActivity(long userId, String currentPage) {
        int currentPageInt = currentPage != null ? Integer.parseInt(currentPage) : 1;
        currentPageInt = currentPageInt <= 0 ? 0 : (currentPageInt - 1) * OBJECT_ON_PAGE;
        int offset = currentPageInt;
        return getAllWithCondition(bundle.getString("activity.get.all"), getMapper(), ps -> {
            ps.setLong(1, userId);
            ps.setInt(2, OBJECT_ON_PAGE);
            ps.setInt(3, offset);
        });
    }

    public List<Activity> getActiveActivityByUserId(long userId, String currentPage) {
        int currentPageInt = currentPage != null ? Integer.parseInt(currentPage) : 1;
        currentPageInt = currentPageInt <= 0 ? 0 : (currentPageInt - 1) * OBJECT_ON_PAGE;
        int offset = currentPageInt;
        return getAllWithCondition(bundle.getString("activity.get.all.active"), getMapper(), ps -> {
            ps.setLong(1, userId);
            ps.setInt(2, OBJECT_ON_PAGE);
            ps.setInt(3, offset);
        });
    }

    public int getPageCountForActiveActivities(long id) {
        return countPages(bundle.getString("activity.active.count"), ps -> ps.setLong(1, id)) / OBJECT_ON_PAGE + 1;
    }

    public int getPageCountForUserActivities(long id) {
        return countPages(bundle.getString("activity.all.count"), ps -> ps.setLong(1, id)) / OBJECT_ON_PAGE + 1;
    }

    public List<Activity> getAllUsersActivity(long userId) {
        return getAllWithCondition(bundle.getString("activity.get.all.full"), getMapper(), ps -> {
            ps.setLong(1, userId);
        });
    }


}
