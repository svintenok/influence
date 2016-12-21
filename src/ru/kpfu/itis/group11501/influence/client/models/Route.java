package ru.kpfu.itis.group11501.influence.client.models;

import javafx.scene.paint.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;


/**
 * Author: Svintenok Kate and Konstantin Menshenin
 * Date: 11.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Route {

    private int from;
    private int to;

    private Line edge;


    public Route(Cell fromCell, Cell  toCell) {
        this.from = fromCell.getNumber();
        this.to = toCell.getNumber();

        edge = new Line();

        edge.setStrokeWidth(2);
        edge.setStroke(Color.valueOf("#4F4F4F"));
        edge.setOpacity(0.7);

        edge.setStartX(fromCell.getCenterX());
        edge.setStartY(fromCell.getCenterY());

        edge.setEndX(toCell.getCenterX());
        edge.setEndY(toCell.getCenterY());
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public Line getEdge() {
        return edge;
    }

    @Override
    public String toString() {
        return "{" + from + ", " + to + '}';
    }


    public void setGradient(Cell fromCell, Cell toCell) {

        Color color1 = (Color) ((Polygon)fromCell.getCellPane().getChildren().get(0)).getFill();
        Color color2 = (Color) ((Polygon)toCell.getCellPane().getChildren().get(0)).getFill();

        LinearGradient edgeColor = new LinearGradient(
                edge.getStartX(),
                edge.getStartY(),
                edge.getEndX(),
                edge.getEndY(),
                false,
                CycleMethod.NO_CYCLE,
                new Stop(0, color1),
                new Stop(1, color2)
        );

        edge.setStroke(edgeColor);
    }
}