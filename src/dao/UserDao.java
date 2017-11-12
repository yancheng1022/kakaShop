package dao;

import java.sql.SQLException;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import domain.User;
import utils.DataSourceUtils;

public class UserDao {

	public int register(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		int update = qr.update(sql, user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
		return update;
	}

	//¼¤»î
	public void active(String activeCode) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="update user set state=? where code=?";
		qr.update(sql,"1",activeCode);
	}

	public Long checkUsername(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select count(*) from user where username=?";
		long query = (long) qr.query(sql, new ScalarHandler(),username);
		return query;
	}

	public User checkUser(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from user where username=? and password=?";
		long query =(long) qr.query(sql, new ScalarHandler(),username,password);
		if(query>0){
			String sql2 = "select * from user where username=?";
			User user = qr.query(sql2, new BeanHandler<User>(User.class),username);
			
			return user;
		}
		return null;
	}

}
