package controller.command.impl.logs.actions;

import controller.command.Command;
import controller.constants.ViewPathConstant;
import controller.data.Page;
import controller.util.AuthUtils;
import model.domain.entity.User;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.ActivityLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Delete log command.
 */
public class DeleteLogCommand implements Command {
    /**
     * The Activity log service.
     */
    ActivityLogService activityLogService;

    /**
     * Instantiates a new Delete log command.
     */
    public DeleteLogCommand() {
        this.activityLogService = (ActivityLogService) ServiceFactory.getService(ServiceType.LOGS);
    }

    @Override
    public Page perform(HttpServletRequest request) throws IOException, ServletException {
        User user = AuthUtils.isAuthenticated(request);
        if (user == null) {
            return new Page(ViewPathConstant.LOGIN, true);
        }
        deleteLog(request);
        return new Page(ViewPathConstant.LOGS, true);
    }

    private void deleteLog(HttpServletRequest request) {
        long logId = Long.parseLong(request.getParameter("logId"));
        activityLogService.delete(logId);
    }
}
