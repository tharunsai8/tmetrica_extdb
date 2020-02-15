package controller.command.impl;

import controller.command.Command;
import controller.constants.ViewPathConstant;
import controller.data.Page;
import controller.util.AuthUtils;
import model.domain.entity.User;
import model.domain.enums.Role;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.StatisticService;

import javax.servlet.http.HttpServletRequest;

public class StatisticCommand implements Command {
    private StatisticService statisticService;

    public StatisticCommand() {
        this.statisticService = (StatisticService) ServiceFactory.getService(ServiceType.STATS);
    }

    @Override
    public Page perform(HttpServletRequest request) {
        User user = AuthUtils.isAuthenticated(request);
        if (user == null) {
            return new Page(ViewPathConstant.LOGIN, true);
        }
        boolean isAdmin = AuthUtils.hasAuthority(request, Role.ADMIN);
        request.setAttribute("stats", statisticService.getStatisticByUser(user.getId()));
        request.setAttribute("admin", isAdmin);
        return new Page(ViewPathConstant.STATISTIC);
    }
}
