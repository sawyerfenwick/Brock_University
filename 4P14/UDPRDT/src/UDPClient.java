import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.*;
import java.util.zip.Adler32;

/**
 Sawyer Fenwick
 6005011
 COSC 4P14 Lab 2

 This class defines a UDPClient that pings a server 10 times and calculates the RTT. I have set the socket timeout to 1 second.
 **/
class UDPClient {

    public static void main(String[] args) throws Exception{

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        clientSocket.setSoTimeout(1000); //Set the timeout for our socket to 1 second, if nothing given in 1 second, execption thrown and caught
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String ping = "ping";
        Packet packet = new Packet(0,0,123,"a1");
        System.out.println("Pinging Server 10 Times...");
        for(int i = 0; i < 10; i++){
            Packet p = new Packet(0,0,123,"a1");
            System.out.println("TEST");
            System.out.println("TO STRING : " + p.toString());
            System.out.println("GET BYTES : " + p.toString().getBytes());

            sendData = p.toString().getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            try {

                clientSocket.send(sendPacket);	//send packet
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);	//get packet

            }
            catch (SocketTimeoutException e){
                System.out.println("Timeout on Packet " + i); //print timeout message if packet fails
            }
        }
        clientSocket.close();
    }
}
