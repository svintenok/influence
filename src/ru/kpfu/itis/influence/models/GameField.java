package ru.kpfu.itis.influence.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cmen on 08/12/16.
 */

public class GameField {

    private List<Cell> cellsList;
    private List<Edge> edgesList;

    //ObservableList<Cell> map;

    private static final double SHIFT_X = 50;
    private static final double SHIFT_Y = 44.5;

    private static final double START_X = 0;
    private static final double START_Y = 0;

    private static double x;
    private static double y;

    // Default construvto

    public GameField(int rows, int columns, Pane pane) {

        this(rows, columns, pane, generateFullMap(rows, columns));

    }

    public GameField(int rows, int columns, Pane pane, List<Cell> cellsList) {

        pane.setPrefSize(SHIFT_X * columns, SHIFT_Y * rows);
        pane.getChildren().clear();
        x = 0;
        y = 0;
        System.out.println(SHIFT_X * columns);
        System.out.println(SHIFT_Y * rows);

        for (Cell cell: cellsList) {
            pane.getChildren().add(cell.getPaneForm());
        }

        // The next code is just a hardcode testing

        cellsList.get(1).setColor(Color.RED);

        Edge edge = new Edge();
        edge.setCells(
                cellsList.get(1),
                cellsList.get(2)
        );
        pane.getChildren().add(edge.getLineForm());
        edge.getLineForm().toBack();

        Polygon innerPolygon_1 = (Polygon) cellsList.get(1).getPaneForm().getChildren().get(0);
        Color color1 = (Color) innerPolygon_1.getFill();
        Polygon innerPolygon_2 = (Polygon) cellsList.get(2).getPaneForm().getChildren().get(0);
        Color color2 = (Color) innerPolygon_2.getFill();

        System.out.println(edge.getStartX());
        System.out.println(edge.getStartY());
        System.out.println(edge.getEndX());
        System.out.println(edge.getEndY());

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
        edge.getLineForm().setStroke(edgeColor);
    }

    private static List<Cell> generateFullMap(int rows, int columns) {

        int column_counter = 0;
        int row_parity = 0;

        List<Cell> cells = new LinkedList<>();
        int cellsNumber = rows * columns - rows / 2;
        for (int i = 0; i < cellsNumber; i++) {
            Cell cell;
            if (i == 17) {
                cell = new BigCell();
            } else {
                cell = new Cell();
            }
            cell.getPaneForm().setLayoutX(x);
            cell.getPaneForm().setLayoutY(y);
            cells.add(cell);

            column_counter += 1;
            if (column_counter + row_parity == columns) {
                column_counter = 0;
                y += SHIFT_Y;
                x = row_parity == 0 ? START_X + SHIFT_X / 2 : START_X;
                row_parity = (row_parity + 1) % 2;
            } else {
                x += SHIFT_X;
            }

        }

        return cells;
    }

}
