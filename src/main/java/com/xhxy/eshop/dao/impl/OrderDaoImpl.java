package com.xhxy.eshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xhxy.eshop.dao.AddressDao;
import com.xhxy.eshop.dao.OrderDao;
import com.xhxy.eshop.dao.OrderItemDao;
import com.xhxy.eshop.dao.UserDao;
import com.xhxy.eshop.entity.Address;
import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.CartItem;
import com.xhxy.eshop.entity.Order;
import com.xhxy.eshop.entity.OrderItem;
import com.xhxy.eshop.entity.Status;
import com.xhxy.eshop.util.JdbcUtils;

public class OrderDaoImpl implements OrderDao {

	@Override
	public List<Order> getByUserId(Integer userId) {
		// SQL：查询某个用户的全部订单
		String sql = "select * from orders where user_id=?"; //数据库表名orders，不能是order（因为order是关键字）
		try(
			Connection conn = JdbcUtils.getConnection();	//JdbcUtils的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
				)
		{
			pstmt.setInt(1, userId);	// sql中的?
			ResultSet rs = pstmt.executeQuery();
			List<Order> orderList = new ArrayList<Order>();
			while(rs != null && rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setCreateTime(rs.getDate("createtime"));
				order.setStatus(Status.values()[rs.getInt("status")]);	// 将数据库中的整型转为Java枚举型
				order.setTotal(rs.getFloat("total"));
				
				UserDao userDao = new UserDaoImpl();
				order.setUser(userDao.findById(userId));	// 该订单的用户
				
				OrderItemDao orderItemDao = new OrderItemDaoImpl();
				Integer orderId = userId;
				order.setOrderItemList(orderItemDao.findByOrderId(orderId));	// 全部的订单项
				
				orderList.add(order);	// 将该订单加入该用户的订单列表
			}
			return orderList;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Integer save(Order order) {
		// SQL：新增某个order
		String sql = "insert into orders(user_id,address_id,total,status,createtime) value(?,?,?,?,?)"; //数据库表名orders，不能是order（因为order是关键字）
		try(
			Connection conn = JdbcUtils.getConnection();//JdbcUtils的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)
				)
		{
			pstmt.setInt(1, order.getUser().getId());
			pstmt.setInt(2, order.getAddress().getId());
			pstmt.setFloat(3, order.getTotal());
			pstmt.setInt(4, order.getStatus().getIndex());
			pstmt.setDate(5, new java.sql.Date(order.getCreateTime().getTime()));
			pstmt.executeUpdate();
			
			ResultSet rs= pstmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);// 获取所新增加的订单行id号
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回存储后的order行的id字段
	 */
	@Override
	public Integer saveFromCart(Cart cart,Address address) {
		// SQL：新增某个order
		String sql = "insert into orders(user_id,total,address_id,status,createtime) values(?,?,?,?,?)"; //数据库表名orders，不能是order（因为order是关键字）
		try(
			Connection conn = JdbcUtils.getConnection();//JdbcUtils的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)
				)
		{
			pstmt.setInt(1, cart.getUser().getId());
			pstmt.setFloat(2, cart.getTotal());
			pstmt.setInt(3, address.getId());
			pstmt.setInt(4, Status.getStatusIndex("已完成"));
//			pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
			pstmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			int rows = pstmt.executeUpdate();
			
			ResultSet rs= pstmt.getGeneratedKeys();
            rs.next();
            int orderId = rs.getInt(1);// 获取所新增加的订单行id号
			
			List<CartItem> cartItemList = cart.getCartItemList();
			for(CartItem cartItem:cartItemList) {
				String itemSql = "insert into orderitem(product_id,quantity,order_id,total) values(?,?,?,?)";
				PreparedStatement itmePstmt = conn.prepareStatement(itemSql);
				itmePstmt.setInt(1, cartItem.getProduct().getId());
				itmePstmt.setInt(2, cartItem.getQuantity());
				itmePstmt.setInt(3, orderId);
				itmePstmt.setFloat(4, cartItem.getTotal());
				itmePstmt.executeUpdate();				
			}
			
			if(rows > 0 ) {
				return orderId;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Order findById(Integer orderId) {
		// SQL：查询某个订单
		String sql = "select * from orders where id = ?";   //数据库表名orders，不能是order（因为order是关键字）
		try(
			Connection conn = JdbcUtils.getConnection();//JdbcUtils的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
				)
		{
			pstmt.setInt(1, orderId);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));					// id
				order.setCreateTime(rs.getDate("createtime"));	// createTime:下单时间
				order.setTotal(rs.getFloat("total"));			// total:总额
				
				UserDao userDao = new UserDaoImpl();
				order.setUser(userDao.findById(rs.getInt("user_id")));	// user:下单的用户
				
				OrderItemDao orderItemDao = new OrderItemDaoImpl();
				order.setOrderItemList(orderItemDao.findByOrderId(orderId));// orderItemList:订单项
				
				AddressDao addressDao = new AddressDaoImpl();
				order.setAddress(addressDao.findById(rs.getInt("address_id")));// address:收货地址
				
				return order;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
