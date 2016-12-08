import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author: Svintenok Kate
 * Date: 03.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Server {

    private final int PORT = 3456;

    public  Server() {}

    private void go() throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);

        while (true) {
            Socket socket1 = serverSocket.accept();
            Socket socket2 = serverSocket.accept();
            new Room(socket1, socket2);
        }

    }

    public static void main(String[] args) throws IOException {
        (new Server()).go();
    }
}