package commands;

import interfaces.Command;
import interfaces.ValidatableArguments;
import model.City;
import network.Connector;
import util.Ask;
import util.MyConsole;
import util.RequestGenerator;

import java.net.SocketException;
import java.net.SocketTimeoutException;

public class RemoveKeyCommand implements Command, ValidatableArguments {
    @Override
    public void execute(Connector connector, String[] args) throws IllegalArgumentException, SocketTimeoutException {
        if (!isValid(args)) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        try{
            byte[] request = RequestGenerator.generateWithString("remove_key", args[0]);
            connector.send(request);
            String answer = new String(connector.receive());
            MyConsole.println(answer);
        } catch (SocketTimeoutException e){
            throw e;
        } catch (Exception e){
            MyConsole.println("Error in execute");
        }

    }

    @Override
    public boolean isValid(String[] args){
        try{
            Long.parseLong(args[0]);
        } catch (Exception e){
            return false;
        }
        return args.length == 1;
    }

    @Override
    public String description() {
        return "remove element by id";
    }


}
