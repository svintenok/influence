package ru.kpfu.itis.group11501.influence.client;

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
    private static final int PORT = 3456;
    private static final String HOST = "localhost";
    private static BufferedInputStream bufferedInputStream;
    private static BufferedOutputStream bufferedOutputStream;

    public static void init() {
        try {
            Socket s = new Socket(HOST, PORT);
            bufferedInputStream = new BufferedInputStream(s.getInputStream());
            bufferedOutputStream = new BufferedOutputStream(s.getOutputStream());
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
}