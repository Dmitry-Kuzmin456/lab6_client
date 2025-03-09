package commands;

import interfaces.Command;
import interfaces.ValidatableArguments;
import model.City;
import model.Government;
import network.Connector;
import util.Ask;
import util.MyConsole;
import util.RequestGenerator;

import java.net.SocketException;
import java.net.SocketTimeoutException;

public class RemoveAllByGovernmentCommand implements Command, ValidatableArguments {

    @Override
    public void execute(Connector connector, String[] args) throws IllegalArgumentException, SocketTimeoutException {
        if (!isValid(args)) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        try{
            Government government = Ask.askGovernment(MyConsole.getScanner());
            byte[] request = RequestGenerator.generateWithString("remove_all_by_government", government.getDescription());
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
        return args.length == 0;
    }

    @Override
    public String description() {
        return "remove all elements which governments equals to the given";
    }

}
