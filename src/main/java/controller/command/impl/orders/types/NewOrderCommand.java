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
 * The type New order command.
 */
public class NewOrderCommand implements Command {
    private OrderService orderService;


    /**
     * Instantiates a new New order command.
     */
    public NewOrderCommand() {
        this.orderService = (OrderService) ServiceFactory.getService(ServiceType.ORDERS);
    }

    @Override
    public Page perform(HttpServletRequest request) throws IOException, ServletException {
        User user = AuthUtils.isAuthenticated(request);
        if (user == null) {
            return new Page(ViewPathConstant.LOGIN, true);
        }

        return (createOrder(request, user.getEmail())) ? new Page(ViewPathConstant.ACTIVITY, true) : new Page(ViewPathConstant.ERROR_500);
    }

    private boolean createOrder(HttpServletRequest request, String email) {
        Order order = orderService.createNewActivityOrder(request.getParameter("activityName"), email);
        if (order != null) {

            if (AuthUtils.hasAuthority(request, Role.ADMIN)) {
                orderService.approveOrder(order);
            }
            return true;
        }
        return false;
    }

}
