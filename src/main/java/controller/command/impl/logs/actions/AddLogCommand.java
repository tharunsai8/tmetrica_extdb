package controller.command.impl.logs.actions;

import controller.command.Command;
import controller.constants.ViewPathConstant;
import controller.data.Page;
import controller.util.AuthUtils;
import model.domain.entity.Activity;
import model.domain.entity.ActivityLog;
import model.domain.entity.User;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.ActivityLogService;
import model.service.ActivityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddLogCommand implements Command {
    ActivityLogService activityLogService;
    ActivityService activityService;

    public AddLogCommand() {
        this.activityLogService = (ActivityLogService) ServiceFactory.getService(ServiceType.LOGS);
        this.activityService = (ActivityService) ServiceFactory.getService(ServiceType.ACTIVITY);
    }

    @Override
    public Page perform(HttpServletRequest request) throws IOException, ServletException {
        User user = AuthUtils.isAuthenticated(request);
        if (user == null) {
            return new Page(ViewPathConstant.LOGIN, true);
        }
        return (addLog(request, user)) ? new Page(ViewPathConstant.LOGS, true) : new Page(ViewPathConstant.ERROR_500);
    }

    private boolean addLog(HttpServletRequest request, User user) {
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        long activityId = Long.parseLong(request.getParameter("activityId"));
        Activity activity = activityService.getById(activityId);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        ActivityLog log = null;
        try {
            log = new ActivityLog(1, activity, user, formatter.parse(startDateStr), formatter.parse(endDateStr));
        } catch (ParseException e) {
            return false;
        }
        return activityLogService.create(log);
    }
}
