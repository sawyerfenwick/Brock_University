import java.io.*;
import java.net.*;
import java.util.Date;
 
public class Server {
 
    public static void main(String[] args) {
        // if (args.length < 1) { System.out.println( "Expected port number"); return; }
        // int port = Integer.parseInt(args[0]);
        int port = 4000;
 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            while (true) {
                System.out.println("Client hit me!");
        
                Socket socket = serverSocket.accept();
 
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
 
                writer.println(new Date().toString());
                output.close();
                socket.close();
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}