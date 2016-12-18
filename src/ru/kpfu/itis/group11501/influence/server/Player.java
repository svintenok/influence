package ru.kpfu.itis.group11501.influence.server;

import java.io.*;
import java.net.Socket;

/**
 * Author: Svintenok Kate
 * Date: 03.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Player{
    private Socket socket;
    private BufferedInputStream bufferedInputStream;
    private BufferedOutputStream bufferedOutputStream;

    public Player(Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        this.bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
    }

    public BufferedInputStream getBufferedInputStream() {
        return bufferedInputStream;
    }

    public BufferedOutputStream getBufferedOutputStream() {
        return bufferedOutputStream;
    }

    public Socket getSocket() {
        return socket;
    }
}