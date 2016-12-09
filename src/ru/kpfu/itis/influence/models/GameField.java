package ru.kpfu.itis.influence.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cmen on 08/12/16.
 */
public class GameField {

    List<Cell> cellsList;

    //ObservableList<Cell> map;

    private static final double SHIFT_X = 50;
    private static final double SHIFT_Y = 44.5;

    private static final double START_X = 0;
    private static final double START_Y = 0;

    private static double x = 0;
    private static double y = 0;


    public GameField(int rows, int columns, Pane pane) {

        int column_counter = 0;
        int row_parity = 0;

        int cellsNumber = rows * columns - rows / 2;

        pane.setPrefSize(SHIFT_X * columns, SHIFT_Y * rows);
        System.out.println(SHIFT_X * columns);
        System.out.println(SHIFT_Y * rows);

        cellsList = new LinkedList<>();
        //map = FXCollections.observableList(cellsList);

        for (int i = 0; i < cellsNumber; i++) {
            Cell cell = new Cell();
            cell.getPaneForm().setLayoutX(x);
            cell.getPaneForm().setLayoutY(y);
            if (i == 17) {
                cell.getPaneForm().getChildren().get(0).setScaleX(1.1);
                cell.getPaneForm().getChildren().get(0).setScaleY(1.1);
                Polygon outerPolygon = (Polygon) cell.getPaneForm().getChildren().get(1);
                outerPolygon.setStrokeWidth(3);
                Label label = (Label) cell.getPaneForm().getChildren().get(2);
                label.setText("12");
                System.out.println("Boom!");
            }
            //map.add(cell);
            cellsList.add(cell);
            pane.getChildren().add(cell.getPaneForm());
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
    }
/*
        int parity;
        LinkedList<LinkedList<Cell>> map = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            parity = 1 - i % 2;
            LinkedList<Cell> temp = new LinkedList<>();
            for (int j = 0; j < columns - parity; j++) {
                Cell cell = new Cell();
                cell.getPaneForm().setLayoutX(x);
                cell.getPaneForm().setLayoutY(y);

                temp.add(cell);
                x += SHIFT_X;
            }
            map.add(temp);
            y += SHIFT_Y;
        }
    }

    public Cell getCell(int row, int column) {
        Cell cell = null;
        try {
            cell = map.get(row).get(column);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return cell;
    }
*/
}
