import java.io.*;
import java.net.*;

/**
 Sawyer Fenwick
 6005011
 COSC 4P14 Lab 2

 This class defines a UDPServer that returns the message sent from the UDPClient
 **/
class UDPServer {

    public static void main(String[] args) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(9876);
        RDT rdt = new RDT();
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while (true){
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            System.out.println(sentence + " from Client");
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
            receiveData = new byte[1024];
        }
    }
}
