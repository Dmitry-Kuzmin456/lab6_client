package commands;

import interfaces.Command;
import interfaces.ValidatableArguments;
import network.Connector;
import util.CommandManager;
import util.MyConsole;
import util.RequestGenerator;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class HelpCommand implements Command ,ValidatableArguments {
    @Override
    public void execute(Connector connector, String[] args) throws IllegalArgumentException, SocketTimeoutException {
        if (!isValid(args)){
            throw new IllegalArgumentException("Invalid arguments");
        }

        MyConsole.println(CommandManager.getInstance().getDescriptions());
    }

    @Override
    public boolean isValid(String[] args){
        return args.length == 0;
    }

    @Override
    public String description(){
        return "shows all available commands";
    }

}
