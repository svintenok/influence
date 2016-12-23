package ru.kpfu.itis.group11501.influence.client.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Svintenok Kate and  Menshenin Konstantin
 * Date: 11.12.2016
 * Group: 11-501
 * Project: influence
 */
public class GameMap {

    private List<Cell> cells;
    private List<Route> routes;
    private int orderNumber;
    private Status status;

    public final static int maxX = 14;
    public final static int maxY = 9;
    public final static int cellsMaxCount = 121;


    public GameMap(byte[] cellsBytes, byte[] routesBytes){

        int[] yCoordinatesMap = new int[cellsMaxCount + 1];
        int[] xCoordinatesMap = new int[cellsMaxCount + 1];

        for (int i = 1; i <= maxY; i++) {
            for (int j = 1; j < maxX; j++) {
                yCoordinatesMap[(i - 1) * (maxX - 1) + ((i - 1) / 2) + j] = i;
                xCoordinatesMap[(i - 1) * (maxX - 1) + ((i - 1) / 2) + j] = j;
            }
            if (i % 2 == 0) {
                yCoordinatesMap[(i - 1) * (maxX - 1) + ((i - 1) / 2) + maxX] = i;
                xCoordinatesMap[(i - 1) * (maxX - 1) + ((i - 1) / 2) + maxX] = maxX;
            }
        }

        //Cells creating
        this.cells = new ArrayList<>();
        for (int i = 0; i < cellsBytes.length; i++){
            if (cellsBytes[i] != 0)
                cells.add(new Cell(i+1, cellsBytes[i], yCoordinatesMap[i+1], xCoordinatesMap[i+1]));
        }

        //Routes creating
        this.routes = new ArrayList<>();
        for (int i = 0; i < routesBytes.length-1; i+=2){
            routes.add(new Route(getCell(routesBytes[i]), getCell(routesBytes[i+1])));
        }
    }


    public void printGameMap(String view){

        System.out.println("--------------------------");
        for (int i = 1; i <= maxY; i++) {
            if (i % 2 == 1)
                System.out.print(" ");

            for (int j = 1; j < maxX; j++)
                printCell((i - 1) * (maxX - 1) + ((i - 1) / 2) + j, view);

            if (i % 2 == 0)
                printCell((i - 1) * (maxX - 1) + ((i - 1) / 2) + maxX, view);

            System.out.println();
        }
        System.out.println("--------------------------");
        System.out.println(routes);
    }

    public void printCell(int cellNum, String view){
        Cell cell  = getCell(cellNum);
        if (cell != null) {
           if (view.equals("type"))
                System.out.print(cell.getType() + " ");
            else if (view.equals("power"))
                System.out.print(cell.getPower() + " ");
            else if (view.equals("maxPower"))
                System.out.print(cell.getMaxPower() + " ");
            else if (view.equals("number"))
                System.out.print(cell.getNumber() + " ");

        } else {
            System.out.print("  ");
        }
    }

    public void printGameMap(){
        printGameMap("type");
    }

    public Cell getCell(int cellNum){

        for (Cell cell: cells)
            if (cell.getNumber() == cellNum)
                return cell;

        return null;
    }

    public int getCellsCountByType(int type){

        int count = 0;
        for (Cell cell: cells)
            if (cell.getType() == type)
                count++;

        return count;
    }

    public boolean isConnected(Cell cell1, Cell cell2) {
        int fromCellNum;
        int toCellNum;
        if (cell1.getNumber() < cell2.getNumber()){
            fromCellNum = cell1.getNumber();
            toCellNum = cell2.getNumber();
        }
        else {
            fromCellNum = cell2.getNumber();
            toCellNum = cell1.getNumber();
        }

        for (Route route: routes)
            if (route.getFrom() == fromCellNum && route.getTo() == toCellNum)
                return true;
        return false;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void changeCell(Cell cell, int type, int power) {
        cell.setPower(power);
        cell.setType(type);
        for (Route route : getRoutes(cell))
            route.setGradient(getCell(route.getFrom()), getCell(route.getTo()));
    }

    private List<Route> getRoutes(Cell cell){

        List<Route> routesFromCell = new ArrayList<>();
        for (Route route : routes)
            if (route.getFrom() == cell.getNumber() || route.getTo() == cell.getNumber())
                routesFromCell.add(route);
        return routesFromCell;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }
}