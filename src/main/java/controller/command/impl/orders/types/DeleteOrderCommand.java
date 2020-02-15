package controller.command.impl.orders.types;

import controller.command.Command;
import controller.constants.ViewPathConstant;
import controller.data.Page;
import controller.util.AuthUtils;
import model.domain.entity.Order;
import model.domain.entity.User;
import model.domain.enums.Role;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Delete order command.
 */
public class DeleteOrderCommand implements Command {
    private OrderService orderService;

    /**
     * Instantiates a new Delete order command.
     */
    public DeleteOrderCommand() {
        this.orderService = (OrderService) ServiceFactory.getService(ServiceType.ORDERS);
    }

    @Override
    public Page perform(HttpServletRequest request) throws IOException, ServletException {
        User user = AuthUtils.isAuthenticated(request);
        if (user == null) {
            return new Page(ViewPathConstant.LOGIN, true);
        }

        return (createOrder(request, user.getEmail())) ? new Page(ViewPathConstant.ACTIVITY, true) : new Page(ViewPathConstant.ERROR_405, true);
    }

    private boolean createOrder(HttpServletRequest request, String email) {
        long activityId = Long.parseLong(request.getParameter("activityId"));
        Order order = orderService.createDeleteActivityOrder(email, activityId);
        if (order != null) {
            if (AuthUtils.hasAuthority(request, Role.ADMIN)) {
                orderService.approveOrder(order);
            }
            return true;
        }
        return false;
    }
}
