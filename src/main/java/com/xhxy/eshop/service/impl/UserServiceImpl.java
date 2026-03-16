package com.xhxy.eshop.service.impl;

import com.xhxy.eshop.dao.CartDao;
import com.xhxy.eshop.dao.UserDao;
import com.xhxy.eshop.dao.impl.CartDaoImpl;
import com.xhxy.eshop.dao.impl.UserDaoImpl;
import com.xhxy.eshop.entity.User;
import com.xhxy.eshop.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();	// 需要用到下层的UserDao和CartDao
	private CartDao cartDao = new CartDaoImpl();
	
	@Override
	public Integer addUser(User user) {
		Integer userId = userDao.addUser(user);	// 返回新增用户的id
		cartDao.add(userId);	// 在cart购物车表中，新增该用户的购物车行
		return 1;
	}

	@Override
	public Integer login(String username, String password) {
		return userDao.login(username, password);
	}

	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}

	@Override
	public Integer update(User user) {
		return userDao.update(user);
	}

	@Override
	public String findPasswordById(int id) {
		return userDao.findPasswordById(id);
	}

}
