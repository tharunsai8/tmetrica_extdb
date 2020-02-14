package controller.command.impl.activity;

import model.domain.entity.User;
import model.domain.enums.Role;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.ActivityService;
import controller.command.Command;
import controller.constants.ViewPathConstant;
import controller.data.Page;
import controller.util.AuthUtils;

import javax.servlet.http.HttpServletRequest;

public class AvailableActivityCommand implements Command {
    private ActivityService activityService;

    public AvailableActivityCommand() {
        this.activityService = (ActivityService) ServiceFactory.getService(ServiceType.ACTIVITY);
    }

    @Override
    public Page perform(HttpServletRequest request) {
        User user = AuthUtils.isAuthenticated(request);
        if (user == null) {
            return new Page(ViewPathConstant.LOGIN, true);
        }
        boolean isAdmin = AuthUtils.hasAuthority(request, Role.ADMIN);
        request.setAttribute("activities", activityService.getActiveActivityByUser(user.getId()));
        request.setAttribute("admin", isAdmin);
        request.setAttribute("usersList", false);
        return new Page(ViewPathConstant.ACTIVITY);
    }

}
