package model.service;

import model.domain.entity.Activity;
import model.domain.entity.User;

import java.util.List;

public interface ActivityService extends Service {
    Activity getById(long id);

    List<Activity> getAll();

    boolean create(Activity activity, long userId);

    Activity createAndReturn(Activity activity, long userId);

    boolean update(Activity activity);

    Activity updateAndReturn(Activity activity);

    boolean delete(Activity activity);

    boolean addUserToActivity(Activity activity, User user);

    boolean deleteUserFromActivity(Activity activity, User user);

    List<Activity> getActivityList(String currentPage, String userEmail, int postOnPage);

    List<Activity> getAvailableActivityList(String currentPage, String userEmail, int postOnPage);

    int getPageCount(String userEmail, int postOnPage);
}
