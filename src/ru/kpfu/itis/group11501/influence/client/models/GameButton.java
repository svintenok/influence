package ru.kpfu.itis.group11501.influence.client.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

/**
 * Author: Svintenok Kate and Menshenin Konstantin
 * Date: 25.12.2016
 * Group: 11-501
 * Project: influence
 */
public class GameButton {

    private static final String GAME_BUTTON_FXML = "../fxml/game_button.fxml";
    private static final int SCREEN_WIDTH = 800;

    private GameMap gameMap;
    private Pane gameButtonPane;

    public GameButton(GameMap gameMap) {

        this.gameMap = gameMap;

        try {
            gameButtonPane = FXMLLoader.load(getClass().getResource(GAME_BUTTON_FXML));

            Color playerColor;
            Color enemyColor;

            if (gameMap.getOrderNumber() == 1) {
                playerColor = Color.valueOf("#3A5FCD");
                enemyColor = Color.valueOf("#2E8B57");
            }
            else {
                playerColor = Color.valueOf("#2E8B57");
                enemyColor = Color.valueOf("#3A5FCD");
            }

            ((Rectangle) gameButtonPane.getChildren().get(0)).setFill(enemyColor);
            ((Rectangle) gameButtonPane.getChildren().get(1)).setFill(playerColor);
            ((Rectangle) gameButtonPane.getChildren().get(4)).setFill(playerColor);

        } catch (IOException e) {
            e.printStackTrace();
        }

        updatePowersBalance();
    }

    public void updatePowersBalance() {
        int enemyPowers = gameMap.getPowersByType(gameMap.getEnemyOrderNumber());
        int playerPowers = gameMap.getPowersByType(gameMap.getOrderNumber());

        ((Rectangle) gameButtonPane.getChildren().get(1)).setWidth(
                (SCREEN_WIDTH / (enemyPowers + playerPowers)) * playerPowers);
    }

    public void setText(String text, String helpText) {
        ((Label) gameButtonPane.getChildren().get(2)).setText(text);
        ((Label) gameButtonPane.getChildren().get(3)).setText(helpText);
    }

    public void setText(String text) {
        setText(text, "");
    }

    public Pane getGameButtonPane() {
        return gameButtonPane;
    }
}
