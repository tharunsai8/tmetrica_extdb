package web.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class StaticResourceFilter implements Filter {
    private static final String RESOURCES_PATH = "/resources/";
    private static final String VIEW_PATH = "/view/";
    private static final String APP_PATH = "/app";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String referencePath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (shouldBeSkipped(referencePath)) {
            chain.doFilter(request, response);
            return;
        } else {
            String pathToForward = APP_PATH + referencePath;
            httpRequest.getRequestDispatcher(pathToForward).forward(request, response);
        }
        return;


    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    private boolean shouldBeSkipped(String path) {
        return path.startsWith(RESOURCES_PATH) || path.startsWith(VIEW_PATH) || path.startsWith(APP_PATH);
    }

}
