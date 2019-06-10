package com.hansung.ac.kr.sfe;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hansung.ac.kr.sfe.beacon.Beacon;
import com.hansung.ac.kr.sfe.beacon.BeaconAdapter;
import com.hansung.ac.kr.sfe.method.CreateBeacon;
import com.hansung.ac.kr.sfe.sendReciveData.SendReceiveData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private SendReceiveData fireData;
    private DatabaseReference databaseReference;
    private Location location;
    private FirebaseAuth mAuth;

    //Beacon
    BluetoothAdapter mBluetoothAdapter;
    BluetoothLeScanner mBluetoothLeScanner;
    BluetoothLeAdvertiser mBluetoothLeAdvertiser;
    private static final int PERMISSIONS = 100;
    //초기화된 비콘들을 저장한 벡터
    ArrayList<Beacon> beaconList;
    //리스트뷰에 이용할 벡터
    private Vector<Beacon> beacons;
    private CreateBeacon createBeacon;
    BeaconAdapter beaconAdapter;
    ListView beaconListView;
    ScanSettings.Builder mScanSettings;
    List<ScanFilter> scanFilters;
    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.KOREAN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createBeacon = new CreateBeacon();
        beaconList = createBeacon.getBeacons();
       //User----------------------------------------------------------------
         location = new Location(this);

        setContentView(location);
        //setContentView(R.layout.activity_main);

        //fireBase--------------------------------------------------------------
        FirebaseMessaging.getInstance().subscribeToTopic("ALL"); //모두에게 푸시하는거 받음
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //Beacon----------------------------------------------------------------
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS);
        beaconListView = (ListView) findViewById(R.id.beaconListView);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
        mBluetoothLeAdvertiser = mBluetoothAdapter.getBluetoothLeAdvertiser();

        beacons = new Vector<Beacon>();
        mScanSettings = new ScanSettings.Builder();
        mScanSettings.setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY);

        ScanSettings scanSettings = mScanSettings.build();

        scanFilters = new Vector<>();
        ScanFilter.Builder scanFilter = new ScanFilter.Builder();
        //A8:10:87:21:91:45 / 34:03:DE:03:14:27 / 34:03:DE:02:EB:16 / 34:03:DE:02:AE:A4 / 34:03:DE:02:A4:7F / 34:03:DE:02:DA:57
        //스캔할 비콘 맥주소 셋팅
        for(int i = 0; i <beaconList.size(); i++) {
            scanFilter.setDeviceAddress(beaconList.get(i).getAddress()); //ex) 00:00:00:00:00:00
            ScanFilter scan = scanFilter.build();
            scanFilters.add(scan);
        }

       mBluetoothLeScanner.startScan(scanFilters, scanSettings, mScanCallback);
        // filter와 settings 기능을 사용하지 않을 때는mBluetoothLeScanner.startScan(mScanCallback); 처럼 사용하시면 돼요.



        /*//Web -> Android--------------------------------------------------------
        Log.d("asd","asd");
        fireData = new SendReceiveData(location);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(fireData,1000,3000);
        */
    }

    //Beacon 스캔 될때마다 실행------------------------------------------------------------
    ScanCallback mScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            try {
                ScanRecord scanRecord = result.getScanRecord();
                final ScanResult scanResult = result;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for(int i = 0;i<beaconList.size();i++) {
                                    if (scanResult.getDevice().getAddress().equals(beaconList.get(i).getAddress())) {
                                        Log.d("onScanResult()", scanResult.getDevice().getAddress() + "\n" + scanResult.getRssi());
                                        beaconSetting(beaconList,i,scanResult);
                                    }
                                }
                                if(!beacons.isEmpty()) {
                                    Beacon maxBeacon = new Beacon();
                                    int max = beacons.get(0).getRssi();
                                    for (Beacon b : beacons) {
                                        Log.d("fireDAta: ", ""+b.getNode()+ " " + b.getRssi() );
                                        if (max <= b.getRssi()) {
                                            max = b.getRssi();
                                            maxBeacon = b;
                                        }
                                    }
                                    Log.d("fireDAta max: ", ""+maxBeacon.getNode()+ "" + maxBeacon.getRssi());

                                    fireData = new SendReceiveData(location);
                                    fireData.setNode(maxBeacon.getNode());
                                    fireData.getTh().start();

                                }
/*
                                beaconAdapter = new BeaconAdapter(beacons, getLayoutInflater());
                                beaconListView.setAdapter(beaconAdapter);
                                beaconAdapter.notifyDataSetChanged();
*/
                            }
                        });
                    }
                }).start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
            Log.d("onBatchScanResults", results.size() + "");
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            Log.d("onScanFailed()", errorCode+"");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBluetoothLeScanner.stopScan(mScanCallback);
    }


    public void beaconSetting(ArrayList<Beacon> beacon,int id,ScanResult scanResult) {
        int index=0;
        if(beacons.contains(beacon.get(id))) {
            index = beacons.indexOf(beacon.get(id));
            beacons.remove(beacon.get(id));
            beacon.get(id).setRssi(scanResult.getRssi());

            beacon.get(id).born();
            if(!beacons.isEmpty()) {
                for(Beacon b:beacons) {
                    b.age();
                }
                Iterator<Beacon> iter = beacons.iterator();
                while(iter.hasNext()) {
                    if(iter.next().getLife() >= 3) {

                        iter.remove();
                        if(index != 0) {
                            index--;
                        }
                    }
                }
            }
            beacons.add(index,beacon.get(id));
            databaseReference.child("beacons").child(beacon.get(id).getAddress()).setValue(beacon.get(id));
        }
        else {
            beacon.get(id).setRssi(scanResult.getRssi());

            beacon.get(id).born();
            if(!beacons.isEmpty()) {
                for(Beacon b:beacons) {
                    b.age();
                }
                Iterator<Beacon> iter = beacons.iterator();
                while(iter.hasNext()) {
                    if(iter.next().getLife() >=3) {
                        iter.remove();
                        if(index != 0) {
                            index--;
                        }
                    }
                }
            }

            beacons.add(beacon.get(id));
            databaseReference.child("beacons").child(beacon.get(id).getAddress()).setValue(beacon.get(id));
        }
    }

}