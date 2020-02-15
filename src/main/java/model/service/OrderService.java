package model.service;

import model.domain.entity.Order;

import java.util.List;

public interface OrderService extends Service {
    boolean create(Order order);

    List<Order> getAll();

    List<Order> getAllPending();

    List<Order> getAllReviewed();

    Order createNewActivityOrder(String activityName, String email);

    Order createJoinToActivityOrder(String email, long activityId);

    Order createDeleteActivityOrder(String email, long activityId);

    Order getById(long id);

    void approveOrder(Order order);

    void rejectOrder(Order order);
}
