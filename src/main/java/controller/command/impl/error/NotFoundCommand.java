package controller.command.impl.error;

import controller.command.Command;
import controller.constants.ViewPathConstant;
import controller.data.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Not found command.
 */
public class NotFoundCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) throws IOException, ServletException {
        return new Page(ViewPathConstant.NOT_FOUND);
    }
}
