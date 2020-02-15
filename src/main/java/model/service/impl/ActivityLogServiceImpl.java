package model.service.impl;


import model.dao.impl.ActivityLogDao;
import model.domain.entity.Activity;
import model.domain.entity.ActivityLog;
import model.domain.entity.User;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.ActivityLogService;
import model.service.ActivityService;
import model.service.UserService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * The type Activity log service.
 */
public class ActivityLogServiceImpl implements ActivityLogService {

    private UserService userService;

    private ActivityService activityService;

    private ActivityLogDao logDao;


    /**
     * Instantiates a new Activity log service.
     */
    public ActivityLogServiceImpl() {
        userService = (UserService) ServiceFactory.getService(ServiceType.USERS);
        activityService = (ActivityService) ServiceFactory.getService(ServiceType.ACTIVITY);
        logDao = new ActivityLogDao();
    }

    @Override
    public ActivityLog getById(long id) {
        return logDao.getById(id);
    }

    @Override
    public boolean create(ActivityLog log) {
        return logDao.create(log);
    }

    @Override
    public List<ActivityLog> getAll() {
        return logDao.getAll();
    }

    @Override
    public List<ActivityLog> getAllByUser(long userId) {
        return logDao.getAllByUser(userId);
    }

    @Override
    public List<ActivityLog> getAllByUserAndActivity(long userId, long activityId) {
        return logDao.getAllByUserAndActivity(userId, activityId);
    }

    @Override
    public boolean create(String startDate, String endDate, String email, long activityId) throws ParseException {
        User user = userService.getByEmail(email);
        Activity activity = activityService.getById(activityId);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        ActivityLog log = new ActivityLog(activity, user, formatter.parse(startDate), formatter.parse(endDate));
        return logDao.create(log);
    }

    @Override
    public boolean update(ActivityLog log) {
        return logDao.update(log);
    }

    @Override
    public boolean update(long id, String startDate, String endDate, String email, long activityId) throws ParseException {
        User user = userService.getByEmail(email);
        Activity activity = activityService.getById(activityId);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        ActivityLog log = new ActivityLog(id, activity, user, formatter.parse(startDate), formatter.parse(endDate));
        return logDao.update(log);
    }

    @Override
    public boolean delete(long logId) {
        ActivityLog log = logDao.getById(logId);
        return logDao.remove(log);
    }

    //TODO add pagination
}
