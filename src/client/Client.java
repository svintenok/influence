package client;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author: Svintenok Kate
 * Date: 03.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Client {

    private static final int PORT = 3456;
    private static final String HOST = "localhost";


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        Socket s = new Socket(HOST, PORT);

        BufferedInputStream bufferedInputStream = new BufferedInputStream(s.getInputStream());
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(s.getOutputStream());

        byte[] cells = new byte[bufferedInputStream.read()];
        bufferedInputStream.read(cells);
        for (int i = 0; i < cells.length; i++)
            System.out.print(cells[i]  + " ");
        System.out.println();

        byte[] routesArraySize = new byte[2];
        bufferedInputStream.read(routesArraySize);
        byte[] routes = new byte[new BigInteger(routesArraySize).intValue()];
        bufferedInputStream.read(routes);
        for (int i = 0; i < routes.length; i++)
            System.out.print(routes[i]  + " ");
        System.out.println();

        int number = bufferedInputStream.read();
        if (number == 1){
            System.out.println("Ваш ход: ");
            int outAction = scanner.nextInt();
            bufferedOutputStream.write(outAction);
            bufferedOutputStream.flush();
        }


        while (true) {

            System.out.println("Ожидание хода противника... ");
            int inAction = bufferedInputStream.read();
            System.out.println("Ход противника: " + inAction);

            System.out.println("Ваш ход: ");
            int outAction = scanner.nextInt();
            bufferedOutputStream.write(outAction);
            bufferedOutputStream.flush();
        }
    }
}