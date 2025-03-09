package runner;

import util.CommandManager;
import network.Connector;
import util.MyConsole;

import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

public class UDPClient {
    private static final int PORT = 8080;
    public void run() {
        CommandManager commandManager = CommandManager.getInstance();
        try(DatagramSocket socket = new DatagramSocket()){
            Scanner sc = MyConsole.getScanner();
            InetAddress serverAddress = InetAddress.getLoopbackAddress();
            int port = 8080;
            DatagramPacket sendPacket = new DatagramPacket(new byte[1024], 1024, serverAddress, port);
            DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
            Connector connector = new Connector(socket, sendPacket, receivePacket);

            while(true){
                try{
                    String[] line = sc.nextLine().split(" ");
                    String command = line[0];
                    String[] args = Arrays.copyOfRange(line, 1, line.length);
                    if (command.isEmpty()){continue;}
                    commandManager.executeCommand(command, connector, args);
                    MyConsole.println("");
                } catch (Exception e){
                    MyConsole.println(e.getMessage());
                }
            }

        } catch (Exception e){
            MyConsole.println("Error in UDPClient");
        }



    }
}
