package server;

import server.models.Cell;

import java.util.*;

/**
 * Author: Svintenok Kate
 * Date: 07.12.2016
 * Group: 11-501
 * Project: influence
 */

/**
 * First version!
 * Planned generation with removing cells and checking cell for bridge edges
 */
public class GraphGenerator {

    public final int maxX = 14;
    public final int maxY = 9;
    public final int cellsCount = 77;
    public final int cellsMaxCount = 121;
    public final double bigCellsPercent = 0.15;
    public final double routesAddingPercent = 0.86;

    private Map<Integer, Cell> cellsMap;
    private Map<Integer, Set<Integer>> routesMap;

    private Random random;
    private int[] yCoordinatesMap;
    private Map<Integer, Integer> reachableCellsMap;


    public GraphGenerator() {

        routesMap = new HashMap<>();
        cellsMap = new HashMap<>();

        random = new Random();
        reachableCellsMap = new HashMap<>();
        yCoordinatesMap = new int[cellsMaxCount + 1];

        for (int i = 1; i <= maxY; i++) {
            for (int j = 1; j < maxX; j++) {
                yCoordinatesMap[(i - 1) * (maxX - 1) + ((i - 1) / 2) + j] = i;
            }
            if (i % 2 == 0) {
                yCoordinatesMap[(i - 1) * (maxX - 1) + ((i - 1) / 2) + maxX] = i;
            }
        }
    }


    public  void generate(){

        int cellNum = random.nextInt(cellsMaxCount);
        addCell(cellNum);

        for (int i = 2; i <= cellsCount; i++) {
            Integer[] reachableCells = new Integer[reachableCellsMap.size()];
            reachableCellsMap.keySet().toArray(reachableCells);

            do {
                cellNum = reachableCells[random.nextInt(reachableCells.length)];
            }
            while (cellsMap.get(cellNum) != null);

            addCell(cellNum);
            addRoute(cellNum, reachableCellsMap.get(cellNum));
            reachableCellsMap.remove(cellNum);
        }

        addRoutes();

        printMap();
    }


    private void addCell(int cellNum) {

        int cellSize = random.nextDouble() > bigCellsPercent? 1 : 2;
        cellsMap.put(cellNum, new Cell(cellSize));

        addReachableCells(cellNum);
    }


    private void addReachableCells(int cellNum) {

        if (cellNum + 1 <= cellsMaxCount && yCoordinatesMap[cellNum] == yCoordinatesMap[cellNum + 1])
            reachableCellsMap.put(cellNum + 1, cellNum);

        if (cellNum + maxX - 1 <= cellsMaxCount && yCoordinatesMap[cellNum] + 1 == yCoordinatesMap[cellNum + maxX - 1])
            reachableCellsMap.put(cellNum + maxX - 1, cellNum);

        if (cellNum + maxX <= cellsMaxCount  && yCoordinatesMap[cellNum] + 1 == yCoordinatesMap[cellNum + maxX])
            reachableCellsMap.put(cellNum + maxX, cellNum);

        if (cellNum - 1 >= 1 && yCoordinatesMap[cellNum] == yCoordinatesMap[cellNum - 1])
            reachableCellsMap.put(cellNum - 1, cellNum);

        if (cellNum - maxX + 1 >= 1 && yCoordinatesMap[cellNum] - 1 == yCoordinatesMap[cellNum - maxX + 1])
            reachableCellsMap.put(cellNum - maxX + 1, cellNum);

        if (cellNum - maxX >= 1 && yCoordinatesMap[cellNum] - 1 == yCoordinatesMap[cellNum - maxX])
            reachableCellsMap.put(cellNum - maxX, cellNum);
    }


    private void addRoutes() {

        for (Integer cellNum : cellsMap.keySet()){

            if (routesMap.get(cellNum) == null)
                routesMap.put(cellNum, new HashSet<>());

            if ( random.nextDouble() < routesAddingPercent &&
                    cellNum + 1 <= cellsMaxCount &&
                    cellsMap.get(cellNum + 1) != null &&
                    !routesMap.get(cellNum).contains(cellNum + 1)) {

                if (yCoordinatesMap[cellNum] == yCoordinatesMap[cellNum + 1])
                    addRoute(cellNum, cellNum + 1);
            }
            if ( random.nextDouble() < routesAddingPercent &&
                    cellNum + maxX - 1<= cellsMaxCount &&
                            cellsMap.get(cellNum + maxX - 1) != null &&
                            !routesMap.get(cellNum).contains(cellNum + maxX - 1)) {

                if (yCoordinatesMap[cellNum] + 1 == yCoordinatesMap[cellNum + maxX - 1])
                    addRoute(cellNum, cellNum + maxX - 1);
            }
            if ( random.nextDouble() < routesAddingPercent &&
                    cellNum + maxX<= cellsMaxCount &&
                            cellsMap.get(cellNum + maxX) != null &&
                            !routesMap.get(cellNum).contains(cellNum + maxX)) {

                if (yCoordinatesMap[cellNum] + 1 == yCoordinatesMap[cellNum + maxX])
                    addRoute(cellNum, cellNum + maxX);
            }
        }
    }

    private void addRoute(int fromCellNum, int toCellNum){

        if (fromCellNum < toCellNum) {
            if (routesMap.get(fromCellNum) == null)
                routesMap.put(fromCellNum, new HashSet<>());
            routesMap.get(fromCellNum).add(toCellNum);
        }
        else {
            if (routesMap.get(toCellNum) == null)
                routesMap.put(toCellNum, new HashSet<>());
            routesMap.get(toCellNum).add(fromCellNum);
        }

        Cell fromCell = cellsMap.get(fromCellNum);
        Cell toCell = cellsMap.get(toCellNum);
        fromCell.setRouteCount(fromCell.getRouteCount() + 1);
        toCell.setRouteCount(toCell.getRouteCount() + 1);
    }


    private void printMap() {
        System.out.println("--------------------------");
        for (int i = 1; i <= maxY; i++) {
            if (i % 2 == 1)
                System.out.print(" ");

            for (int j = 1; j < maxX; j++)
                printCell((i - 1) * (maxX - 1) + ((i - 1) / 2) + j);

            if (i % 2 == 0)
                printCell((i - 1) * (maxX - 1) + ((i - 1) / 2) + maxX);

            System.out.println();
        }
        System.out.println("--------------------------");
        System.out.println(routesMap);

    }


    private void printCell(int cellNum){
        Cell cell  = cellsMap.get(cellNum);
        if (cell != null) {
            System.out.print(cell.getSize() + " ");
        } else {
            System.out.print("  ");
        }
    }


    public static void main(String[] args) {
        GraphGenerator graphGenerator = new GraphGenerator();
        graphGenerator.generate();
    }
}
