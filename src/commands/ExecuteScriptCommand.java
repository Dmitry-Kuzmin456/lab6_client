package commands;

import interfaces.Command;
import interfaces.ValidatableArguments;
import network.Connector;
import util.Ask;
import util.CommandManager;
import util.MyConsole;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ExecuteScriptCommand implements Command, ValidatableArguments {
    private static Stack<String> scriptStack = new Stack<>();


    @Override
    public void execute(Connector connector, String[] args) throws IllegalArgumentException, SocketTimeoutException {
        if (!isValid(args)) {
            throw new IllegalArgumentException("invalid arguments, write correct filename");
        }
        String filename = args[0];
        Ask.setIsScript(true);
        Scanner oldScanner = MyConsole.getScanner();
        if (scriptStack.contains(filename)) {
            throw new IllegalArgumentException("script execute itself");
        }
        if (scriptStack.size() >= 500) {
            throw new IllegalArgumentException("script recursion limit");
        }
        scriptStack.push(filename);
        try{
            MyConsole.setScanner(new Scanner(new File(filename)));
        } catch (IOException e){
            throw new IllegalArgumentException("invalid arguments, write correct filename");
        }
        Scanner newScanner = MyConsole.getScanner();
        while(newScanner.hasNextLine()){
            try{
                String[] line = MyConsole.getScanner().nextLine().split(" ");
                String command = line[0];
                String[] arguments = Arrays.copyOfRange(line, 1, line.length);
                if (command.isEmpty()){continue;}
                CommandManager.getInstance().executeCommand(command, connector, arguments);
                MyConsole.println("");
            } catch (Exception e){
                MyConsole.println(e.getMessage() + "\n");
            } finally {
                MyConsole.setBlock(false);
            }
        }
        MyConsole.setScanner(oldScanner);
        Ask.setIsScript(false);
        scriptStack.pop();
    }

    @Override
    public boolean isValid(String[] args) {
        if (args.length != 1) {return false;}
        if (!(new File(args[0]).exists())) {return false;}
        return true;
    }

    @Override
    public String description(){
        return "executes scripts commands";
    }
}
