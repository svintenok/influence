package ru.kpfu.itis.group11501.influence.client.util.helpers;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Author: Svintenok Kate and Menshenin Konstantin
 * Date: 19.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Loader {

    public static void goTo(String resource, Pane pane){
        Stage stage = (Stage) pane.getScene().getWindow();
        goTo(resource, stage);
    }

    public static void goTo(String resource, Stage stage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Loader.class.getResource(resource));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openModalWindow(String resource, Pane pane){

        try {
            double shiftX = 255;
            double shiftY = 200;

            Bounds boundsInScreen = pane.localToScreen(pane.getBoundsInLocal());

            double coordinateX = boundsInScreen.getMinX();
            double coordinateY = boundsInScreen.getMinY();

            Stage modalStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Loader.class.getResource(resource));
            Parent modalWindow = fxmlLoader.load();
            modalStage.setX(coordinateX + shiftX);
            modalStage.setY(coordinateY + shiftY);
            modalStage.initStyle(StageStyle.UNDECORATED);
            modalStage.setMinWidth(300);
            modalStage.setMinHeight(150);
            modalStage.setResizable(false);
            modalStage.setScene(new Scene(modalWindow));
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(pane.getScene().getWindow());
            modalStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}