
package the_hangout_society;

import java.io.IOException;
import java.net.*;



public class Server {  public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler ClientHandler = new ClientHandler(clientSocket);
                 System.out.println("Client connect via: " + clientSocket.getInetAddress().getHostAddress());
                 
                 Thread thread = new Thread(ClientHandler);

                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Exception " + e);
        }
    }

    
}
