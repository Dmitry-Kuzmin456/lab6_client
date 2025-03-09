package interfaces;

import network.Connector;

import java.net.SocketException;
import java.net.SocketTimeoutException;

public interface Command {
    void execute(Connector connector, String[] args) throws IllegalArgumentException, SocketTimeoutException;
    String description();
}
