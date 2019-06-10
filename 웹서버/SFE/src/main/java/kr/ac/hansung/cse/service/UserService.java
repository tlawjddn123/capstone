package kr.ac.hansung.cse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.UserDao;
import kr.ac.hansung.cse.model.User;


@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		return userDao.getUserByName(name);
	}

	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userDao.getUsers();
	}

	public List<User> overlapInfo() {
		// TODO Auto-generated method stub
		return userDao.overlapInfo();
	}

	public List<User> unityInfo() {
		// TODO Auto-generated method stub
		return userDao.unityInfo();
	}

	public boolean addUser(String name, int node) {
		// TODO Auto-generated method stub
		for(int i = 0; i < getUsers().size(); i++) {
			if(getUsers().get(i).getName().equals(name)) {
				System.out.println(name);
				return userDao.updateUserInfo(name, node);
			}
		}
		return userDao.createUserInfo(name, node);
		
		
	}
	
	public void updateUserNode(int id, int node) {
		userDao.updateUserNode(id, node);
	}
}
