package model.domain.entity;

import model.domain.enums.ActivityStatus;

import java.util.Date;
import java.util.Objects;
import java.util.Set;


/**
 * The type Activity.
 */
public final class Activity{

    private long id;
    private String name;
    private Date openingTime;
    private Date closingTime;
    private ActivityStatus status;
    private Set<User> users;
    private Set<ActivityLog> logs;

    private Activity() {

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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets opening time.
     *
     * @return the opening time
     */
    public Date getOpeningTime() {
        return openingTime;
    }

    /**
     * Gets closing time.
     *
     * @return the closing time
     */
    public Date getClosingTime() {
        return closingTime;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public ActivityStatus getStatus() {
        return status;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * Gets logs.
     *
     * @return the logs
     */
    public Set<ActivityLog> getLogs() {
        return logs;
    }

    /**
     * New builder activity builder.
     *
     * @return the activity builder
     */
    public static ActivityBuilder newBuilder() {
        return new Activity().new ActivityBuilder();
    }

    /**
     * The type Activity builder.
     */
    public class ActivityBuilder {

        private ActivityBuilder() {
        }

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public ActivityBuilder setId(long id) {
            Activity.this.id = id;
            return this;
        }

        /**
         * Sets name.
         *
         * @param name the name
         * @return the name
         */
        public ActivityBuilder setName(String name) {
            Activity.this.name = name;
            return this;
        }

        /**
         * Modify status activity builder.
         *
         * @param activity the activity
         * @param status   the status
         * @return the activity builder
         */
        public ActivityBuilder modifyStatus(Activity activity, ActivityStatus status) {
            this.clone(activity);
            Activity.this.status = status;
            return this;
        }

        /**
         * Sets opening time.
         *
         * @param openingTime the opening time
         * @return the opening time
         */
        public ActivityBuilder setOpeningTime(Date openingTime) {
            Activity.this.openingTime = openingTime;
            return this;
        }

        /**
         * Sets closing time.
         *
         * @param closingTime the closing time
         * @return the closing time
         */
        public ActivityBuilder setClosingTime(Date closingTime) {
            Activity.this.closingTime = closingTime;
            return this;
        }

        /**
         * Sets status.
         *
         * @param status the status
         * @return the status
         */
        public ActivityBuilder setStatus(ActivityStatus status) {
            Activity.this.status = status;
            return this;
        }

        /**
         * Sets users.
         *
         * @param users the users
         * @return the users
         */
        public ActivityBuilder setUsers(Set<User> users) {
            Activity.this.users = users;
            return this;
        }


        /**
         * Sets logs.
         *
         * @param logs the logs
         * @return the logs
         */
        public ActivityBuilder setLogs(Set<ActivityLog> logs) {
            Activity.this.logs = logs;
            return this;
        }

        /**
         * Build activity.
         *
         * @return the activity
         */
        public Activity build() {
            return Activity.this;
        }

        private void clone(Activity activity) {
            Activity.this.openingTime = activity.openingTime;
            Activity.this.closingTime = activity.closingTime;
            Activity.this.users = activity.users;
            Activity.this.name = activity.name;
            Activity.this.status = activity.status;
            Activity.this.id = activity.id;
            Activity.this.logs = activity.logs;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return id == activity.id &&
                Objects.equals(name, activity.name) &&
                Objects.equals(openingTime, activity.openingTime) &&
                Objects.equals(closingTime, activity.closingTime) &&
                status == activity.status &&
                Objects.equals(users, activity.users) &&
                Objects.equals(logs, activity.logs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, openingTime, closingTime, status, users, logs);
    }

}
