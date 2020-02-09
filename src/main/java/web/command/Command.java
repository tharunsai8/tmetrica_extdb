package web.command;

import web.data.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Command {
    Page perform(HttpServletRequest request) throws IOException, ServletException;
}
