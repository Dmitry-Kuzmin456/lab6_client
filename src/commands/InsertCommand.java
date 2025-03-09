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
import java.nio.charset.StandardCharsets;

public class InsertCommand implements Command, ValidatableArguments {

    @Override
    public void execute(Connector connector, String[] args) throws IllegalArgumentException, SocketTimeoutException {
        if (!isValid(args)) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        try{
            City city = Ask.ask(MyConsole.getScanner());
            city.setID(Integer.parseInt(args[0]));
            byte[] request = RequestGenerator.generateWithCity("insert", city);
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
        return "insert element by id";
    }

}
