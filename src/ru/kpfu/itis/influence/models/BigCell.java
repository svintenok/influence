package ru.kpfu.itis.influence.models;

import javafx.scene.control.Label;
import javafx.scene.shape.Polygon;

/**
 * Created by cmen on 09/12/16.
 */
public class BigCell extends Cell{

    public BigCell() {
        super();
        this.getPaneForm().getChildren().get(0).setScaleX(1.1);
        this.getPaneForm().getChildren().get(0).setScaleY(1.1);
        Polygon outerStroke = (Polygon) this.getPaneForm().getChildren().get(1);
        outerStroke.setStrokeWidth(3);
        Label value = (Label) this.getPaneForm().getChildren().get(2);
        value.setText("12");
    }
}
