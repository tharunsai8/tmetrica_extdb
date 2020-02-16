package controller.command.impl.orders;

import controller.command.Command;
import controller.constants.ViewPathConstant;
import controller.data.Page;
import controller.util.AuthUtils;
import model.domain.entity.User;
import model.domain.enums.Role;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class ReviewedOrderCommand implements Command {
    private OrderService orderService;

    public ReviewedOrderCommand() {
        this.orderService = (OrderService) ServiceFactory.getService(ServiceType.ORDERS);
    }

    @Override
    public Page perform(HttpServletRequest request) {
        User user = AuthUtils.isAuthenticated(request);
        if (user == null) {
            return new Page(ViewPathConstant.LOGIN, true);
        } else if (!AuthUtils.hasAuthority(request, Role.ADMIN))
            return new Page(ViewPathConstant.ERROR_403);
        String page = request.getParameter("page");
        request.setAttribute("orders", orderService.getAllReviewed(page));
        request.setAttribute("pageCount", orderService.getAllReviewedPages());
        request.setAttribute("currentPage", request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1);
        request.setAttribute("active", false);
        request.setAttribute("admin", true);
        return new Page(ViewPathConstant.ORDERS);
    }
}
