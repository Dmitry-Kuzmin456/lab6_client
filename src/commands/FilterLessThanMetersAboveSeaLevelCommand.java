package commands;

import interfaces.Command;
import interfaces.ValidatableArguments;
import model.Government;
import network.Connector;
import serialize.DeserializeCity;
import util.Ask;
import util.MyConsole;
import util.RequestGenerator;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;

public class FilterLessThanMetersAboveSeaLevelCommand implements Command, ValidatableArguments {

    @Override
    public void execute(Connector connector, String[] args) throws IllegalArgumentException, SocketTimeoutException {
        if (!isValid(args)) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        try{
            byte[] request = RequestGenerator.generateWithString("filter_less_than_meters_above_sea_level", args[0]);
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
        try{
            Float.parseFloat(args[0]);
        } catch (Exception e){
            return false;
        }
        return args.length == 1;
    }

    @Override
    public String description() {
        return "show all elements which meters above sea level less than the given";
    }

}
