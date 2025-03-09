package commands;

import interfaces.Command;
import interfaces.ValidatableArguments;
import network.Connector;
import util.MyConsole;
import util.RequestGenerator;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ClearCommand implements Command, ValidatableArguments {
    @Override
    public void execute(Connector connector, String[] args) throws IllegalArgumentException, SocketTimeoutException {
        if (!isValid(args)){
            throw new IllegalArgumentException("invalid arguments");
        }

        try{
            byte[] request = RequestGenerator.generateWithString("clear", "");
            connector.send(request);
            String answer = new String(connector.receive());
            MyConsole.println(answer);
        } catch (SocketTimeoutException e){
            throw e;
        } catch (Exception e){
            throw new IllegalArgumentException("Error in execute");
        }
    }

    @Override
    public boolean isValid(String[] args) {
        return args.length == 0;
    }

    @Override
    public String description(){
        return "clears the collection";
    }
}
