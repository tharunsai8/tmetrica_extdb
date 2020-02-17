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
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Available activity command.
 */
public class AvailableActivityCommand implements Command {
    private ActivityService activityService;
    private UserService userService;

    /**
     * Instantiates a new Available activity command.
     */
    public AvailableActivityCommand() {
        this.activityService = (ActivityService) ServiceFactory.getService(ServiceType.ACTIVITY);
        this.userService = (UserService) ServiceFactory.getService(ServiceType.USERS);
    }

    @Override
    public Page perform(HttpServletRequest request) {
        User user = AuthUtils.isAuthenticated(request);
        if (user == null) {
            return new Page(ViewPathConstant.LOGIN, true);
        }
        String page = request.getParameter("page");
        boolean isAdmin = AuthUtils.hasAuthority(request, Role.ADMIN);
        if (isAdmin) {
            request.setAttribute("users", userService.getAll());
        }
        request.setAttribute("activities", activityService.getActiveActivityByUser(user.getId(), page));
        request.setAttribute("currentPage", request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1);
        request.setAttribute("admin", isAdmin);
        request.setAttribute("usersList", false);
        request.setAttribute("pageCount", activityService.getActiveActivitiesPages(user.getId()));
        return new Page(ViewPathConstant.ACTIVITY);
    }

}
