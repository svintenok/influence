package ru.kpfu.itis.group11501.influence.client.util;

import ru.kpfu.itis.group11501.influence.client.models.GameMap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Author: Svintenok Kate
 * Date: 18.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Connection {
    private static final int PORT = 3450;
    private static final String HOST = "localhost";
    private static Socket socket;
    private static BufferedInputStream bufferedInputStream;
    private static BufferedOutputStream bufferedOutputStream;
    private static GameMap gameMap;

    public static void init() {
        try {
            socket = new Socket(HOST, PORT);
            bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connection set");
    }


    public static BufferedInputStream getBufferedInputStream() {
        return bufferedInputStream;
    }

    public static BufferedOutputStream getBufferedOutputStream() {
        return bufferedOutputStream;
    }

    public static GameMap getGameMap() {
        return gameMap;
    }

    public static void setGameMap(GameMap gameMap) {
        Connection.gameMap = gameMap;
    }
}