package kr.ac.hansung.cse.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.cse.method.Graph;
import kr.ac.hansung.cse.model.Sensor;
import kr.ac.hansung.cse.model.User;
import kr.ac.hansung.cse.service.SensorService;
import kr.ac.hansung.cse.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Resource
	private SensorService sensorService;
	

	private ArrayList<Boolean> overList;
	
	@RequestMapping(value="/randomLocation")
	public String randomLocation() {
		List<User> users = userService.getUsers();
		for(int i = 0; i < users.size(); i++) {
			userService.updateUserNode(i+1, (int)(Math.random()*20)+1);
		}
		return "redirect:location";
	}
	public List<User> getInfo() {
		List<User> users = userService.getUsers();
		
		return users;
	}
	public User getUserByName(String name) {
		User users = userService.getUserByName(name);
		
		return users;
	}
	
	public List<User> overlapInfo(){
		List<User> users = userService.overlapInfo();
		return users;
	}
	public List<User> unityInfo(){
		List<User> users = userService.unityInfo();
		return users;
	}
	public boolean addUser(String name, int node) {
		// TODO Auto-generated method stub
		return userService.addUser(name, node);
	}
	
	public List<Integer> findLoacation(String name) {
		List<Sensor> fireSensors = sensorService.getFireSensors();
		User user = getUserByName(name);
		overList = new ArrayList<Boolean>();
	
		for(int i = 0; i < fireSensors.size(); i++) {
			overList.add(i, false);
		}
			
			
		//길찾기
		Graph g = new Graph(20);
		g.input(1, 2, 1); g.input(2, 3, 1);
		g.input(3, 4, 2); g.input(4, 5, 3);
		g.input(5, 6, 3); g.input(6, 7, 3);
		g.input(3, 8, 2); g.input(8, 9, 1);
		g.input(9, 10, 2); g.input(10, 11, 1);
		g.input(11, 12, 1); g.input(12, 13, 1);
		g.input(12, 14, 3); g.input(14, 15, 1);
		g.input(15, 16, 1); g.input(16, 18, 2);
		g.input(17, 18, 2); g.input(16, 19, 3);
		g.input(19, 20, 2);
		
		for(int j = 0; j < overlapInfo().size(); j++) {
			if(overlapInfo().get(j).getCount() >= 5) {
				if(overlapInfo().get(j).getNode()==1)overList.add(0,true);
				if(overlapInfo().get(j).getNode()==3)overList.add(1,true);
				if(overlapInfo().get(j).getNode()==5)overList.add(2,true);
				if(overlapInfo().get(j).getNode()==7)overList.add(3,true);
				if(overlapInfo().get(j).getNode()==9)overList.add(4,true);
				if(overlapInfo().get(j).getNode()==11)overList.add(5,true);
				if(overlapInfo().get(j).getNode()==13)overList.add(6,true);
				if(overlapInfo().get(j).getNode()==14)overList.add(7,true);
				if(overlapInfo().get(j).getNode()==16)overList.add(8,true);
				if(overlapInfo().get(j).getNode()==17)overList.add(9,true);
				if(overlapInfo().get(j).getNode()==20)overList.add(10,true);
				if(overlapInfo().get(j).getNode()==2) {
					g.input(1, 2, 25); g.input(2, 3, 25);
				}
				if(overlapInfo().get(j).getNode()==4) {
					g.input(3, 4, 25); g.input(4, 5, 25);
				}
				if(overlapInfo().get(j).getNode()==6) {
					g.input(5, 6, 25); g.input(6, 7, 25);
				}
				if(overlapInfo().get(j).getNode()==8) {
					g.input(3, 8, 25); g.input(8, 9, 25);
				}
				if(overlapInfo().get(j).getNode()==10) {
					g.input(9, 10, 25); g.input(10, 11, 25);
				}
				if(overlapInfo().get(j).getNode()==12) {
					g.input(11, 12, 25); g.input(12, 13, 25); g.input(12, 14, 25);
				}
				if(overlapInfo().get(j).getNode()==15) {
					g.input(14, 15, 25); g.input(15, 16, 25);
				}
				if(overlapInfo().get(j).getNode()==18) {
					g.input(17, 18, 25); g.input(16, 18, 25);
				}
				if(overlapInfo().get(j).getNode()==19) {
					g.input(18, 19, 25); g.input(19, 20, 25);
				}
			}
		}
		
		
		int n = 1;
		if(fireSensors.get(0).getSensorValue().equals("on") || overList.get(0)) {
			n=1;
			if(overList.get(0))n=4;
			g.input(1, 2, 100/n);
		}
		if(fireSensors.get(1).getSensorValue().equals("on") || overList.get(1)) {
			n=1;
			if(overList.get(1))n=4;
			g.input(2, 3, 100/n); g.input(3, 4, 100/n); g.input(3, 8, 100/n);
		}
		if(fireSensors.get(2).getSensorValue().equals("on") || overList.get(2)) {
			n=1;
			if(overList.get(2))n=4;
			g.input(4, 5, 100/n); g.input(5, 6, 100/n);
		}
		if(fireSensors.get(3).getSensorValue().equals("on") || overList.get(3)) {
			n=1;
			if(overList.get(3))n=4;
			g.input(6, 7, 100/n);
		}
		if(fireSensors.get(4).getSensorValue().equals("on") || overList.get(4)) {
			n=1;
			if(overList.get(4))n=4;
			g.input(8, 9, 100/n); g.input(9, 10, 100/n);
		}
		if(fireSensors.get(5).getSensorValue().equals("on") || overList.get(5)) {
			n=1;
			if(overList.get(5))n=4;
			g.input(10, 11, 100/n); g.input(11, 12, 100/n);
		}
		if(fireSensors.get(6).getSensorValue().equals("on") || overList.get(6)) {
			n=1;
			if(overList.get(6))n=4;
			g.input(12, 13, 100/n);
		}
		if(fireSensors.get(7).getSensorValue().equals("on") || overList.get(7)) {
			n=1;
			if(overList.get(7))n=4;
			g.input(12, 14, 100/n); g.input(14, 15, 100/n);
		}
		if(fireSensors.get(8).getSensorValue().equals("on") || overList.get(8)) {
			n=1;
			if(overList.get(8))n=4;
			g.input(15, 16, 100/n); g.input(18, 16, 100/n);
			g.input(16, 19, 100/n);
		}
		if(fireSensors.get(9).getSensorValue().equals("on") || overList.get(9)) {
			n=1;
			if(overList.get(9))n=4;
			g.input(17, 18, 100/n);
		}if(fireSensors.get(10).getSensorValue().equals("on") || overList.get(10)) {
			n=1;
			if(overList.get(10))n=4;
			g.input(19, 20, 100/n);
		}
		
		
		// 탈출구 노드 1, 7, 11, 17, 20
		return g.dijkstra(user.getNode());		
	}

}
