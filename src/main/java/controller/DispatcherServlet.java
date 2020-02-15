package controller;

import controller.command.Command;
import controller.data.Page;
import controller.factory.CommandFactory;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/app/*")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = getRelativePath(req);
        Command command = CommandFactory.getCommand(path, req.getMethod());
        Page page = command.perform(req);
        if (page.isRedirect()) {
            resp.sendRedirect(getRedirectUrl(req, page));
        } else {
            req.getRequestDispatcher(page.getUrl()).forward(req, resp);
        }
    }

    private String getRelativePath(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        int lastPath = requestURI.lastIndexOf("/");
        return requestURI.substring(lastPath);
    }

    private String getRedirectUrl(HttpServletRequest request, Page page) {
        String withoutExt = FilenameUtils.removeExtension(page.getUrl());
        int lastPath = withoutExt.lastIndexOf("/");
        return request.getContextPath() + withoutExt.substring(lastPath);
    }

}
