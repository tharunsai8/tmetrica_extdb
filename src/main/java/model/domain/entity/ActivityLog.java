package model.domain.entity;

import java.util.Date;
import java.util.Objects;

/**
 * The type Activity log.
 */
public class ActivityLog {

    private long id;
    private Activity activity;
    private User user;
    private Date startTime;
    private Date endTime;

    /**
     * Instantiates a new Activity log.
     */
    public ActivityLog() {
    }

    /**
     * Instantiates a new Activity log.
     *
     * @param activity  the activity
     * @param user      the user
     * @param startTime the start time
     * @param endTime   the end time
     */
    public ActivityLog(Activity activity, User user, Date startTime, Date endTime) {
        this.activity = activity;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Instantiates a new Activity log.
     *
     * @param id        the id
     * @param activity  the activity
     * @param user      the user
     * @param startTime the start time
     * @param endTime   the end time
     */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets activity.
     *
     * @return the activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Sets activity.
     *
     * @param activity the activity
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public Date getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "ActivityLog{" +
                "id=" + id +
                ", activity=" + activity +
                ", user=" + user +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
