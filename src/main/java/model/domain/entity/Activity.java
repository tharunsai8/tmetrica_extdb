package model.domain.entity;

import model.domain.enums.ActivityStatus;

import java.util.Date;
import java.util.Objects;
import java.util.Set;


public class Activity {

    private long id;
    private String name;
    private Date openingTime;
    private Date closingTime;
    private ActivityStatus status;
    private Set<User> users;

    public Activity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Set<ActivityLog> logs;

    public Activity() {
    }

    public Activity(String name, Date openingTime, ActivityStatus status) {
        this.name = name;
        this.openingTime = openingTime;
        this.status = status;
    }

    public Activity(long id, String name, Date openingTime, Date closingTime, ActivityStatus status, Set<User> users, Set<ActivityLog> logs) {
        this.id = id;
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.status = status;
        this.users = users;
        this.logs = logs;
    }

    public Activity(long id, String name, Date openingTime, Date closingTime, ActivityStatus status) {
        this.id = id;
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.status = status;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<ActivityLog> getLogs() {
        return logs;
    }

    public void setLogs(Set<ActivityLog> logs) {
        this.logs = logs;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }
}
