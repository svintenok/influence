package ru.kpfu.itis.influence.models;

import javafx.scene.shape.Line;

/**
 * Created by cmen on 09/12/16.
 */
public class Edge {

    private Line edge;

    public Edge() {
        edge = new Line();
    }

    public Line getLineForm() {
        return edge;
    }

    public void setCells(Cell fromCell, Cell toCell) {
        edge.setStartX(fromCell.getCenterX());
        edge.setStartY(fromCell.getCenterY());

        edge.setEndX(toCell.getCenterX());
        edge.setEndY(toCell.getCenterY());
    }

}
