package ru.kpfu.itis.group11501.influence.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 * Author: Svintenok Kate and Menshenin Konstantin
 * Date: 03.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Influence extends Application {

    private static final String MENU_FXML = "fxml/main_menu.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(MENU_FXML));
        primaryStage.setTitle("Influence");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

  /*
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
*/

    public static void main(String[] args) throws IOException{
        launch(args);
    }

}