package com.hansung.ac.kr.sfe.method;

import android.util.Log;

import com.hansung.ac.kr.sfe.model.Sensor;

import java.util.ArrayList;

public class CreateSensors extends ArrayList{
    private ArrayList<Sensor> sensors;

    public CreateSensors() {
        sensors = new ArrayList<Sensor>();
    /*
        sensors.add(new Sensor(0,"off", 320, 400));
        sensors.add(new Sensor(1,"off", 490, 400));
        sensors.add(new Sensor(2,"off", 840, 400));
        sensors.add(new Sensor(3,"off", 1200, 400));
        sensors.add(new Sensor(4,"off", 490, 730));
        sensors.add(new Sensor(5,"off", 490, 1300));
        sensors.add(new Sensor(6,"off", 660, 1300));
        sensors.add(new Sensor(7,"off", 610, 1750));
        sensors.add(new Sensor(8,"off", 610, 2100));
        sensors.add(new Sensor(9,"off", 150, 2150));
        sensors.add(new Sensor(10,"off", 1300, 2150));
    */
        sensors.add(new Sensor(0,"off", 280, 310));
        sensors.add(new Sensor(1,"off", 450, 310));
        sensors.add(new Sensor(2,"off", 800, 310));
        sensors.add(new Sensor(3,"off", 1160, 310));
        sensors.add(new Sensor(4,"off", 450, 640));
        sensors.add(new Sensor(5,"off", 450, 1210));
        sensors.add(new Sensor(6,"off", 620, 1210));
        sensors.add(new Sensor(7,"off", 570, 1660));
        sensors.add(new Sensor(8,"off", 570, 2010));
        sensors.add(new Sensor(9,"off", 110, 2060));
        sensors.add(new Sensor(10,"off", 1260, 2060));
    }

    public ArrayList<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(ArrayList<Sensor> sensors) {
        this.sensors = sensors;
    }


}
