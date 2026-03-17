package com.xhxy.eshop.mapper;

import org.apache.ibatis.annotations.Param;

import com.xhxy.eshop.entity.User;

public interface UserMapper {
	// 数据库操作: INSERT INTO user (...)
	// 参数: user - 用户对象
	// 返回: 受影响的行数（成功返回1）
	Integer addUser(User user);
	
	// 数据库操作: SELECT id FROM user WHERE username = ? AND password = ?
	// 参数: username - 用户名, password - 密码
	// 返回: 用户ID（登录成功），0（登录失败）
	Integer login(@Param("username") String username, @Param("password") String password);
	
	// 数据库操作: SELECT * FROM user WHERE id = ?
	// 参数: id - 用户ID
	// 返回: 用户对象，找不到返回null
	User findById(int id);
	
	// 数据库操作: UPDATE user SET ... WHERE id = ?
	// 参数: user - 用户对象
	// 返回: 受影响的行数（成功返回1）
	Integer update(User user);
	
	// 数据库操作: SELECT password FROM user WHERE id = ?
	// 参数: id - 用户ID
	// 返回: 密码字符串，找不到返回null
	String findPasswordById(int id);

	// Integer dhczid()
}
