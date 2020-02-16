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

public class PendingOrderCommand implements Command {
    private OrderService orderService;

    public PendingOrderCommand() {
        this.orderService = (OrderService) ServiceFactory.getService(ServiceType.ORDERS);
    }

    @Override
    public Page perform(HttpServletRequest request) {
        if (AuthUtils.isAuthenticated(request) == null) {
            return new Page(ViewPathConstant.LOGIN, true);
        } else if (!AuthUtils.hasAuthority(request, Role.ADMIN))
            return new Page(ViewPathConstant.ERROR_403);
        String page = request.getParameter("page");
        request.setAttribute("orders", orderService.getAllPending(page));
        request.setAttribute("active", true);
        request.setAttribute("pageCount", orderService.getAllPendingPages());
        request.setAttribute("currentPage", request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1);
        request.setAttribute("admin", true);
        return new Page(ViewPathConstant.ORDERS);
    }
}
