package model.service;

import model.domain.entity.ActivityLog;

import java.text.ParseException;
import java.util.List;

public interface ActivityLogService extends Service {
    ActivityLog getById(long id);

    boolean create(ActivityLog log);

    List<ActivityLog> getAll();

    boolean create(String startDate, String endDate, String email, long activityId) throws ParseException;

    boolean update(ActivityLog log);

    boolean update(long id, String startDate, String endDate, String email, long activityId) throws ParseException;

    boolean delete(long logId);
}
