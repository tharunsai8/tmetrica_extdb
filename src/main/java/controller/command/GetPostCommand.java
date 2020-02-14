package controller.command;

import controller.data.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Get post command.
 */
public abstract class GetPostCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) throws IOException, ServletException {
        String method = request.getMethod();
        return "GET".equals(method)
                ? performGet(request)
                : performPost(request);
    }

    /**
     * Perform get page.
     *
     * @param request the request
     * @return the page
     */
    protected abstract Page performGet(HttpServletRequest request);

    /**
     * Perform post page.
     *
     * @param request the request
     * @return the page
     */
    protected abstract Page performPost(HttpServletRequest request);

}
