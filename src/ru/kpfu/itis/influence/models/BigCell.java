package ru.kpfu.itis.influence.models;

import javafx.scene.control.Label;
import javafx.scene.shape.Polygon;

/**
 * Created by cmen on 09/12/16.
 */

public class BigCell extends Cell{

    private static final int MAX_VALUE = 12;

    public BigCell() {
        super();
        this.getPaneForm().getChildren().get(0).setScaleX(1.1);
        this.getPaneForm().getChildren().get(0).setScaleY(1.1);
        Polygon outerStroke = (Polygon) this.getPaneForm().getChildren().get(1);
        outerStroke.setStrokeWidth(3);

        this.getPaneForm().getChildren().get(2).setOnMouseClicked(mouseEvent -> {
            Label label = (Label) this.getPaneForm().getChildren().get(2);

            int value = Integer.parseInt(label.getText());
            if (value < MAX_VALUE) {
                label.setText(String.valueOf(++value));
                double scaleX = this.getPaneForm().getChildren().get(0).getScaleX();
                this.getPaneForm().getChildren().get(0).setScaleX(scaleX + 0.005);
                this.getPaneForm().getChildren().get(0).setScaleY(scaleX + 0.005);
            }
        });

    }
}
