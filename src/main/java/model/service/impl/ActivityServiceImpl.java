package model.service.impl;

import model.dao.impl.ActivityDao;
import model.domain.entity.Activity;
import model.domain.entity.User;
import model.domain.enums.ActivityStatus;
import model.service.ActivityService;

import java.util.List;

public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao;


    public ActivityServiceImpl() {
        activityDao = new ActivityDao();
    }

    @Override
    public Activity getById(long id) {
        return activityDao.getById(id);
    }

    @Override
    public List<Activity> getAll() {
        return activityDao.getAll();
    }

    @Override
    public boolean create(Activity activity, long userId) {
        activity = Activity.newBuilder().modifyStatus(activity, ActivityStatus.SUSPENDED).build();
        return activityDao.create(activity);
    }

    @Override
    public List<Activity> getActiveActivityByUser(long userId) {
        return activityDao.getActiveActivityByUserId(userId);
    }

    @Override
    public List<Activity> getActiveActivityByUser(long userId, String currentPage) {
        return activityDao.getActiveActivityByUserId(userId, currentPage);
    }

    @Override
    public List<Activity> getAllActivityByUser(long userId, String currentPage) {
        return activityDao.getAllUsersActivity(userId, currentPage);
    }

    @Override
    public List<Activity> getAllActivityByUser(long userId) {
        return activityDao.getAllUsersActivity(userId);
    }


    @Override
    public Activity createAndReturn(Activity activity, long userId) {
        activity = Activity.newBuilder().modifyStatus(activity, ActivityStatus.SUSPENDED).build();
        return activityDao.createAndReturn(activity);
    }

    @Override
    public boolean update(Activity activity) {
        return activityDao.update(activity);
    }

    @Override
    public Activity updateAndReturn(Activity activity) {
        if (activityDao.update(activity)) {
            return activity;
        }
        return null;
    }

    @Override
    public boolean delete(Activity activity) {
        return activityDao.remove(activity);
    }

    @Override
    public boolean addUserToActivity(Activity activity, User user) {
        return activityDao.addUserToActivity(activity, user);
    }

    @Override
    public boolean deleteUserFromActivity(Activity activity, User user) {
        return activityDao.deleteUserFromActivity(activity, user);
    }

    @Override
    public int getUserActivitiesPages(long userId) {
        return activityDao.getPageCountForUserActivities(userId);
    }

    @Override
    public int getActiveActivitiesPages(long userId) {
        return activityDao.getPageCountForActiveActivities(userId);
    }


}

//TODO add pagination
