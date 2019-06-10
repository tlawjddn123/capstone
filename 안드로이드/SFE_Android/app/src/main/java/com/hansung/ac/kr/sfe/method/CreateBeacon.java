package com.hansung.ac.kr.sfe.method;

import android.util.Log;

import com.hansung.ac.kr.sfe.beacon.Beacon;

import java.util.ArrayList;

public class CreateBeacon {
    private ArrayList<Beacon> beacons;

//"34:03:DE:02:F3:1D
    private final String beaconsUuid[] = {"A8:10:87:21:91:45","34:99:99:99:99:99","34:03:DE:02:EB:16",
            "34:03:DE:02:AE:A4","34:03:DE:02:A4:7F","34:03:DE:02:DA:57","34:03:DE:02:47:49",
            "A8:10:87:1B:4D:1D","34:03:DE:02:E5:89","34:03:DE:02:FA:66","A8:10:87:21:8F:89" ,"34:03:DE:03:14:27"
    };

    public ArrayList<Beacon> getBeacons() {
        return beacons;
    }

    public void setBeacons(ArrayList<Beacon> beacons) {
        this.beacons = beacons;
    }

    public CreateBeacon() {
        beacons = new ArrayList<Beacon>();

        for(int i=1;i<=12;i++) {
            beacons.add(new Beacon(i,beaconsUuid[i-1])); //0
            Log.d("beacon ADD", "i = "+ beaconsUuid[i-1]);
        }
    }
}
