package controller.command.impl.auth;

import org.apache.log4j.Logger;
import controller.command.Command;
import controller.constants.ViewPathConstant;
import controller.data.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * The type Logout command.
 */
public class LogoutCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

    @Override
    public Page perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
        LOG.debug("Invalidate session with ID: " + session.getId());

        session.invalidate();
        return new Page(ViewPathConstant.HOME, true);
    }
}
