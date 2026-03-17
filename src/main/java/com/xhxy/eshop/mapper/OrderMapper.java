package com.xhxy.eshop.mapper;

import com.xhxy.eshop.entity.Address;
import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.Order;
import java.util.List;

public interface OrderMapper {
	// 数据库操作: SELECT * FROM order WHERE user_id = ?
	// 参数: userId - 用户ID
	// 返回: 该用户的所有订单列表
	List<Order> getByUserId(Integer userId);
	
	// 数据库操作: SELECT * FROM order WHERE id = ?
	// 参数: orderId - 订单ID
	// 返回: 订单对象，找不到返回null
	Order findById(Integer orderId);
	
	// 数据库操作: INSERT INTO order (...)，从购物车创建订单
	// 参数: cart - 购物车对象, address - 地址对象
	// 返回: 新订单ID
	Integer saveFromCart(Cart cart, Address address);
	
	// 数据库操作: INSERT INTO order (...)
	// 参数: order - 订单对象
	// 返回: 受影响的行数（成功返回1）
	Integer save(Order order);
}
