package commands;

import interfaces.Command;
import interfaces.ValidatableArguments;
import network.Connector;
import serialize.DeserializeCity;
import util.MyConsole;
import util.RequestGenerator;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;

public class ShowCommand implements Command, ValidatableArguments {
    @Override
    public void execute(Connector connector, String[] args) throws IllegalArgumentException, SocketTimeoutException {
        if (!isValid(args)){
            throw new IllegalArgumentException("Invalid arguments");
        }

        try{
            byte[] request = RequestGenerator.generateWithString("show", "");
            connector.send(request);
            int k = ByteBuffer.wrap(connector.receive()).getInt();
            for (int i = 0; i < k; i++) {
                MyConsole.println(DeserializeCity.deserialize(connector.receive()) + "\n");
            }
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
        return "shows all cities in collection";
    }

}
