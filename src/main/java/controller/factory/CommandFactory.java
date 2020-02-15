package controller.factory;

import controller.command.Command;
import controller.command.impl.HomeCommand;
import controller.command.impl.activity.AvailableActivityCommand;
import controller.command.impl.activity.UserActivitiesCommand;
import controller.command.impl.auth.LoginCommand;
import controller.command.impl.auth.LogoutCommand;
import controller.command.impl.error.NotFoundCommand;
import controller.command.impl.logs.ActivityLogCommand;
import controller.command.impl.logs.actions.AddLogCommand;
import controller.command.impl.logs.actions.DeleteLogCommand;
import controller.command.impl.logs.actions.EditLogCommand;
import controller.command.impl.orders.PendingOrderCommand;
import controller.command.impl.orders.ReviewedOrderCommand;
import controller.command.impl.orders.actions.ApproveOrder;
import controller.command.impl.orders.actions.RejectOrder;
import controller.command.impl.orders.types.DeleteOrderCommand;
import controller.command.impl.orders.types.JoinOrderCommand;
import controller.command.impl.orders.types.NewOrderCommand;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

/**
 * The type Command factory.
 */
public class CommandFactory {
    private static Map<String, Command> getCommandMap = new HashMap<>();
    private static Map<String, Command> postCommandMap = new HashMap<>();
    private static Command notFoundCommand = new NotFoundCommand();

    static {

        getCommandMap.put("/", new HomeCommand());
        getCommandMap.put("/index", new HomeCommand());
        getCommandMap.put("/login", new LoginCommand());
        getCommandMap.put("/activities", new AvailableActivityCommand());
        getCommandMap.put("/myactivity", new UserActivitiesCommand());
        getCommandMap.put("/reviewedorders", new ReviewedOrderCommand());
        getCommandMap.put("/logs", new ActivityLogCommand());
        getCommandMap.put("/orders", new PendingOrderCommand());


        postCommandMap.put("/login", new LoginCommand());
        postCommandMap.put("/logout", new LogoutCommand());
        postCommandMap.put("/createactivity", new NewOrderCommand());
        postCommandMap.put("/join", new JoinOrderCommand());
        postCommandMap.put("/deleteactivity", new DeleteOrderCommand());
        postCommandMap.put("/accept", new ApproveOrder());
        postCommandMap.put("/reject", new RejectOrder());
        postCommandMap.put("/deletelog", new DeleteLogCommand());
        postCommandMap.put("/addlog", new AddLogCommand());
        postCommandMap.put("/editlog", new EditLogCommand());
    }

    /**
     * Gets command.
     *
     * @param path   the path
     * @param method the method
     * @return the command
     */
    public static Command getCommand(String path, String method) {
        return isGetMethod(method) ? getCommand(path) : postCommand(path);
    }

    private static Command postCommand(String path) {
        Command command = (postCommandMap.get(path));
        return nonNull(command) ? command : notFoundCommand;
    }

    private static boolean isGetMethod(String method) {
        return "GET".equals(method);
    }

    private static Command getCommand(String path) {
        Command command = (getCommandMap.get(path));
        return nonNull(command) ? command : notFoundCommand;
    }
}
