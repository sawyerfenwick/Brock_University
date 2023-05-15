import java.io.*;
import java.net.*;

/**
 * @author Sawyer Fenwick  | 6005011
 * @author Daniel Figueroa | 6276463
 * @version 1.0
 *
 * COSC 4P14-A1-Project Part I
 */
public class BattleshipServer {

    public static final int port = 4000;

    /**
     * This class defines a ClientHandler that is used to allow communication between two clients through a server
     */
    private static class ClientHandler extends Thread {
        private final Socket socket, peerSocket;
        private BufferedReader in;
        private PrintWriter out;

        /**
         * Constructor for Client Handler
         * Gets two sockets from Server
         *
         * @param socket first connected socket
         * @param peerSocket second connect socket
         */
        public ClientHandler(Socket socket, Socket peerSocket){
            this.socket = socket;
            this.peerSocket = peerSocket;
        }//ClientHandler - constructor

        /**
         * Server gets message from one Client and sends it to the other Client
         */
        @Override
        public void run(){
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(peerSocket.getOutputStream(), true);

                while(true){
                    String line = in.readLine();
                    if(line == null){
                        return;
                    }
                    out.println(line);
                }
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                try {
                    if(out != null){
                        out.println("You have been disconnected from the server.");
                        out.close();
                    }
                    if( in != null){
                        in.close();
                    }
                    socket.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }//run
    }//ClientHandler

    /**
     * Server waits for 2 client sockets to connect to it, then assigns them to a Thread to run in parallel
     *
     * @param args program parameters
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args){
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket previousSocket = null;
            while(true){
                Socket newSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(newSocket.getOutputStream(), true);
                if(previousSocket == null){
                    previousSocket = newSocket;
                    out.println("p1");  //tell p1 they are p1
                }
                else{
                    out.println("p2"); //tell p2 they are p2
                    PrintWriter pw = new PrintWriter(previousSocket.getOutputStream(), true);
                    pw.println("start");   //tell p1 that p2 has connected
                    new ClientHandler(previousSocket, newSocket).start();
                    new ClientHandler(newSocket, previousSocket).start();
                    previousSocket = null;
                }
            }
        } catch (IOException e){
            System.out.println("Server Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }//main
}//BattleshipServer
