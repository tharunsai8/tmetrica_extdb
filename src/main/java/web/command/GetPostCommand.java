package web.command;

import web.data.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public abstract class GetPostCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) throws IOException, ServletException {
        String method = request.getMethod();
        return "GET".equals(method)
                ? performGet(request)
                : performPost(request);
    }

    protected abstract Page performGet(HttpServletRequest request);

    protected abstract Page performPost(HttpServletRequest request);

}
