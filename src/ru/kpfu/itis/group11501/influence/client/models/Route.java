package ru.kpfu.itis.group11501.influence.client.models;

/**
 * Author: Svintenok Kate
 * Date: 11.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Route {

    int from;
    int to;

    public Route(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "{" + from + ", " + to + '}';
    }
}