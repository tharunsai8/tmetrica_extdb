package model.domain.entity;

import model.domain.enums.ActivityStatus;

import java.util.Date;
import java.util.Objects;
import java.util.Set;


/**
 * The type Activity.
 */
public class Activity {

    private long id;
    private String name;
    private Date openingTime;
    private Date closingTime;
    private ActivityStatus status;
    private Set<User> users;

    /**
     * Instantiates a new Activity.
     *
     * @param id   the id
     * @param name the name
     */
    public Activity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Set<ActivityLog> logs;

    /**
     * Instantiates a new Activity.
     */
    public Activity() {
    }

    /**
     * Instantiates a new Activity.
     *
     * @param name        the name
     * @param openingTime the opening time
     * @param status      the status
     */
    public Activity(String name, Date openingTime, ActivityStatus status) {
        this.name = name;
        this.openingTime = openingTime;
        this.status = status;
    }

    public Activity(long id, String name, ActivityStatus status) {
        this.name = name;
        this.id = id;
        this.status = status;
    }

    /**
     * Instantiates a new Activity.
     *
     * @param id          the id
     * @param name        the name
     * @param openingTime the opening time
     * @param closingTime the closing time
     * @param status      the status
     * @param users       the users
     * @param logs        the logs
     */
    public Activity(long id, String name, Date openingTime, Date closingTime, ActivityStatus status, Set<User> users, Set<ActivityLog> logs) {
        this.id = id;
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.status = status;
        this.users = users;
        this.logs = logs;
    }

    /**
     * Instantiates a new Activity.
     *
     * @param id          the id
     * @param name        the name
     * @param openingTime the opening time
     * @param closingTime the closing time
     * @param status      the status
     */
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets opening time.
     *
     * @param openingTime the opening time
     */
    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
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
     * Sets closing time.
     *
     * @param closingTime the closing time
     */
    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
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
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(ActivityStatus status) {
        this.status = status;
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
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(Set<User> users) {
        this.users = users;
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
     * Sets logs.
     *
     * @param logs the logs
     */
    public void setLogs(Set<ActivityLog> logs) {
        this.logs = logs;
    }

    /**
     * Add user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean addUser(User user) {
        return users.add(user);
    }
}
