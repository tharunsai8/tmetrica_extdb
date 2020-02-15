package controller.command.impl;

import controller.command.Command;
import controller.constants.ViewPathConstant;
import controller.data.Page;
import controller.util.AuthUtils;
import model.domain.enums.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HomeCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) throws IOException, ServletException {
        request.setAttribute("admin", AuthUtils.hasAuthority(request, Role.ADMIN));
        return new Page(ViewPathConstant.HOME);
    }
}
