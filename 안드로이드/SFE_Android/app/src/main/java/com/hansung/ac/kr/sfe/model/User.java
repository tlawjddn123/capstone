package com.hansung.ac.kr.sfe.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class User implements Serializable {

    private static final long SerialVersionUID = 1L;

    private String name;
    private int userX;
    private int userY;
    private int node;
    private String id;

    public User() {
    }

    public User(String name, int userX, int userY, int node) {
        this.name = name;
        this.userX = userX;
        this.userY = userY;
        this.node = node;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserX() {
        return userX;
    }

    public void setUserX(int userX) {
        this.userX = userX;
    }

    public int getUserY() {
        return userY;
    }

    public void setUserY(int userY) {
        this.userY = userY;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}

