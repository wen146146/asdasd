package com.xhxy.eshop.service;

import com.xhxy.eshop.entity.User;

public interface UserService {
	// 添加用户（注册）
	public Integer addUser(User user);
	// 登录login
	public Integer login(String username,String password);
	// 查询用户
	public User findById(int id);
	// 修改用户信息
	public Integer update(User user);
	// 用id获取该用户的密码
	public String findPasswordById(int id);
	
}
