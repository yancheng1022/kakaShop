package web.service;

import java.sql.SQLException;

import dao.UserDao;
import domain.User;

public class UserService {


	public boolean register(User user) {
		UserDao dao = new UserDao();
		int row = 0;
		try {
			row = dao.register(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return row>0?true:false;
	}

	//激活
	public void active(String activeCode) throws SQLException {
		UserDao dao = new UserDao();
		dao.active(activeCode);
	}
	
	//校验用户名是否存在
	public boolean checkUsername(String username) {
		UserDao dao = new UserDao();
		Long isExist = 0L;
		try {
			isExist = dao.checkUsername(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isExist>0?true:false;
	}

	public User checkUser(String username, String password) {
		UserDao dao = new UserDao();
			User user=null;
		try {
			user = dao.checkUser(username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user!=null){
			return user;
		}
		return null;
		
	}

}
