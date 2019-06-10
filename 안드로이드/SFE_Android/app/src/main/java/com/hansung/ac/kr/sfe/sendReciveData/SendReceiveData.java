package com.hansung.ac.kr.sfe.sendReciveData;

import android.app.PendingIntent;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hansung.ac.kr.sfe.Location;
import com.hansung.ac.kr.sfe.method.CreatePassNode;
import com.hansung.ac.kr.sfe.method.CreateSensors;
import com.hansung.ac.kr.sfe.model.PassNode;
import com.hansung.ac.kr.sfe.model.Sensor;
import com.hansung.ac.kr.sfe.model.User;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.TimerTask;

public class SendReceiveData implements Runnable{//extends TimerTask{ {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private User user;
    private CreateSensors createSensors = new CreateSensors();
    private ArrayList<Sensor> sensors;
    private Thread th = new Thread(this);

    public Thread getTh() {
        return th;
    }

    private int node;

    public void setNode(int node) {
        this.node = node;
    }

    private ArrayList<Integer> passNodes = new ArrayList<>();
    private Location location;

    public SendReceiveData() {}

    public SendReceiveData(Location location) {
        this.location = location;
    }
    @Override
    public void run() {

        try {
            HttpClient http = new DefaultHttpClient();
            sensors = createSensors.getSensors();

            ArrayList<NameValuePair> postData = new ArrayList<>();

            String nodeNum = String.valueOf(node);
            UrlEncodedFormEntity request = new UrlEncodedFormEntity(postData, "utf-8");
            //192.168.0.11 //192.168.0.8 //192.168.60.152//192.168.43.152- 내폰
            String url = "http://192.168.43.152:8080/SFE/android?";
            url+="name=";
            url+="JMH"; // get 사용자 이름
            url+="&node=";
            url+= nodeNum; // get 사용자 현재 노드

            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(request);

            HttpResponse response = http.execute(httpPost);

            String body = EntityUtils.toString(response.getEntity());

            Log.d("JsonError", body.toString());
            JSONObject jsonObj = new JSONObject(body);

            if(jsonObj.get("sensorData") != null) {
                JSONArray jArray = (JSONArray) jsonObj.get("sensorData");
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject row = jArray.getJSONObject(i);
                    if(row.getInt("sensorID") == 9999){
                        continue;
                    }
                    String id = String.valueOf(row.getInt("sensorID"));
                    String sensorValue =row.getString("sensorValue");

                    sensors.get(row.getInt("sensorID")).setSensorValue(sensorValue);
                    sensors.set(row.getInt("sensorID"),sensors.get(row.getInt("sensorID")));

                    location.setSensors(sensors);
                    databaseReference.child("Sensors").child(id).setValue(sensors.get(row.getInt("sensorID")));

                }
            }
            if(jsonObj.get("nodeData") != null) {
                JSONArray jArray2 = (JSONArray) jsonObj.get("nodeData");
                for (int i = 0; i < jArray2.length(); i++) {
                    JSONObject row = jArray2.getJSONObject(i);
                    int id = row.getInt("passNode" + (i + 1));
                    passNodes.add(id);
                   // Log.d("PassNode: ", "" + passNodes.get(i));
                }

                location.setNode(passNodes.get(0));
                Log.d("PassNode: ", "" + passNodes);
                location.setPassNodes(passNodes);
            }
            location.invalidate();

            }catch (Exception e){
            e.printStackTrace();
        }
    }
}

