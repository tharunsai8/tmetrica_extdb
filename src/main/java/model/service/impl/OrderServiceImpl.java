package model.service.impl;

import model.dao.impl.OrderDao;
import model.domain.entity.Activity;
import model.domain.entity.Order;
import model.domain.entity.User;
import model.domain.enums.ActivityStatus;
import model.domain.enums.OrderAction;
import model.domain.enums.OrderStatus;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.ActivityService;
import model.service.OrderService;
import model.service.UserService;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService{

    private OrderDao orderDao;

    private UserService userService;

    private ActivityService activityService;

    public OrderServiceImpl() {
        this.orderDao = new OrderDao();
        userService = (UserService) ServiceFactory.getService(ServiceType.USERS);
        activityService = (ActivityService) ServiceFactory.getService(ServiceType.ACTIVITY);
    }

    @Override
    public boolean create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public int getAllReviewedPages() {
        return orderDao.getAllReviewedPages();
    }

    @Override
    public int getAllPendingPages() {
        return orderDao.getAllPendingPages();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public List<Order> getAllPending() {
        return orderDao.getAllPending();
    }

    @Override
    public List<Order> getAllPending(String page) {
        return orderDao.getAllPending(page);
    }

    @Override
    public List<Order> getAllReviewed() {
        return orderDao.getAllReviewed();
    }

    @Override
    public Order createNewActivityOrder(String activityName, String email) {
        User user = userService.getByEmail(email);
        Activity activity = activityService.createAndReturn(Activity.newBuilder()
                .setOpeningTime(new Date())
                .setStatus(ActivityStatus.SUSPENDED)
                .setName(activityName)
                .build(), user.getId());
        activityService.addUserToActivity(activity, user);
        Order order = new Order(1, OrderAction.CREATE, activityService.getById(activity.getId()), user, OrderStatus.PENDING);
        return orderDao.createAndReturn(order);
    }

    @Override
    public Order createJoinToActivityOrder(String email, long activityId) {
        User user = userService.getByEmail(email);
        Activity activity = activityService.getById(activityId);
        Order order = new Order(1, OrderAction.JOIN, activity, user, OrderStatus.PENDING);
        return orderDao.createAndReturn(order);
    }

    @Override
    public Order createDeleteActivityOrder(String email, long activityId) {
        User user = userService.getByEmail(email);
        Activity activity = activityService.getById(activityId);
        Order order = new Order(1, OrderAction.DELETE, activity, user, OrderStatus.PENDING);
        return orderDao.createAndReturn(order);
    }

    @Override
    public Order getById(long id) {
        return orderDao.getById(id);
    }

    @Override
    public void approveOrder(Order order) {
        Activity activity = order.getActivity();
        User user = order.getUser();
        if (order.getAction().equals(OrderAction.CREATE)) {
            prepareCreateOrderToApprove(order, activity);
        } else if (order.getAction().equals(OrderAction.JOIN)) {
            prepareJoinOrderToApprove(order, activity, user);
        } else {
            if (lastUserInActivity(activity)) {
                return;
            }
            prepareDeleteOrderToApprove(order, activity, user);
        }
        order.setStatus(OrderStatus.ACCEPTED);
        orderDao.update(order);
    }

    @Override
    public void rejectOrder(Order order) {
        Activity activity = order.getActivity();
        if (order.getAction().equals(OrderAction.CREATE)) {
            activityService.delete(activity);
        } else {
            order.setStatus(OrderStatus.REJECTED);
            orderDao.update(order);
        }
    }

    @Override
    public List<Order> getAllReviewed(String currentPage) {
        return orderDao.getAllReviewed(currentPage);
    }

    private void prepareCreateOrderToApprove(Order order, Activity activity) {
        activity = Activity.newBuilder().modifyStatus(activity, ActivityStatus.ACTIVE).build();
        Activity activityToSave = activityService.updateAndReturn(activity);
        order.setActivity(activityToSave);
    }

    private void prepareJoinOrderToApprove(Order order, Activity activity, User user) {
        activityService.addUserToActivity(activity, user);
        order.setActivity(activityService.getById(activity.getId()));
    }

    private void prepareDeleteOrderToApprove(Order order, Activity activity, User user) {
        activityService.deleteUserFromActivity(activity, user);
        order.setActivity(activityService.getById(activity.getId()));
    }

    private boolean lastUserInActivity(Activity activity) {
        if (userService.getAllByActivity(activity.getId()).size() <= 1) {
            activityService.delete(activity);
            return true;
        }
        return false;
    }


    //TODO Add pagination
}
