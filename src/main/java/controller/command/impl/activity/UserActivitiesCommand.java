package controller.command.impl.activity;

import controller.command.Command;
import controller.constants.ViewPathConstant;
import controller.data.Page;
import controller.util.AuthUtils;
import model.domain.entity.User;
import model.domain.enums.Role;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.ActivityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserActivitiesCommand implements Command {
    private ActivityService activityService;

    public UserActivitiesCommand() {
        this.activityService = (ActivityService) ServiceFactory.getService(ServiceType.ACTIVITY);
    }

    @Override
    public Page perform(HttpServletRequest request) throws IOException, ServletException {
        User user = AuthUtils.isAuthenticated(request);
        if (user == null) {
            return new Page(ViewPathConstant.LOGIN, true);
        }
        String page = request.getParameter("page");
        boolean isAdmin = AuthUtils.hasAuthority(request, Role.ADMIN);
        request.setAttribute("activities", activityService.getAllActivityByUser(user.getId(), page));
        request.setAttribute("admin", isAdmin);
        request.setAttribute("currentPage", request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1);
        request.setAttribute("usersList", true);
        request.setAttribute("pageCount", activityService.getUserActivitiesPages(user.getId()));
        return new Page(ViewPathConstant.ACTIVITY);
    }
}
