package model.domain.entity;

import model.domain.enums.OrderAction;
import model.domain.enums.OrderStatus;

import java.util.Objects;

public class Order {
    private long id;
    private OrderAction action;
    private Activity activity;
    private User user;
    private OrderStatus status;

    public Order() {
    }

    public Order(long id, OrderAction action, Activity activity, User user, OrderStatus status) {
        this.id = id;
        this.action = action;
        this.activity = activity;
        this.user = user;
        this.status = status;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderAction getAction() {
        return action;
    }

    public void setAction(OrderAction action) {
        this.action = action;
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

    public OrderStatus getStatus() {
        return status;
    }

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
