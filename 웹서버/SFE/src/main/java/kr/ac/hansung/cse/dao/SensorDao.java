package kr.ac.hansung.cse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.model.Sensor;
import kr.ac.hansung.cse.model.TempSensor;

@Repository
public class SensorDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Sensor> getFireSensors() {
		String sqlStatement = "select * from firesensor";
		return jdbcTemplate.query(sqlStatement, new RowMapper<Sensor>() {

			@Override
			public Sensor mapRow(ResultSet rs, int rowNum) throws SQLException {
				Sensor fireSensor = new Sensor();
				fireSensor.setId(rs.getInt("id"));
				fireSensor.setSensorValue(rs.getString("sensorValue"));
				fireSensor.setSensorX(rs.getInt("sensorX"));
				fireSensor.setSensorY(rs.getInt("SensorY"));
				
				return fireSensor;
			}
		});

	}
	
	

	public boolean updateFireSensor(int id, String sensorValue) {

		String sqlStatement = "update firesensor set sensorValue = ? where id = ?";
		// 리턴값 integer
		return (jdbcTemplate.update(sqlStatement, new Object[] { sensorValue, id }) == 1);

	}

	public boolean deleteFireSensor(int id) {
		String sqlStatement = "delete from firesensor where id=?";
		return (jdbcTemplate.update(sqlStatement, new Object[] { id }) == 1);
	}

	public Sensor getFireSensorById(int id) {
		String sqlStatement = "select * from firesensor where id=?";
		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { id }, new RowMapper<Sensor>() {

			@Override
			public Sensor mapRow(ResultSet rs, int rowNum) throws SQLException {
				Sensor fireSensor = new Sensor();

				fireSensor.setId(rs.getInt("id"));
				fireSensor.setSensorValue(rs.getString("sensorValue"));
				fireSensor.setSensorX(rs.getInt("sensorX"));
				fireSensor.setSensorY(rs.getInt("sensorY"));

				return fireSensor;
			}
		});
	}

	public List<Sensor> getOnFireSensors() {
		String sqlStatement = "select * from firesensor where sensorValue = 'on'";
		return jdbcTemplate.query(sqlStatement, new RowMapper<Sensor>() {

			@Override
			public Sensor mapRow(ResultSet rs, int rowNum) throws SQLException {
				Sensor fireSensor = new Sensor();
				fireSensor.setId(rs.getInt("id"));
				fireSensor.setSensorValue(rs.getString("sensorValue"));
				fireSensor.setSensorX(rs.getInt("sensorX"));
				fireSensor.setSensorY(rs.getInt("SensorY"));
				
				return fireSensor;
			}
		});
	}

	public List<TempSensor> getTempSensors() {
		String sqlStatement = "select * from tempsensor";
		return jdbcTemplate.query(sqlStatement, new RowMapper<TempSensor>() {

			@Override
			public TempSensor mapRow(ResultSet rs, int rowNum) throws SQLException {
				TempSensor tempSensor = new TempSensor();
				tempSensor.setId(rs.getInt("id"));
				tempSensor.setTempValue(rs.getString("sensorValue"));
				
				return tempSensor;
			}
		});
	}

	public boolean updateTempSensor(int id, String sensorValue) {
		String sqlStatement = "update tempsensor set sensorValue = ? where id = ?";
		// 리턴값 integer
		return (jdbcTemplate.update(sqlStatement, new Object[] { sensorValue, id }) == 1);
	}


}