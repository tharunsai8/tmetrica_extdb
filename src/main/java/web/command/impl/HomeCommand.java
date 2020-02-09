package web.command.impl;

import web.command.Command;
import web.constants.ViewPathConstant;
import web.data.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HomeCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) throws IOException, ServletException {
        return new Page(ViewPathConstant.HOME);
    }
}
