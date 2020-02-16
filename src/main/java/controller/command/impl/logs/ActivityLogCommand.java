package controller.command.impl.logs;

import controller.command.Command;
import controller.constants.ViewPathConstant;
import controller.data.Page;
import controller.util.AuthUtils;
import model.domain.entity.ActivityLog;
import model.domain.entity.User;
import model.domain.enums.Role;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.ActivityLogService;
import model.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ActivityLogCommand implements Command {
    private ActivityLogService activityLogService;
    private ActivityService activityService;

    public ActivityLogCommand() {
        this.activityLogService = (ActivityLogService) ServiceFactory.getService(ServiceType.LOGS);
        this.activityService = (ActivityService) ServiceFactory.getService(ServiceType.ACTIVITY);
    }

    @Override
    public Page perform(HttpServletRequest request) {
        User user = AuthUtils.isAuthenticated(request);
        if (user == null) {
            return new Page(ViewPathConstant.LOGIN, true);
        }
        String page = request.getParameter("page");
        boolean isAdmin = AuthUtils.hasAuthority(request, Role.ADMIN);

        request.setAttribute("logs", getLogs(request, user, page));
        request.setAttribute("activities", activityService.getAllActivityByUser(user.getId()));
        request.setAttribute("currentPage", request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1);
        request.setAttribute("pageCount", countPages(request, user));
        request.setAttribute("admin", isAdmin);
        return new Page(ViewPathConstant.LOGS);
    }

    private List<ActivityLog> getLogs(HttpServletRequest request, User user, String currentPage) {
        String activityId = request.getParameter("activityId");
        return (activityId == null) ? activityLogService.getAllByUser(user.getId(), currentPage)
                : activityLogService.getAllByUserAndActivity(user.getId(), Long.parseLong(activityId), currentPage);
    }

    private int countPages(HttpServletRequest request, User user) {
        String activityId = request.getParameter("activityId");
        return (activityId == null) ? activityLogService.getAllByUserPages(user.getId())
                : activityLogService.getAllByUserAndActivityPages(user.getId(), Long.parseLong(activityId));
    }

}
