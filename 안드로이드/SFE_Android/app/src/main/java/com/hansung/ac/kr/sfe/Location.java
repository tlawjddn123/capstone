package com.hansung.ac.kr.sfe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hansung.ac.kr.sfe.method.CreatePassNode;
import com.hansung.ac.kr.sfe.method.CreateSensors;
import com.hansung.ac.kr.sfe.model.PassNode;
import com.hansung.ac.kr.sfe.model.Sensor;
import com.hansung.ac.kr.sfe.sendReciveData.SendReceiveData;


import java.util.ArrayList;



public class Location extends View {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private Bitmap bitmap;
    private Bitmap fire;
    private int width = 1440;
    private int height = 2280;

    private Thread thread;
    private int node;
    private int userX;
    private int userY;

    public void setNode(int node) {
        this.node = node;
    }

    private int count = 0;
    private int temp = 0;
    private ArrayList<Sensor> sensors;
    private  ArrayList<Integer> passNodes;
    private  ArrayList<Integer> storeNodes;
    private  ArrayList<PassNode> passNodeArrayList;
    private SendReceiveData data;

    private CreateSensors createSensors = new CreateSensors();
    private CreatePassNode createPassNode = new CreatePassNode();

    public void setSensors(ArrayList<Sensor> sensors) {
        this.sensors = sensors;
    }

    public void setPassNodes(ArrayList<Integer> passNodes) {

        Log.d("setpassNode","-------- " + passNodes.size());
        this.passNodes = passNodes;
        this.storeNodes = passNodes;
        userX = passNodeArrayList.get(node - 1).getNodeX();
        userY = passNodeArrayList.get(node - 1).getNodeY();
    }

    public Location(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.dlc2, null);
        fire = BitmapFactory.decodeResource(context.getResources(), R.drawable.fire, null);
        sensors = createSensors.getSensors();
        passNodeArrayList = createPassNode.getNodes();

    }
    //move()------------------------------------------------------------------
    @Override
    public void onDraw(Canvas canvas) {

        float nX = (float) canvas.getWidth() / width;
        float nY = (float) canvas.getHeight() / height;
        int dynamicWidth = (int) (width * nX);
        int dynamicHeight = (int) (height * nY);

        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);

        Bitmap resize_bitmap = Bitmap.createScaledBitmap(bitmap, dynamicWidth, dynamicHeight, true);
        Rect src = new Rect(0, 0, dynamicWidth, dynamicHeight);
        Rect dst = new Rect(0, 0, dynamicWidth, dynamicHeight);
        canvas.drawBitmap(resize_bitmap, 0, 0, paint);

