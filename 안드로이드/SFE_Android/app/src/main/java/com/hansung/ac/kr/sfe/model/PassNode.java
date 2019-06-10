package com.hansung.ac.kr.sfe.model;

public class PassNode {
    private int id;
    private int node;
    private int nodeX;
    private int nodeY;

    public PassNode(int id, int node, int nodeX, int nodeY) {
        this.id = id;
        this.node = node;
        this.nodeX = nodeX;
        this.nodeY = nodeY;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public void setNodeX(int nodeX) {
        this.nodeX = nodeX;
    }

    public void setNodeY(int nodeY) {
        this.nodeY = nodeY;
    }

    public int getId() {
        return id;
    }

    public int getNode() {
        return node;
    }

    public int getNodeX() {
        return nodeX;
    }

    public int getNodeY() {
        return nodeY;
    }
}
