import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Socket s = new Socket(HOST, PORT);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter printWriter = new PrintWriter(s.getOutputStream(), true);

        String number = bufferedReader.readLine();
        if (number.equals("1")){
            System.out.println("Ваш ход: ");
            String outAction = scanner.nextLine();
            printWriter.println(outAction);
        }


        while (true) {

            System.out.println("Ожидание хода противника... ");
            String inAction = bufferedReader.readLine();
            System.out.println("Ход противника: " + inAction);

            System.out.println("Ваш ход: ");
            String outAction = scanner.nextLine();
            printWriter.println(outAction);
        }
    }
}