package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;

public class Connector {
    private DatagramSocket socket;
    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;

    public Connector(DatagramSocket socket, DatagramPacket sendPacket, DatagramPacket receivePacket) throws SocketException {
        this.socket = socket;
        socket.setSoTimeout(2000);
        this.sendPacket = sendPacket;
        this.receivePacket = receivePacket;
    }

    public void send(byte[] data) throws IOException {
        sendPacket.setData(data);
        socket.send(sendPacket);
    }

    public byte[] receive() throws IOException {
        try{
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e){
            throw new SocketTimeoutException("server error");
        }

        return Arrays.copyOfRange(receivePacket.getData(), 0, receivePacket.getLength());
    }
}
