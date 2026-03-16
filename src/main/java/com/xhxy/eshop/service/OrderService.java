package com.xhxy.eshop.service;

import java.util.List;

import com.xhxy.eshop.entity.Address;
import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.Order;

public interface OrderService {
	// 根据用户id获取全部的Order
	public List<Order> getByUserId(Integer userId);
	
	// 根据用户id获取Order
	public Order findById(Integer orderId);
	
	// 从购物车+收货地址创建订单
	public Integer createOrder(Cart cart,Address address);
}
