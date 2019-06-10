package kr.ac.hansung.cse.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.cse.model.Sensor;
import kr.ac.hansung.cse.service.SensorService;
import kr.ac.hansung.cse.service.UserService;



@Controller
public class ToAndroidController {

	@Autowired
	private SensorService sensorService;
	
	@Resource
	private UserController userController;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/android")
	public @ResponseBody JSONObject toAndroid(@RequestParam("name") String name,  @RequestParam("node") String node) {
		
		System.out.println("데이터옴 "+ name+ node);
		if(!userController.addUser(name, Integer.parseInt(node)))
			System.out.println("유저 추가 오류입니다.");
		
		JSONObject jsonMain = new JSONObject(); // json 객체
		JSONArray jArray = new JSONArray(); // json배열
       
		//sensor
        //List<Sensor> items = sensorService.getOnFireSensors();
        List<Sensor> items = sensorService.getFireSensors();
        if(items.size() != 0) {
        	for(int i = 0; i < items.size(); i++) {
        		Sensor sensor = items.get(i);
        		JSONObject row1 = new JSONObject();
        	
        		row1.put("sensorID", sensor.getId());
        		row1.put("sensorValue", sensor.getSensorValue());
        	
        		jArray.add(i,row1);
        	}
        
        	jsonMain.put("sensorData", jArray);
        }else {
        	JSONObject row1 = new JSONObject();
        	row1.put("sensorID", 9999);
        	row1.put("sensorValue", "safe");
        	jArray.add(row1);
        	jsonMain.put("sensorData", jArray);
        }
        
        JSONArray jArray2 = new JSONArray();
        List<Integer> passNode = userController.findLoacation(name);
        for(int i = 0; i < passNode.size(); i++) {
        	System.out.print(" passNode: "+passNode.get(i));
        	JSONObject row2 = new JSONObject();
        	
        	row2.put("passNode"+(i+1), passNode.get(i));
        	jArray2.add(i, row2);
        }
        jsonMain.put("nodeData", jArray2);
        System.out.println("");
        //passNode
        
		return jsonMain;
	}
	
//---------------Push Message---------------------------------
	public void sendFCM()
			throws Exception {

		/*List<MobileTokenVO> tokenList = fcmService.loadFCMInfoList(vo);
		String token = tokenList.get(count).getDEVICE_ID();*/

		final String apiKey = "AAAAlVFWjjw:APA91bHFA1fD1_i_q2PW__pZFhG4V04WTcnIqGqODyndsCYlOTQp6o3jIxc7RsSt3cWODZVbf9LV_e2UITlxLJXW8BElgPMKTkVNuMZThuj4Pwuq5GYkI34Eh2wkewC_EjAYCxY9gCGK";
		URL url = new URL("https://fcm.googleapis.com/fcm/send");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "key=" + apiKey);

		conn.setDoOutput(true);


		// 이렇게 보내면 주제를 ALL로 지정해놓은 모든 사람들한테 알림을 날려준다.
		String input = "{\"notification\" : {\"title\" : \"SFE \", \"body\" : \"화재 발생!!\n신속히 대피해주세요!!\"}, \"to\":\"/topics/ALL\"}";

		// 이걸로 보내면 특정 토큰을 가지고있는 어플에만 알림을 날려준다 위에 둘중에 한개 골라서 날려주자
		//String input = "{\"notification\" : {\"title\" : \" 여기다 제목넣기 \", \"body\" : \"여기다 내용 넣기\"}, \"to\":\" 여기가 받을 사람 토큰  \"}";

		OutputStream os = conn.getOutputStream();

		// 서버에서 날려서 한글 깨지는 사람은 아래처럼 UTF-8로 인코딩해서 날려주자
		
		os.write(input.getBytes("UTF-8"));
		os.flush();
		os.close();

		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + input);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// print result
		System.out.println(response.toString());

	}


}
