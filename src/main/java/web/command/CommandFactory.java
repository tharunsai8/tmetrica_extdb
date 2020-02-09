package web.command;

import web.command.impl.HomeCommand;
import web.command.impl.LoginCommand;
import web.command.impl.NotFoundCommand;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

public class CommandFactory {
    private static Map<String, Command> getCommandMap = new HashMap<>();
    private static Map<String, Command> postCommandMap = new HashMap<>();
    private static Command notFoundCommand = new NotFoundCommand();

    static {

        getCommandMap.put("/", new HomeCommand());
        postCommandMap.put("/", new HomeCommand());
        getCommandMap.put("/login", new LoginCommand());
        postCommandMap.put("/login", new LoginCommand());
    }

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