//------------------------------------불꽃 센서-------------------------------------------------------
        for (int i = 0; i < sensors.size(); i++) {
            //  Log.d("Location", i + " " + sensors.get(i).getSensorValue());
            if (sensors.get(i).getSensorValue().equals("on")) {
                canvas.drawBitmap(fire, sensors.get(i).getSensorX() * nX, sensors.get(i).getSensorY() * nY, paint);
            }
        }

        //---------------------------중간 노드(길찾기 기준)-------------------------


        if (count != 0) {
            Log.d("count","--------in");
            paint.setStrokeWidth(30);
            Log.d("storeSize","-------- "+ storeNodes.size());
            if(storeNodes.size() > 0) {
                for (int i = 0; i < storeNodes.size(); i++) {
                    for (int j = 0; j < passNodeArrayList.size(); j++) {

                        Log.d("for","--------in");
                        if (storeNodes.get(storeNodes.size() - 1) == 1) {
                            //1번 출구
                            canvas.drawLine(405 * nX, 400 * nY, 320 * nX, 400 * nY, paint);
                            canvas.drawLine(365 * nX, 470 * nY, 310 * nX, 392 * nY, paint);
                            canvas.drawLine(365 * nX, 330 * nY, 310 * nX, 408 * nY, paint);
                            if(storeNodes.size() == 1) {
                                paint.setColor(Color.RED);
                                canvas.drawCircle(405 * nX, userY * nY, 30, paint);
                                paint.setColor(Color.GREEN);
                            }
                        } else if (storeNodes.get(storeNodes.size() - 1) == 7) {
                            //7번 출구
                            canvas.drawLine(1020 * nX, 400 * nY, 1200 * nX, 400 * nY, paint);
                            canvas.drawLine(1155 * nX, 470 * nY, 1210 * nX, 392 * nY, paint);
                            canvas.drawLine(1155 * nX, 330 * nY, 1210 * nX, 408 * nY, paint);
                            if(storeNodes.size() == 1) {
                                paint.setColor(Color.RED);
                                canvas.drawCircle(1020 * nX, userY * nY, 30, paint);
                                paint.setColor(Color.GREEN);
                            }
                        } else if (storeNodes.get(storeNodes.size() - 1) == 13) {
                            //13번 출구
                            canvas.drawLine(550 * nX, 1300 * nY, 660 * nX, 1300 * nY, paint);
                            canvas.drawLine(615 * nX, 1370 * nY, 670 * nX, 1292 * nY, paint);
                            canvas.drawLine(615 * nX, 1230 * nY, 670 * nX, 1308 * nY, paint);
                            if(storeNodes.size() == 1) {
                                paint.setColor(Color.RED);
                                canvas.drawCircle(510 * nX, userY * nY, 30, paint);
                                paint.setColor(Color.GREEN);
                            }
                        } else if (storeNodes.get(storeNodes.size() - 1) == 17) {
                            //17번
                            canvas.drawLine(380 * nX, 2150 * nY, 150 * nX, 2150 * nY, paint);
                            canvas.drawLine(195 * nX, 2220 * nY, 140 * nX, 2142 * nY, paint);
                            canvas.drawLine(195 * nX, 2080 * nY, 140 * nX, 2158 * nY, paint);
                            if(storeNodes.size() == 1) {
                                paint.setColor(Color.RED);
                                canvas.drawCircle(380 * nX, userY * nY, 30, paint);
                                paint.setColor(Color.GREEN);
                            }
                        } else if (storeNodes.get(storeNodes.size() - 1) == 20) {
                            //20번
                            canvas.drawLine(955 * nX, 2150 * nY, 1300 * nX, 2150 * nY, paint);
                            canvas.drawLine(1255 * nX, 2220 * nY, 1310 * nX, 2142 * nY, paint);
                            canvas.drawLine(1255 * nX, 2080 * nY, 1310 * nX, 2158 * nY, paint);
                            if(storeNodes.size() == 1) {
                                paint.setColor(Color.RED);
                                canvas.drawCircle(955 * nX, userY * nY, 30, paint);
                                paint.setColor(Color.GREEN);
                            }
                        }
////////////////////////////////
                        if(storeNodes.size() > 1){
                            if (i == 1 && storeNodes.get(1) == passNodeArrayList.get(j).getNode()) {
                                Log.d("location","--------in");
                                canvas.drawLine(userX * nX, userY * nY, passNodeArrayList.get(j).getNodeX() * nX, passNodeArrayList.get(j).getNodeY() * nY, paint);
                                int tx = userX;
                                int ty =userY;
                                if(storeNodes.get(0)==12){
                                    userX=525;
                                    userY=1300;
                                }else if(storeNodes.get(0) == 6){
                                    userX=980;
                                    userY=400;
                                }

                                paint.setColor(Color.RED);
                                canvas.drawCircle(userX*nX,userY*nY,30, paint);
                                paint.setColor(Color.GREEN);
                                userX = tx;
                                userY = ty;
                                //  스타트점  User X,Y값으로
                                this.temp = j;
                            } else {
                                if(i>0 ){
                                    if (storeNodes.get(i) == passNodeArrayList.get(j).getNode()) {
                                        canvas.drawLine(passNodeArrayList.get(temp).getNodeX() * nX, passNodeArrayList.get(temp).getNodeY() * nY, passNodeArrayList.get(j).getNodeX() * nX, passNodeArrayList.get(j).getNodeY() * nY, paint);
                                        this.temp = j;
                                    }
                                }
                            }
                        }


                    }
                }
            }
            passNodes.clear();
            storeNodes.clear();
        }

        this.count++;
    }
/*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            userX = (int)event.getX();
            userY = (int)event.getY();

            int minNode = 1;
            int minDistance = Integer.MAX_VALUE;
            for(int i =0;i<passNodeArrayList.size();i++) {
                int x = passNodeArrayList.get(i).getNodeX();
                int y = passNodeArrayList.get(i).getNodeY();

                int distance = (int) Math.round(Math.sqrt(Math.pow(userX-x,2)+Math.pow(userY-y,2)));
                if(distance < minDistance) {
                    minDistance = distance;
                    minNode = passNodeArrayList.get(i).getNode();
                }

            }

            data = new SendReceiveData(this);
            data.setNode(minNode);
            data.getTh().start();
        }
        return true;
    }
*/
}
