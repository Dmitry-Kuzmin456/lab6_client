package util;

import commands.*;
import interfaces.Command;
import network.Connector;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

public class CommandManager {
    private static CommandManager instance;
    private HashMap<String, Command> commands = new HashMap<>();

    private CommandManager() {
        commands.put("show", new ShowCommand());
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("insert", new InsertCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("update", new UpdateCommand());
        commands.put("remove_key", new RemoveKeyCommand());
        commands.put("clear", new ClearCommand());
        commands.put("exit", new ExitCommand());
        commands.put("history", new HistoryCommand());
        commands.put("replace_if_greater", new ReplaceIfGreaterCommand());
        commands.put("remove_greater_key", new RemoveGreaterKeyCommand());
        commands.put("remove_all_by_government", new RemoveAllByGovernmentCommand());
        commands.put("filter_less_than_meters_above_sea_level", new FilterLessThanMetersAboveSeaLevelCommand());
        commands.put("print_field_descending_meters_above_sea_level", new PrintFieldDescendingMetersAboveSeaLevelCommand());
    }

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    public void executeCommand(String name, Connector connector, String[] args) throws IllegalArgumentException, SocketTimeoutException {
        if (!commands.containsKey(name)) {
            throw new IllegalArgumentException("no such command");
        }
        Command command = commands.get(name);
        command.execute(connector, args);
    }

    public String getDescriptions(){
        StringBuilder res = new StringBuilder();
        for (String k: commands.keySet()){
            res.append(k).append(": ").append(commands.get(k).description()).append("\n");
        }
        return res.toString();
    }

}
