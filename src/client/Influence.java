package client;

import client.models.Cell;
import client.models.GameMap;

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
public class Influence {

    private static final int PORT = 3456;
    private static final String HOST = "localhost";
    private BufferedInputStream bufferedInputStream;
    private BufferedOutputStream bufferedOutputStream;
    private Scanner scanner;
    private GameMap gameMap;
    private int orderNumber;

    public Influence() {
        try {
            Socket s = new Socket(HOST, PORT);
            this.bufferedInputStream = new BufferedInputStream(s.getInputStream());
            this.bufferedOutputStream = new BufferedOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.scanner = new Scanner(System.in);
    }

    public void startInfluence() throws IOException {
        readMap();
        orderNumber = bufferedInputStream.read();
        readStartingCells();

        if (orderNumber == 1){

            givePowers();

            /*
            System.out.println("Ваш ход: ");
            int outAction = scanner.nextInt();
            bufferedOutputStream.write(outAction);
            bufferedOutputStream.flush();
            */
        }


        while (true) {

            getEnemyMove();
            givePowers();

            /*
            System.out.println("Ожидание хода противника... ");
            int inAction = bufferedInputStream.read();
            System.out.println("Ход противника: " + inAction);

            System.out.println("Ваш ход: ");
            int outAction = scanner.nextInt();
            bufferedOutputStream.write(outAction);
            bufferedOutputStream.flush();
            */
        }

    }

    private void getEnemyMove() throws IOException {
        int count = bufferedInputStream.read();
        for (int i = 0; i < count; i++) {
            Cell cell = gameMap.getCell(bufferedInputStream.read());
            cell.setPower(cell.getPower() + 1);
            System.out.println("Противник дал силу ячейке " + cell.getNumber());
        }
        gameMap.printGameMap("power");
    }

    private void givePowers() throws IOException {
        gameMap.printGameMap("number");
        System.out.println("Раздача сил ячейкам");
        bufferedOutputStream.write(gameMap.getCellsCountByType(orderNumber));
        bufferedOutputStream.flush();

        for(int i = 0; i < gameMap.getCellsCountByType(orderNumber); i++){
            System.out.println("Введите номер ячейки: ");
            Cell cell = gameMap.getCell(scanner.nextInt());
            if (cell.getType() == orderNumber && cell.getPower() < cell.getMaxPower()) {
                cell.setPower(cell.getPower() + 1);

                bufferedOutputStream.write(cell.getNumber());
                bufferedOutputStream.flush();
            }
            else {
                System.out.println("неверная ячейка");
                i--;
            }
        }
        gameMap.printGameMap("power");
    }

    private void readMap() throws IOException {
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

        gameMap = GameMap.createGameMap(cells, routes);
        gameMap.printGameMap();
    }

    private void readStartingCells() throws IOException {
        Cell cell = gameMap.getCell(bufferedInputStream.read());
        cell.setType(1);
        cell.setPower(2);
        cell = gameMap.getCell(bufferedInputStream.read());
        cell.setType(2);
        cell.setPower(3);

        gameMap.printGameMap();
    }



    public static void main(String[] args) throws IOException{
        (new Influence()).startInfluence();
    }

}