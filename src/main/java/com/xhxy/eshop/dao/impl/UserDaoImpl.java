package com.xhxy.eshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.xhxy.eshop.dao.UserDao;
import com.xhxy.eshop.entity.User;
import com.xhxy.eshop.util.JdbcUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public Integer addUser(User user) {
		String sql = "insert into user(username,password,phone,email,avatar) values(?,?,?,?,?)";
		try(
			Connection conn = JdbcUtils.getConnection();//JDBC工具类的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)
				)
		{
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getPhone());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getAvatar());
			
			pstmt.executeUpdate();
			
			ResultSet rs= pstmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);	// 获取所新增加的User行id号
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public Integer login(String username, String password) {
		String sql = "select * from user where username=? and password=?";
		try(
			Connection conn = JdbcUtils.getConnection();//JDBC工具类的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
				)
		{
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()) {
				return rs.getInt("id");
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	@Override
	public User findById(int id) {
		String sql = "select * from user where id = ?";
		try(
			Connection conn = JdbcUtils.getConnection();	//JDBC工具类的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
				)
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()) {
				User user = new User();
				user.setId(id);
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setAvatar(rs.getString("avatar"));
				return user;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer update(User user) {
		// 根据user的属性情况，拼接sql
		String sql = "update user set username = " + user.getUsername() + 
				",email = " + user.getEmail() + ",phone = " + user.getPhone() ;
		
		if(user.getAvatar() != null)
			sql = sql + ",avatar = " + user.getAvatar();
		
		if(user.getPassword()!= null)
			sql = sql + ",password = " + user.getPassword();
		
		sql = sql + " where id = " + user.getId();

		try(
			Connection conn = JdbcUtils.getConnection();	//JdbcUtils的静态方法
			Statement stmt = conn.createStatement()
				)
		{
			 
			return stmt.executeUpdate(sql);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public String findPasswordById(int id) {
		String sql = "select password from user where id=?";
		try(
			Connection conn = JdbcUtils.getConnection();	//JDBC工具类的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
				)
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()) {
				return rs.getString("password");
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
