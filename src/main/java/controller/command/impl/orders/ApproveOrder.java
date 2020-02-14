package controller.command.impl.orders;

import controller.command.Command;
import controller.data.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ApproveOrder implements Command {
    @Override
    public Page perform(HttpServletRequest request) throws IOException, ServletException {
        return null;
    }
}
