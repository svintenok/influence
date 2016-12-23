package ru.kpfu.itis.group11501.influence.client.util;

import javafx.application.Platform;
import javafx.scene.text.Text;
import ru.kpfu.itis.group11501.influence.client.models.Cell;
import ru.kpfu.itis.group11501.influence.client.models.GameMap;
import ru.kpfu.itis.group11501.influence.client.models.Status;
import ru.kpfu.itis.group11501.influence.client.util.Connection;

import java.io.IOException;

/**
 * Author: Svintenok Kate
 * Date: 23.12.2016
 * Group: 11-501
 * Project: influence
 */
public class MovesRecipient implements Runnable {
    private Thread thread;
    private GameMap gameMap;
    private Text gameButtonText;

    public MovesRecipient(GameMap gameMap, Status status, Text gameButtonText) {
        this.gameMap = gameMap;
        this.gameButtonText = gameButtonText;
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {

            try {
                /*int count = Connection.getBufferedInputStream().read();
                for (int i = 0; i < count; i++) {
                    Cell cell = gameMap.getCell(Connection.getBufferedInputStream().read());
                    cell.setPower(cell.getPower() + 1);
                }
                */
                    Cell cell = gameMap.getCell(Connection.getBufferedInputStream().read());
                    do {

                        Cell finalCell = cell;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                finalCell.setPower(finalCell.getPower() + 1);
                            }
                        });
                        cell = gameMap.getCell(Connection.getBufferedInputStream().read());

                    } while (cell != null);


                } catch(IOException e){
                    e.printStackTrace();
                }

                gameMap.setStatus(Status.POWERS_DISTRIBUTION);
                int pointsQuantity = gameMap.getCellsCountByType(gameMap.getOrderNumber());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        gameButtonText.setText("Power up your cells (" + pointsQuantity + ")");
                    }
                });
            }
    }
}