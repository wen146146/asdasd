package com.xhxy.eshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xhxy.eshop.dao.CartDao;
import com.xhxy.eshop.dao.CartItemDao;
import com.xhxy.eshop.dao.UserDao;
import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.util.JdbcUtils;

public class CartDaoImpl implements CartDao {
	// 根据用户Id，返回其Cart
	@Override
	public Cart findByUserId(Integer userId) {

		// SQL：查询某个用户的购物车
		String sql = "select * from cart where user_id=?";
		try(
			Connection conn = JdbcUtils.getConnection();	//静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
				)
		{
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()) {
				Cart cart = new Cart();
				cart.setId(rs.getInt("id"));
				cart.setTotal(rs.getFloat("total"));
				
				UserDao userDao = new UserDaoImpl();
				cart.setUser(userDao.findById(userId));
				
				CartItemDao cartItemDao = new CartItemDaoImpl();
				Integer cartId = userId;
				cart.setCartItemList(cartItemDao.findByCartId(cartId));
				
				return cart;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer updateTotal(Integer cartId, Float total) {
		String cartSql = "update cart set total = ? where id = ?";
		try(
			Connection conn = JdbcUtils.getConnection();	// JdbcUtils的静态方法
			PreparedStatement pstmt = conn.prepareStatement(cartSql)
			)
		{
			pstmt.setFloat(1, total);
			pstmt.setInt(2, cartId);
			return pstmt.executeUpdate(); 	// 返回更新的行数
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public Cart findById(Integer id) {
		// SQL：查询某个cart
		String sql = "select * from cart where id = ?";
		try(
			Connection conn = JdbcUtils.getConnection();	//静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
				)
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()) {
				Cart cart = new Cart();
				cart.setId(rs.getInt("id"));
				cart.setTotal(rs.getFloat("total"));
				
				UserDao userDao = new UserDaoImpl();
				cart.setUser(userDao.findById(rs.getInt("user_id")));
				
				CartItemDao cartItemDao = new CartItemDaoImpl();
				cart.setCartItemList(cartItemDao.findByCartId(id));
				
				return cart;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer add(Integer userId) {
		String sql = "insert into cart(total,user_id) values(?,?)";
		try(
			Connection conn = JdbcUtils.getConnection();	//JDBC工具类的静态方法，获得连接
			PreparedStatement pstmt = conn.prepareStatement(sql)	// 从字符串生成
				)
		{
			pstmt.setFloat(1, 0);		// 总额设为0
			pstmt.setInt(2, userId);	// 所属的用户id
			
			return pstmt.executeUpdate();	// 执行sql语句
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

}
