package kr.ac.hansung.cse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.SensorDao;
import kr.ac.hansung.cse.model.Sensor;
import kr.ac.hansung.cse.model.TempSensor;

@Service
public class SensorService {

	@Autowired
	private SensorDao sensorDao;
	public List<Sensor> getOnFireSensors(){
		return sensorDao.getOnFireSensors();
	}
	
	public List<Sensor> getFireSensors(){
		return sensorDao.getFireSensors();
	}

	public boolean updateFireSensor(int id, String sensorValue) {
		// TODO Auto-generated method stub
		return sensorDao.updateFireSensor(id, sensorValue);
	}


	public Sensor getFireSensorById(int id) {
		// TODO Auto-generated method stub
		return sensorDao.getFireSensorById(id);
	}

	public List<TempSensor> getTempSensors() {
		// TODO Auto-generated method stub
		return sensorDao.getTempSensors();
	}
	public boolean updateTempSensor(int id, String sensorValue) {
		// TODO Auto-generated method stub
		return sensorDao.updateTempSensor(id, sensorValue);
	}
}
