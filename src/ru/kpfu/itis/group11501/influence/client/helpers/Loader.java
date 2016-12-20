package ru.kpfu.itis.group11501.influence.client.helpers;

import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Author: Svintenok Kate and Konstantin Menshenin
 * Date: 19.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Loader {

    public static void goTo(String resource, Pane pane){
        try {
            Stage stage = (Stage) pane.getScene().getWindow();
            Parent root = javafx.fxml.FXMLLoader.load(Loader.class.getResource(resource));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openModalWindow(String resource, Pane pane, double shiftX, double shiftY){

        try {
            Bounds boundsInScreen = pane.localToScreen(pane.getBoundsInLocal());

            double coordinateX = boundsInScreen.getMinX();
            double coordinateY = boundsInScreen.getMinY();

            Stage modalStage = new Stage();
            Parent modalWindow = javafx.fxml.FXMLLoader.load(Loader.class.getResource(resource));
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