package ru.kpfu.itis.group11501.influence.client.util;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import ru.kpfu.itis.group11501.influence.client.models.Cell;
import ru.kpfu.itis.group11501.influence.client.models.GameMap;
import ru.kpfu.itis.group11501.influence.client.models.Status;
import ru.kpfu.itis.group11501.influence.client.util.helpers.Loader;

import java.io.IOException;

/**
 * Author: Svintenok Kate
 * Date: 23.12.2016
 * Group: 11-501
 * Project: influence
 */
public class MovesRecipient implements Runnable {
    private static final String LOSE_FXML = "../../fxml/lose.fxml";
    private Thread thread;
    private GameMap gameMap;
    private Pane gamePane;
    private Text gameButtonText;



    public MovesRecipient(GameMap gameMap, Text gameButtonText, Pane gamePane) {
        this.gameMap = gameMap;
        this.gameButtonText = gameButtonText;
        this.gamePane = gamePane;
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {

        boolean flag = true;

        while (flag) {
            try {
                while (Connection.getBufferedInputStream().read() != 0 && !thread.isInterrupted()) {


                    byte[] moveBytes = new byte[5];
                    Connection.getBufferedInputStream().read(moveBytes);

                    Cell fromCell = gameMap.getCell(moveBytes[0]);
                    Cell toCell = gameMap.getCell(moveBytes[2]);


                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            fromCell.selecting();
                            toCell.selecting();
                            gameMap.changeCell(toCell, moveBytes[4], moveBytes[3]);
                            fromCell.setPower(moveBytes[1]);
                            fromCell.deleteSelecting();
                            toCell.deleteSelecting();
                        }
                    });

                }

                if (thread.isInterrupted()) {
                    flag = false;
                    break;
                }

                int count = Connection.getBufferedInputStream().read();

                if (count == 0) {

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Loader.openModalWindow(LOSE_FXML, gamePane);
                        }
                    });
                    break;
                }

                for (int i = 0; i < count; i++) {
                    Cell cell = gameMap.getCell(Connection.getBufferedInputStream().read());
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            cell.increasePower();
                        }
                    });
                }

            } catch (IOException e) {
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

    public void interrupt(){
        thread.interrupt();
    }
}