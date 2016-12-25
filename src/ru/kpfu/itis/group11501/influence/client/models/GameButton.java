package ru.kpfu.itis.group11501.influence.client.models;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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
    private static LinearGradient linearGradient;
    private static FadeTransition fadeTransition;

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

            Stop[] stops = new Stop[] {
                    new Stop(0, Color.WHITESMOKE),
                    new Stop(0.27, Color.WHITESMOKE),
                    new Stop(0.27, playerColor),
                    new Stop(1, playerColor)};
            linearGradient = new LinearGradient(0.27, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);


            fadeTransition = new FadeTransition();
            fadeTransition.setCycleCount(2);
            fadeTransition.setNode(gameButtonPane);
            fadeTransition.setDuration(Duration.millis(56));
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.5);
            fadeTransition.setAutoReverse(true);

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

    public void setText(String text, String helpText, boolean coloringText) {

        if (coloringText) {
            ((Label) gameButtonPane.getChildren().get(2)).setTextFill(linearGradient);
        }
        else
            ((Label) gameButtonPane.getChildren().get(2)).setTextFill(Color.WHITESMOKE);

        ((Label) gameButtonPane.getChildren().get(2)).setText(text);
        ((Label) gameButtonPane.getChildren().get(3)).setText(helpText);
    }

    public void setText(String text, String helpText) {
        setText(text, helpText, false);
    }

    public void setText(String text) {
        setText(text, "");
    }

    public Pane getGameButtonPane() {
        return gameButtonPane;
    }

    public static void animate() {
        fadeTransition.play();
    }
}
