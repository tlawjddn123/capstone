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
import kr.ac.hansung.cse.model.User;

@Repository
public class UserDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<User> getUsers() {
		String sqlStatement = "select * from userlocation";
		return jdbcTemplate.query(sqlStatement, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setNode(rs.getInt("node"));
				return user;
			}
		});
	}

	public User getUserByName(String name) {
		String sqlStatement = "select * from userlocation where name=?";
		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { name }, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setNode(rs.getInt("node"));
				return user;
			}
		});
	}

	public List<User> overlapInfo() {
		String sqlStatement = "SELECT *, count(*)as count FROM userlocation group by node having count>1";
	
		return jdbcTemplate.query(sqlStatement, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setCount(rs.getInt("count"));
				user.setNode(rs.getInt("node"));
				return user;
			}
		});
	}

	public List<User> unityInfo() {
		String sqlStatement = "SELECT *, count(*)as count FROM userlocation group by node having count=1";

		return jdbcTemplate.query(sqlStatement, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setCount(rs.getInt("count"));
				user.setNode(rs.getInt("node"));
				return user;
			}
		});
	}

	public boolean createUserInfo(String name, int node) {
		// TODO Auto-generated method stub
		String sqlStatement = "insert into userlocation(name, node) values(?, ?);";
		
		// 리턴값 integer
		return (jdbcTemplate.update(sqlStatement, new Object[] { name, node }) == 1);
	}

	public boolean updateUserInfo(String name, int node) {
		// TODO Auto-generated method stub
		String sqlStatement = "update userlocation set node = ? where name = ?";
		// 리턴값 integer
		return (jdbcTemplate.update(sqlStatement, new Object[] { node, name }) == 1);
	}
	
	public boolean updateUserNode(int id, int node) {
		// TODO Auto-generated method stub
		String sqlStatement = "update userlocation set node = ? where id = ?";
		// 리턴값 integer
		return (jdbcTemplate.update(sqlStatement, new Object[] { node, id }) == 1);
	}

}
