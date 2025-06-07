
package the_hangout_society;

import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;


class ClientHandler implements Runnable {
       
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            
            try (ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                    PrintWriter printWriter = new PrintWriter(new FileWriter("users.txt", true), true)) {
                User user = (User) objectInputStream.readObject();
                synchronized (printWriter) {
                    printWriter.println(user.getName() + ", " + user.getCity() + ", " + user.getFilter1Choice() + ", " + user.getFilter2Choice()+ ", " + user.getChoose_an_activity_or_random() + ", " + user.getName_the_activity());
                }
                System.out.println("Your information has been saved");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }