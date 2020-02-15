package model.domain.entity;

import model.domain.enums.ActivityStatus;

import java.util.Date;
import java.util.Objects;
import java.util.Set;


public final class Activity {

    private long id;
    private String name;
    private Date openingTime;
    private Date closingTime;
    private ActivityStatus status;
    private Set<User> users;
    private Set<ActivityLog> logs;

    private Activity() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getOpeningTime() {
        return openingTime;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<ActivityLog> getLogs() {
        return logs;
    }

    public static ActivityBuilder newBuilder() {
        return new Activity().new ActivityBuilder();
    }

    public class ActivityBuilder {

        private ActivityBuilder() {
        }

        public ActivityBuilder setId(long id) {
            Activity.this.id = id;
            return this;
        }

        public ActivityBuilder setName(String name) {
            Activity.this.name = name;
            return this;
        }

        public ActivityBuilder setOpeningTime(Date openingTime) {
            Activity.this.openingTime = openingTime;
            return this;
        }

        public ActivityBuilder setClosingTime(Date closingTime) {
            Activity.this.closingTime = closingTime;
            return this;
        }

        public ActivityBuilder setStatus(ActivityStatus status) {
            Activity.this.status = status;
            return this;
        }

        public ActivityBuilder setUsers(Set<User> users) {
            Activity.this.users = users;
            return this;
        }

        public ActivityBuilder setLogs(Set<ActivityLog> logs) {
            Activity.this.logs = logs;
            return this;
        }

        public Activity build() {
            return Activity.this;
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
