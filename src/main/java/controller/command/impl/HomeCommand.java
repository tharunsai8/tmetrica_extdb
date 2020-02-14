package controller.command.impl;

import controller.command.Command;
import controller.constants.ViewPathConstant;
import controller.data.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Home command.
 */
public class HomeCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) throws IOException, ServletException {
        return new Page(ViewPathConstant.HOME);
    }
}
