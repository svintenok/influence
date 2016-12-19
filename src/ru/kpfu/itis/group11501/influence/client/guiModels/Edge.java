package ru.kpfu.itis.group11501.influence.client.guiModels;

import javafx.geometry.Bounds;
import javafx.scene.shape.Line;

/**
 * Created by cmen on 09/12/16.
 */
public class Edge {

    private Line edge;

    private static final double PADDING_X = 51;
    private static final double PADDING_Y = 61;

    private Bounds boundsInScene;

    public Edge() {
        edge = new Line();
    }

    public Line getLineForm() {
        return edge;
    }

    public void setCells(Cell fromCell, Cell toCell) {
        edge.setStrokeWidth(2);
        edge.setStartX(fromCell.getCenterX());
        System.out.println("StartX: " + fromCell.getCenterX());
        edge.setStartY(fromCell.getCenterY());
        System.out.println("StartY: " + fromCell.getCenterY());

        edge.setEndX(toCell.getCenterX());
        edge.setEndY(toCell.getCenterY());

        boundsInScene = edge.localToScene(edge.getBoundsInLocal());
    }



    public double getStartX() {
        return boundsInScene.getMinX();
    }

    public double getStartY() {
        return boundsInScene.getMinY();
    }

    public double getEndX() {
        return boundsInScene.getMaxX();
    }

    public double getEndY() {
        return boundsInScene.getMaxY();
    }

}
