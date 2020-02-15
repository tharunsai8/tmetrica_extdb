package controller.command.impl.auth;

import controller.command.GetPostCommand;
import controller.constants.ViewPathConstant;
import controller.data.Page;
import controller.util.AuthUtils;
import model.domain.entity.User;
import model.domain.enums.Role;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;

public class RegistrationCommand extends GetPostCommand {
    private UserService userService;

    public RegistrationCommand() {
        userService = (UserService) ServiceFactory.getService(ServiceType.USERS);
    }

    @Override
    protected Page performGet(HttpServletRequest request) {
        return new Page(ViewPathConstant.REGISTRATION);
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();

        User user = AuthUtils.isAuthenticated(request);
        if (user != null) {
            session.setAttribute("user", user);
            return new Page(ViewPathConstant.HOME, true);
        }
        return (createUser(request)) ? new Page(ViewPathConstant.LOGIN, true) : new Page(ViewPathConstant.ERROR_500);
    }

    boolean createUser(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        User userToRegister = new User(1, email, password, name, Collections.singleton(Role.USER));
        return userService.register(userToRegister);
    }
}
