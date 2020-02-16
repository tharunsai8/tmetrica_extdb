package model.dao.impl;

import model.dao.AbstractJDBCDao;
import model.dao.EntityMapper;
import model.domain.entity.Activity;
import model.domain.entity.Order;
import model.domain.entity.User;
import model.domain.enums.OrderAction;
import model.domain.enums.OrderStatus;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.ActivityService;
import model.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

/**
 * The type Order dao.
 */
public class OrderDao extends AbstractJDBCDao<Order> {
    private static final Logger LOG = Logger.getLogger(ActivityDao.class);
    private final String ID = "id";
    private final String ACTION = "action";
    private final String STATUS = "status";
    private static final String USER_ID = "user_id";
    private static final String ACTIVITY_ID = "activity_id";
    private static ResourceBundle bundle = ResourceBundle.getBundle("database/queries");
    private ActivityService activityService;
    private UserService userService;

    /**
     * Instantiates a new Order dao.
     */
    public OrderDao() {
        activityService = (ActivityService) ServiceFactory.getService(ServiceType.ACTIVITY);
        userService = (UserService) ServiceFactory.getService(ServiceType.USERS);
    }

    @Override
    public Order getById(long id) {
        LOG.info("Trying execute " + bundle.getString("order.get.id") + "id: " + id);
        return getById(bundle.getString("order.get.id"),
                ps -> ps.setLong(1, id),
                getMapper()
        );
    }

    @Override
    public List<Order> getAll() {
        LOG.info("Trying execute " + bundle.getString("order.get.all"));
        return getAll(bundle.getString("order.get.all"),
                getMapper());
    }

    @Override
    public boolean create(Order entity) {
        LOG.info("Trying execute " + bundle.getString("order.create") + " order: " + entity);
        return createUpdate(bundle.getString("order.create"),
                ps -> {
                    ps.setObject(1, entity.getAction());
                    ps.setLong(2, entity.getActivity().getId());
                    ps.setLong(3, entity.getUser().getId());
                    ps.setObject(4, entity.getStatus());
                });
    }

    /**
     * Create and return order.
     *
     * @param entity the entity
     * @return the order
     */
    public Order createAndReturn(Order entity) {
        LOG.info("Trying execute " + bundle.getString("order.create") + " order: " + entity);
        long id = createUpdateWithReturn(bundle.getString("order.create"),
                ps -> {
                    ps.setObject(1, entity.getAction());
                    ps.setLong(2, entity.getActivity().getId());
                    ps.setLong(3, entity.getUser().getId());
                    ps.setObject(4, entity.getStatus().toString());
                });
        return getById(id);
    }

    @Override
    public boolean update(Order entity) {
        LOG.info("Trying execute " + bundle.getString("order.update") + " order: " + entity);
        return createUpdate(bundle.getString("order.update"),
                ps -> {
                    ps.setObject(1, entity.getStatus());
                    ps.setLong(2, entity.getId());
                });
    }

    @Override
    public boolean remove(Order entity) {
        LOG.info("Trying execute " + bundle.getString("order.remove") + " order: " + entity);
        return createUpdate(bundle.getString("order.remove"),
                ps -> ps.setLong(1, entity.getId()));
    }

    @Override
    public EntityMapper<Order> getMapper() {
        return rs -> {
            Activity activity = activityService.getById(rs.getLong(ACTIVITY_ID));
            User user = userService.getById(rs.getLong(USER_ID));
            return new Order(
                    rs.getLong(ID),
                    OrderAction.valueOf(rs.getString(ACTION)),
                    activity,
                    user,
                    OrderStatus.valueOf(rs.getString(STATUS)));
        };
    }

    /**
     * Gets all pending.
     *
     * @return the all pending
     */
    public List<Order> getAllPending() {
        LOG.info("Trying execute " + bundle.getString("order.get.all.pending"));
        return getAll(bundle.getString("order.get.all.pending"),
                getMapper());
    }

    /**
     * Gets all reviewed.
     *
     * @return the all reviewed
     */
    public List<Order> getAllReviewed() {
        return getAll(bundle.getString("order.get.all.reviewed"),
                getMapper());
    }

    /**
     * Gets all reviewed pages.
     *
     * @return the all reviewed pages
     */
    public int getAllReviewedPages() {
        return count(bundle.getString("order.get.all.reviewed.count"), ps -> {
        }) / OBJECT_ON_PAGE + 1;
    }

    /**
     * Gets all pending pages.
     *
     * @return the all pending pages
     */
    public int getAllPendingPages() {
        return count(bundle.getString("order.get.all.pending.count"), ps -> {
        }) / OBJECT_ON_PAGE + 1;
    }

    /**
     * Gets all reviewed.
     *
     * @param currentPage the current page
     * @return the all reviewed
     */
    public List<Order> getAllReviewed(String currentPage) {
        int currentPageInt = currentPage != null ? Integer.parseInt(currentPage) : 1;
        currentPageInt = currentPageInt <= 0 ? 0 : (currentPageInt - 1) * OBJECT_ON_PAGE;
        int offset = currentPageInt;
        LOG.info("Trying execute " + bundle.getString("log.get.by.user.pages") +  " LIMIT: " + OBJECT_ON_PAGE + " OFFSET: " + offset);
        return getAllWithCondition(bundle.getString("order.get.all.reviewed.pages"), getMapper(), ps -> {
            ps.setInt(1, OBJECT_ON_PAGE);
            ps.setInt(2, offset);
        });
    }


    /**
     * Gets all pending.
     *
     * @param currentPage the current page
     * @return the all pending
     */
    public List<Order> getAllPending(String currentPage) {
        int currentPageInt = currentPage != null ? Integer.parseInt(currentPage) : 1;
        currentPageInt = currentPageInt <= 0 ? 0 : (currentPageInt - 1) * OBJECT_ON_PAGE;
        int offset = currentPageInt;
        LOG.info("Trying execute " + bundle.getString("log.get.by.user.pages") + " LIMIT: " + OBJECT_ON_PAGE + " OFFSET: " + offset);
        return getAllWithCondition(bundle.getString("order.get.all.pending.pages"), getMapper(), ps -> {
            ps.setInt(1, OBJECT_ON_PAGE);
            ps.setInt(2, offset);
        });
    }
}
