package ru.kpfu.itis.group11501.influence.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.kpfu.itis.group11501.influence.client.models.*;


import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 * Author: Svintenok Kate
 * Date: 03.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Influence extends Application {

    //private static final int PORT = 3456;
    //private static final String HOST = "localhost";
    private static final String MENU_FXML = "fxml/main_menu.fxml";
    //private BufferedInputStream bufferedInputStream;
    //private BufferedOutputStream bufferedOutputStream;
    //private Scanner scanner;
    //private GameMap gameMap;
    //private int orderNumber;

    //private Random random;

   /* public Influence() {

        this.random = new Random();

        try {
            Socket s = new Socket(HOST, PORT);
            this.bufferedInputStream = new BufferedInputStream(s.getInputStream());
            this.bufferedOutputStream = new BufferedOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.scanner = new Scanner(System.in);
    }
*/
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(MENU_FXML));
        primaryStage.setTitle("Influence");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

  /*  public void startInfluence() throws IOException {
        readMap();
        orderNumber = bufferedInputStream.read();
        readStartingCells();

        if (orderNumber == 1){
            move();
            givePowers();
        }


        while (true) {
            getEnemyMove();
            move();
            givePowers();
        }

    }

    private void move() throws IOException {
        gameMap.printGameMap("number");
        System.out.println("Ваш ход");
        while (scanner.nextInt() != 0){
            Cell fromCell = gameMap.getCell(scanner.nextInt());
            Cell toCell = gameMap.getCell(scanner.nextInt());
            if (fromCell == null || fromCell.getType() != orderNumber ||
                    fromCell.getPower() < 2 || toCell == null ||
                    toCell.getType() == orderNumber || !gameMap.isConnected(fromCell, toCell))
                System.out.println("Ошибка");
            else {
                if (toCell.getType() == 0){
                    toCell.setType(orderNumber);
                    toCell.setPower(fromCell.getPower() - 1);
                    fromCell.setPower(1);
                }
                else {
                    int fromCellPower = fromCell.getPower();
                    int toCellPower = toCell.getPower();

                    if (Math.abs(fromCellPower - toCellPower) >= 2){
                        toCell.setPower(Math.abs(fromCellPower - toCellPower) <= toCell.getMaxPower()? Math.abs(fromCellPower - toCellPower): toCell.getMaxPower());
                        fromCell.setPower(1);
                        if (fromCellPower > toCellPower)
                            if (toCell.getMaxPower() < Math.abs(fromCellPower - toCellPower))
                                fromCell.setPower((toCell.getMaxPower() - (fromCellPower - toCellPower)));
                            toCell.setType(orderNumber);
                    }
                    else {
                        fromCell.setPower(1);
                        toCell.setPower(1);
                        if (fromCellPower == toCellPower)
                            if (random.nextDouble() > 0.5)
                                toCell.setPower(orderNumber);
                        else if (fromCellPower > toCellPower)
                                if (random.nextDouble() > 0.25)
                                    toCell.setPower(orderNumber);
                        else
                                if (random.nextDouble() > 0.75)
                                    toCell.setPower(orderNumber);
                    }
                }
                gameMap.printGameMap("power");

                //sending move
                bufferedOutputStream.write(1);
                bufferedOutputStream.flush();

                bufferedOutputStream.write(fromCell.getNumber());
                bufferedOutputStream.write(fromCell.getPower());

                bufferedOutputStream.write(toCell.getNumber());
                bufferedOutputStream.write(toCell.getPower());
                bufferedOutputStream.write(toCell.getType());
                bufferedOutputStream.flush();
            }
        }
        bufferedOutputStream.write(0);
    }

    private void getEnemyMove() throws IOException {

        while (bufferedInputStream.read() != 0){
            Cell fromCell = gameMap.getCell(bufferedInputStream.read());
            fromCell.setPower(bufferedInputStream.read());
            Cell toCell = gameMap.getCell(bufferedInputStream.read());
            toCell.setPower(bufferedInputStream.read());
            toCell.setType(bufferedInputStream.read());

            gameMap.printGameMap("power");
        }


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

*/

    public static void main(String[] args) throws IOException{
        launch(args);
        //(new Influence()).startInfluence();
    }

}