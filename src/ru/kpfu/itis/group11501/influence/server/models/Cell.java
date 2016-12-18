package ru.kpfu.itis.group11501.influence.server.models;

/**
 * Author: Svintenok Kate
 * Date: 07.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Cell {

    private int size;
    private int routeCount;


    public Cell(int size) {
        this.size = size;
        this.routeCount = 0;
    }

    public int getSize() {
        return size;
    }

    public int getRouteCount() {
        return routeCount;
    }

    public void setRouteCount(int routeCount) {
        this.routeCount = routeCount;
    }
}