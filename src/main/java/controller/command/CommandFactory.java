package controller.command;

import controller.command.impl.HomeCommand;
import controller.command.impl.activity.AvailableActivityCommand;
import controller.command.impl.activity.UserActivitiesCommand;
import controller.command.impl.auth.LoginCommand;
import controller.command.impl.auth.LogoutCommand;
import controller.command.impl.error.NotFoundCommand;
import controller.command.impl.orders.DeleteOrderCommand;
import controller.command.impl.orders.JoinOrderCommand;
import controller.command.impl.orders.NewOrderCommand;

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
        postCommandMap.put("/login", new LoginCommand());
        getCommandMap.put("/activities", new AvailableActivityCommand());
        postCommandMap.put("/logout", new LogoutCommand());
        postCommandMap.put("/createactivity", new NewOrderCommand());
        postCommandMap.put("/join", new JoinOrderCommand());
        getCommandMap.put("/myactivity", new UserActivitiesCommand());
        postCommandMap.put("/deleteactivity", new DeleteOrderCommand());
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
