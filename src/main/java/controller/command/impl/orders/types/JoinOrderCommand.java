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
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Join order command.
 */
public class JoinOrderCommand implements Command {
    private OrderService orderService;
    private UserService userService;


    /**
     * Instantiates a new Join order command.
     */
    public JoinOrderCommand() {
        this.orderService = (OrderService) ServiceFactory.getService(ServiceType.ORDERS);
        this.userService = (UserService) ServiceFactory.getService(ServiceType.USERS);
    }

    @Override
    public Page perform(HttpServletRequest request) throws IOException, ServletException {
        User user = AuthUtils.isAuthenticated(request);
        if (user == null) {
            return new Page(ViewPathConstant.LOGIN, true);
        }

        return (createOrder(request, user.getEmail()) ? new Page(ViewPathConstant.ACTIVITY, true) : new Page(ViewPathConstant.ERROR_500));
    }

    private boolean createOrder(HttpServletRequest request, String email) {
        long activityId = Long.parseLong(request.getParameter("activityId"));
        String userIdStr = request.getParameter("userId");
        if (userIdStr != null) {
            long userId = Long.parseLong(request.getParameter("userId"));
            email = userService.getById(userId).getEmail();
        }
        Order order = orderService.createJoinToActivityOrder(email, activityId);
        if (order != null) {
            if (AuthUtils.hasAuthority(request, Role.ADMIN)) {
                orderService.approveOrder(order);
            }
            return true;
        }
        return false;
    }
}
