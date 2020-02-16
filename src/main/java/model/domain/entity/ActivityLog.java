package model.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ActivityLog implements Serializable {

    private long id;
    private Activity activity;
    private User user;
    private Date startTime;
    private Date endTime;

    public ActivityLog() {
    }

    public ActivityLog(Activity activity, User user, Date startTime, Date endTime) {
        this.activity = activity;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ActivityLog(long id, Activity activity, User user, Date startTime, Date endTime) {
        this.id = id;
        this.activity = activity;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityLog that = (ActivityLog) o;
        return id == that.id &&
                Objects.equals(activity, that.activity) &&
                Objects.equals(user, that.user) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activity, user, startTime, endTime);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
