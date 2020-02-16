package model.domain.entity;

import model.domain.enums.OrderAction;
import model.domain.enums.OrderStatus;

import java.util.Objects;

/**
 * The type Order.
 */
public class Order {
    private long id;
    private OrderAction action;
    private Activity activity;
    private User user;
    private OrderStatus status;

    /**
     * Instantiates a new Order.
     */
    public Order() {
    }

    /**
     * Instantiates a new Order.
     *
     * @param id       the id
     * @param action   the action
     * @param activity the activity
     * @param user     the user
     * @param status   the status
     */
    public Order(long id, OrderAction action, Activity activity, User user, OrderStatus status) {
        this.id = id;
        this.action = action;
        this.activity = activity;
        this.user = user;
        this.status = status;
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
     * Gets action.
     *
     * @return the action
     */
    public OrderAction getAction() {
        return action;
    }

    /**
     * Sets action.
     *
     * @param action the action
     */
    public void setAction(OrderAction action) {
        this.action = action;
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
     * Gets status.
     *
     * @return the status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                action == order.action &&
                Objects.equals(activity, order.activity) &&
                Objects.equals(user, order.user) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, action, activity, user, status);
    }
}
