package cn.sagar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.sagar.User;

public class UserDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public UserDao(Connection con) {
		
		this.con = con;
	}
	
public User userLogin(String email,String password) {
	User user = null;
	try {
		query="select * from ecommerce_login where email=? and password=?";
		pst= this.con.prepareStatement(query);
		pst.setString(1, email);
		pst.setString(2, password);
		rs=pst.executeQuery();
		if(rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
		}
	}catch(Exception e) {
		//TODO:handle exception
		e.printStackTrace();
		System.out.print(e.getMessage());
	}
	return user;
}
public boolean userRegister(int id,String name, String email,String password) {
	
	try {
		query="insert into ecommerce_login values(?,?,?,?)";
		pst= this.con.prepareStatement(query);
		pst.setInt(1, id);
		pst.setString(2, name);
		pst.setString(3, email);
		pst.setString(4, password);
		pst.executeUpdate();
		return true;
	}catch(Exception e) {
		e.printStackTrace();
	}
	return true;
}
public List<User> getAllUser()
{
	List<User> userList=new ArrayList<>();
	try {
		query="select * from ecommerce_login" ;
		pst= this.con.prepareStatement(query);
		
		rs=pst.executeQuery();
		while(rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			userList.add(user);		

		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return userList;
	
}
}

