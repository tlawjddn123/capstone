package com.hansung.ac.kr.sfe.model;

public class Sensor {
    private int id;
    private String sensorValue;
    private int sensorX;
    private int sensorY;

    public Sensor(int id, String sensorValue, int sensorX, int sensorY) {
        this.id = id;
        this.sensorValue = sensorValue;
        this.sensorX = sensorX;
        this.sensorY = sensorY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSensorValue() {
        return sensorValue;
    }

    public void setSensorValue(String sensorValue) {
        this.sensorValue = sensorValue;
    }

    public int getSensorX() {
        return sensorX;
    }

    public void setSensorX(int sensorX) {
        this.sensorX = sensorX;
    }

    public int getSensorY() {
        return sensorY;
    }

    public void setSensorY(int sensorY) {
        this.sensorY = sensorY;
    }

}
