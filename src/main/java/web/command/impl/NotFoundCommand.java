package web.command.impl;

import web.command.Command;
import web.constants.ViewPathConstant;
import web.data.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class NotFoundCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) throws IOException, ServletException {
        return new Page(ViewPathConstant.NOT_FOUND);
    }
}
