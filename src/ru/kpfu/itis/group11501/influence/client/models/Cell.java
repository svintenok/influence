package ru.kpfu.itis.group11501.influence.client.models;

/**
 * Author: Svintenok Kate
 * Date: 11.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Cell {
    private int number;
    private int type;
    private int power;
    private int maxPower;

    public Cell(int number, int maxPower) {
        this.number = number;
        this.maxPower = maxPower;
        this.power = 0;
        this.type = 0;
    }

    public int getNumber() {
        return number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMaxPower() {
        return maxPower;
    }
}