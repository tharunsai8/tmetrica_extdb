package controller.command.impl.orders.actions;

import controller.command.Command;
import controller.constants.ViewPathConstant;
import controller.data.Page;
import controller.util.AuthUtils;
import model.domain.entity.User;
import model.domain.enums.Role;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Reject order.
 */
public class RejectOrder implements Command {
    private OrderService orderService;


    /**
     * Instantiates a new Reject order.
     */
    public RejectOrder() {
        this.orderService = (OrderService) ServiceFactory.getService(ServiceType.ORDERS);
    }

    @Override
    public Page perform(HttpServletRequest request) throws IOException, ServletException {
        User user = AuthUtils.isAuthenticated(request);
        if (user == null) {
            return new Page(ViewPathConstant.LOGIN, true);
        } else if (!AuthUtils.hasAuthority(request, Role.ADMIN))
            return new Page(ViewPathConstant.ERROR_403);
        reject(request);
        return new Page(ViewPathConstant.ORDERS, true);
    }

    private void reject(HttpServletRequest request) {
        long orderId = Long.parseLong(request.getParameter("orderId"));
        orderService.rejectOrder(orderService.getById(orderId));
    }
}
