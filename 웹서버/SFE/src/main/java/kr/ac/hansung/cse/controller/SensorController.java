package kr.ac.hansung.cse.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.cse.method.SendSMS;
import kr.ac.hansung.cse.model.Sensor;
import kr.ac.hansung.cse.model.TempSensor;
import kr.ac.hansung.cse.model.User;
import kr.ac.hansung.cse.service.SensorService;
import kr.ac.hansung.cse.service.UserService;

@Controller
public class SensorController {
	private int count = 0;
	
	@Autowired
	private SensorService sensorService;
	
	@Resource
	private UserController userController;

	@Resource
	private ToAndroidController toAndroidController;
	
	@RequestMapping("/fireSensors")
	public String getFireSensors(Model model) {
		List<Sensor> fireSensors = sensorService.getFireSensors();
		model.addAttribute("fireSensors", fireSensors);
		
		return "fireSensors"; //view
	}

	@RequestMapping("/fireReset")
	public String setFireReset() {
		for(int i = 0; i < 11; i++) {
			sensorService.updateFireSensor(i, "off");
		}
		this.count = 0;
		return "redirect:location";
	}
	
	@RequestMapping("/location")
	public String getInfo(Model model) {
		
		List<Sensor> fireSensors = sensorService.getFireSensors();

		//sms보내기, androidPush
		for(int i = 0; i<fireSensors.size(); i++) {
			if(fireSensors.get(i).getSensorValue().equals("on")) {
				if(count == 0) {
					SendSMS sms = new SendSMS();
					sms.sendSMS();
					try {
						toAndroidController.sendFCM();
						System.out.println(" After fcm:"+ count);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				this.count++;
			}
		}

		model.addAttribute("fireSensors", fireSensors);
		model.addAttribute("overlapUsers",userController.overlapInfo());
		System.out.println(userController.overlapInfo());
		
		return "location"; //view
	}
	
	@RequestMapping(value = "/updateTempSensor", method = RequestMethod.POST, produces = { "application/json" })
	public /* Map<String, Object> */String makerepo1(@RequestBody Map<String, Object> info) {
		List<TempSensor> tempSensors = sensorService.getTempSensors();
		if(info.size() > 0) {
			for(int i = 0; i< info.size(); i++) {
				for(int j = 0; j < tempSensors.size(); j++) {
					if(tempSensors.get(j).getId() == Integer.parseInt(info.get("id"+i).toString())) {
						if(!sensorService.updateTempSensor(tempSensors.get(j).getId(), "on"))
							System.out.println("Adding tempSensor cannot be done!");
					}
				}
			}
		}
		
		return "empty";
	}
	
	@RequestMapping(value = "/updateFireSensor", method = RequestMethod.POST, produces = { "application/json" })
	public /* Map<String, Object> */String makerepo(@RequestBody Map<String, Object> info) {
		List<Sensor> fireSensors = sensorService.getFireSensors();
		
		if(info.size() > 0) {
			for(int i = 0; i <  info.size(); i++) {
				for(int j = 0; j < fireSensors.size(); j++) {
					System.out.println(fireSensors.get(j).getId()+"///"+Integer.parseInt(info.get("id"+i).toString()));
					if(fireSensors.get(j).getId() == Integer.parseInt(info.get("id"+i).toString())) {
						if(!sensorService.updateFireSensor(fireSensors.get(j).getId(), "on"))
							System.out.println("Adding fireSensor cannot be done!");
					}
				}
			}
		}
		return "empty";

	}
	
	
	
	@RequestMapping("/empty")
	public String empty() {
		return "empty";
	}
	

}