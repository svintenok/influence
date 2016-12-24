package ru.kpfu.itis.group11501.influence.server;

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

    private final int PORT = 3450;

    public  Server() {
    }

    private void go() {

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {
                Socket socket1 = serverSocket.accept();
                System.out.println("First player connected");
                Player player1 = new Player(socket1);

                player1.getBufferedOutputStream().write(1);
                player1.getBufferedOutputStream().flush();

                Socket socket2 = serverSocket.accept();
                System.out.println("Second player connected");
                Player player2 = new Player(socket2);

                player2.getBufferedOutputStream().write(2);
                player2.getBufferedOutputStream().flush();
                player1.getBufferedOutputStream().write(2);
                player1.getBufferedOutputStream().flush();

                System.out.println("Room creating...");
                new Room(player1, player2);
                System.out.println("Room created");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        (new Server()).go();
    }
}