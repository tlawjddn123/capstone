package com.hansung.ac.kr.sfe.beacon;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Beacon {

    private String address;
    private int rssi;
    private int node;
    private String now;
    private int life;

    public Beacon(int node,String address) {
        this.node = node;
        this.address = address;
    }

    public Beacon(String address, int rssi, String now) {
        this.address = address;
        this.rssi = rssi;
        this.now = now;
    }

    public Beacon() {

    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public String getAddress() {
        return address;
    }

    public int getRssi() {
        return rssi;
    }

    public String getNow() {
        return now;
    }

    public void born() {
        this.life = 0;
    }

    public void age() {
        this.life++;
    }

    public int getLife() {
        return this.life;
    }
}
