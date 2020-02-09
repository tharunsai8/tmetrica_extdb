package web;

import web.command.Command;
import web.command.CommandFactory;
import web.data.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * The type Dispatcher servlet.
 */
@WebServlet(value = "/app/*")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in do get");
        processRequest(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in do post");
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = getRelativePath(req);
        System.out.println(path);
        Command command = CommandFactory.getCommand(path, req.getMethod());
        Page page = command.perform(req);
        if (page.isRedirect()) {
            resp.sendRedirect(req.getContextPath() + page.getUrl());
        } else {
            req.getRequestDispatcher(page.getUrl()).forward(req, resp);
        }
    }

    private String getRelativePath(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        int lastPath = requestURI.lastIndexOf("/");
        return requestURI.substring(lastPath);
    }
}
