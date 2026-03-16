package com.xhxy.eshop.dao;

import java.util.List;

import com.xhxy.eshop.entity.Address;
import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.Order;

public interface OrderDao{
	
	// 根据用户id获取其全部的Order
	public List<Order> getByUserId(Integer userId);
	
	// 根据订单id获取Order
	public Order findById(Integer orderId);
	
	
	// 从购物车对象生成订单
	public Integer saveFromCart(Cart cart,Address address);
	
	// 保存（插入）一个Order订单(完整数据)，返回新插入的id值（自增长）
	public Integer save(Order order);
	
}
