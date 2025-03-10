package runner;

import util.CommandManager;
import network.Connector;
import util.MyConsole;

import java.net.*;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UDPClient {
    private static final int PORT = 8080;
    public void run() {
        CommandManager commandManager = CommandManager.getInstance();
        try(DatagramSocket socket = new DatagramSocket()){
            Scanner sc = MyConsole.getScanner();
            InetAddress serverAddress = InetAddress.getLoopbackAddress();
            DatagramPacket sendPacket = new DatagramPacket(new byte[1024], 1024, serverAddress, PORT);
            DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
            Connector connector = new Connector(socket, sendPacket, receivePacket);

            while(true){
                try{
                    MyConsole.print("your command: ");
                    String[] line = sc.nextLine().split(" ");
                    String command = line[0];
                    String[] args = Arrays.copyOfRange(line, 1, line.length);
                    if (command.isEmpty()){continue;}
                    MyConsole.println("");
                    commandManager.executeCommand(command, connector, args);
                    MyConsole.println("");
                } catch (NoSuchElementException e){
                    throw new NoSuchElementException("\nYour stop the program. Good day:)");
                } catch (Exception e){
                    MyConsole.println(e.getMessage());
                }
            }

        } catch (Exception e){
            MyConsole.println(e.getMessage());
        }



    }
}
