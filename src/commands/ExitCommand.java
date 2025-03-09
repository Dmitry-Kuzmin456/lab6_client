package commands;

import interfaces.Command;
import interfaces.ValidatableArguments;
import network.Connector;

import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ExitCommand implements Command, ValidatableArguments {
    @Override
    public void execute(Connector connector, String[] args) throws IllegalArgumentException, SocketTimeoutException {
        if (!isValid(args)){
            throw new IllegalArgumentException("invalid arguments");
        }
        System.exit(0);
    }

    @Override
    public boolean isValid(String[] args) {
        return args.length == 0;
    }

    @Override
    public String description(){
        return "stop the client application";
    }
}
