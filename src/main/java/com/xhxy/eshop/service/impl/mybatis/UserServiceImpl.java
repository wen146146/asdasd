package com.xhxy.eshop.service.impl.mybatis;

import com.xhxy.eshop.entity.User;
import com.xhxy.eshop.mapper.CartMapper;
import com.xhxy.eshop.mapper.UserMapper;
import com.xhxy.eshop.service.UserService;
import com.xhxy.eshop.util.MybatisUtlils;

public class UserServiceImpl implements UserService {

	private UserMapper userMapper = MybatisUtlils.getSqlSession().getMapper(UserMapper.class);
	private CartMapper cartMapper = MybatisUtlils.getSqlSession().getMapper(CartMapper.class);
	//getMapper(CartMapper.class)就是我写的接口调用，如果没有写这个，那只能写地址指定对应的xml文件来处理
	@Override
	public Integer addUser(User user) {
		// 数据库操作:
		//   1. INSERT INTO user (...) - 创建用户
		//   2. INSERT INTO cart (user_id) - 为用户创建购物车
		// 参数: user - 用户对象（需包含用户名、密码、邮箱等）
		// 返回: 1（成功）
		Integer userId = userMapper.addUser(user);
		cartMapper.add(user.getId());
		MybatisUtlils.getSqlSession().commit();
		return 1;
	}

	@Override
	public Integer login(String username, String password) {
		// 数据库操作: SELECT COUNT(*) FROM user WHERE username = ? AND password = ?
		// 参数: username - 用户名, password - 密码
		// 返回: 用户ID（登录成功），0（登录失败）
		return userMapper.login(username, password);
	}

	@Override
	public User findById(int id) {
		// 数据库操作: SELECT * FROM user WHERE id = ?
		// 参数: id - 用户ID
		// 返回: 用户对象，找不到返回null
		return userMapper.findById(id);
	}

	@Override
	public Integer update(User user) {
		// 数据库操作: UPDATE user SET ... WHERE id = ?
		// 参数: user - 用户对象（需包含ID和要修改的字段）
		// 返回: 受影响的行数（成功返回1）
		int result = userMapper.update(user);
		MybatisUtlils.getSqlSession().commit();
		return result;
	}
	@Override
	public String findPasswordById(int id) {
		// 数据库操作: SELECT password FROM user WHERE id = ?
		// 参数: id - 用户ID
		// 返回: 密码字符串，找不到返回null
		return userMapper.findPasswordById(id);
	}
}
