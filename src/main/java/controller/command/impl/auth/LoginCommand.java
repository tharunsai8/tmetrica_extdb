package controller.command.impl.auth;

import controller.command.GetPostCommand;
import controller.constants.AttributeName;
import controller.constants.ViewPathConstant;
import controller.data.Page;
import model.domain.entity.User;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand extends GetPostCommand {
    private UserService userService;

    public LoginCommand() {
        userService = (UserService) ServiceFactory.getService(ServiceType.USERS);
    }

    @Override
    protected Page performGet(HttpServletRequest request) {
        return new Page(ViewPathConstant.LOGIN);
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.validateUser(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            return new Page(ViewPathConstant.HOME, true);
        }
        return failedLogin(request);
    }

    private Page failedLogin(HttpServletRequest request) {
        request.setAttribute(AttributeName.LOGIN_ERROR, true);
        return new Page(ViewPathConstant.LOGIN, true);
    }

}
