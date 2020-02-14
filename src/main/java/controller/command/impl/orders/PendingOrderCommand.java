package controller.command.impl.orders;

import controller.command.Command;
import controller.constants.ViewPathConstant;
import controller.data.Page;
import controller.util.AuthUtils;
import model.domain.enums.Role;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Pending order command.
 */
public class PendingOrderCommand implements Command {
    private OrderService orderService;

    /**
     * Instantiates a new Pending order command.
     */
    public PendingOrderCommand() {
        this.orderService = (OrderService) ServiceFactory.getService(ServiceType.ORDERS);
    }

    @Override
    public Page perform(HttpServletRequest request) {
        if (AuthUtils.isAuthenticated(request) == null) {
            return new Page(ViewPathConstant.LOGIN, true);
        } else if (!AuthUtils.hasAuthority(request, Role.ADMIN))
            return new Page(ViewPathConstant.ERROR_403, true);
        request.setAttribute("orders", orderService.getAllPending());
        request.setAttribute("active", true);
        request.setAttribute("admin", true);
        return new Page(ViewPathConstant.ORDERS);
    }
}
