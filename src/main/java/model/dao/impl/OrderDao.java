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

import java.util.List;
import java.util.ResourceBundle;

public class OrderDao extends AbstractJDBCDao<Order> {
    private final String ID = "id";
    private final String ACTION = "action";
    private final String STATUS = "status";
    private static final String USER_ID = "user_id";
    private static final String ACTIVITY_ID = "activity_id";
    private static ResourceBundle bundle = ResourceBundle.getBundle("database/queries");
    private ActivityService activityService;
    private UserService userService;

    public OrderDao() {
        activityService = (ActivityService) ServiceFactory.getService(ServiceType.ACTIVITY);
        userService = (UserService) ServiceFactory.getService(ServiceType.USERS);
    }

    @Override
    public Order getById(long id) {
        return getById(bundle.getString("order.get.id"),
                ps -> ps.setLong(1, id),
                getMapper()
        );
    }

    @Override
    public List<Order> getAll() {
        return getAll(bundle.getString("order.get.all"),
                getMapper());
    }

    @Override
    public boolean create(Order entity) {
        return createUpdate(bundle.getString("order.create"),
                ps -> {
                    ps.setObject(1, entity.getAction());
                    ps.setLong(2, entity.getActivity().getId());
                    ps.setLong(3, entity.getUser().getId());
                    ps.setObject(4, entity.getStatus());
                });
    }

    public Order createAndReturn(Order entity) {
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
        return createUpdate(bundle.getString("order.update"),
                ps -> {
                    ps.setObject(1, entity.getStatus());
                    ps.setLong(2, entity.getId());
                });
    }

    @Override
    public boolean remove(Order entity) {
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

    public List<Order> getAllPending() {
        return getAll(bundle.getString("order.get.all.pendind"),
                getMapper());
    }

    public List<Order> getAllReviewed() {
        return getAll(bundle.getString("order.get.all.reviewed"),
                getMapper());
    }
}
