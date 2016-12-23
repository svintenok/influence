package ru.kpfu.itis.group11501.influence.client.util;

import javafx.application.Platform;
import javafx.scene.text.Text;
import ru.kpfu.itis.group11501.influence.client.models.Cell;
import ru.kpfu.itis.group11501.influence.client.models.GameMap;
import ru.kpfu.itis.group11501.influence.client.models.Status;

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

    public MovesRecipient(GameMap gameMap, Text gameButtonText) {
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
                    while (Connection.getBufferedInputStream().read() != 0){
                        byte[] moveBytes = new byte[5];
                        Connection.getBufferedInputStream().read(moveBytes);

                        Cell fromCell = gameMap.getCell(moveBytes[0]);
                        Cell toCell = gameMap.getCell(moveBytes[2]);

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                fromCell.selecting();
                                toCell.selecting();
                                toCell.setPower(moveBytes[3]);
                                toCell.setType(moveBytes[4]);
                                fromCell.setPower(moveBytes[1]);
                                fromCell.deleteSelecting();
                                toCell.deleteSelecting();
                            }
                        });
                    }

                    int count = Connection.getBufferedInputStream().read();

                    for (int i = 0; i < count; i ++){
                        Cell cell = gameMap.getCell(Connection.getBufferedInputStream().read());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                cell.setPower(cell.getPower() + 1);
                            }
                        });
                    }

                } catch(IOException e){
                    e.printStackTrace();
                }

                gameMap.setStatus(Status.CAPTURE);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        gameButtonText.setText("Touch a nearby cell to attack");
                    }
                });
            }
    }
}