package web.command;

import web.data.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * Perform page.
     *
     * @param request the request
     * @return the page
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    Page perform(HttpServletRequest request) throws IOException, ServletException;
}
