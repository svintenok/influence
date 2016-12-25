package ru.kpfu.itis.group11501.influence.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

/**
 * Author: Svintenok Kate and Menshenin Konstantin
 * Date: 03.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Influence extends Application {

    private static final String MENU_FXML = "/fxml/main_menu.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MENU_FXML));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Influence");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException{
        launch(args);
    }

}