package model.service;

import model.domain.entity.Order;

import java.util.List;

/**
 * The interface Order service.
 */
public interface OrderService extends Service {
    /**
     * Create boolean.
     *
     * @param order the order
     * @return the boolean
     */
    boolean create(Order order);

    /**
     * Gets all reviewed pages.
     *
     * @return the all reviewed pages
     */
    int getAllReviewedPages();

    /**
     * Gets all pending pages.
     *
     * @return the all pending pages
     */
    int getAllPendingPages();

    /**
     * Gets all.
     *
     * @return the all
     */
    List<Order> getAll();

    /**
     * Gets all pending.
     *
     * @return the all pending
     */
    List<Order> getAllPending();

    /**
     * Gets all pending.
     *
     * @param page the page
     * @return the all pending
     */
    List<Order> getAllPending(String page);

    /**
     * Gets all reviewed.
     *
     * @return the all reviewed
     */
    List<Order> getAllReviewed();

    /**
     * Create new activity order order.
     *
     * @param activityName the activity name
     * @param email        the email
     * @return the order
     */
    Order createNewActivityOrder(String activityName, String email);

    /**
     * Create join to activity order order.
     *
     * @param email      the email
     * @param activityId the activity id
     * @return the order
     */
    Order createJoinToActivityOrder(String email, long activityId);

    /**
     * Create delete activity order order.
     *
     * @param email      the email
     * @param activityId the activity id
     * @return the order
     */
    Order createDeleteActivityOrder(String email, long activityId);

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    Order getById(long id);

    /**
     * Approve order.
     *
     * @param order the order
     */
    void approveOrder(Order order);

    /**
     * Reject order.
     *
     * @param order the order
     */
    void rejectOrder(Order order);

    /**
     * Gets all reviewed.
     *
     * @param currentPage the current page
     * @return the all reviewed
     */
    List<Order> getAllReviewed(String currentPage);


}
