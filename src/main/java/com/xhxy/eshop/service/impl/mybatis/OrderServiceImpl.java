package com.xhxy.eshop.service.impl.mybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xhxy.eshop.entity.Address;
import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.CartItem;
import com.xhxy.eshop.entity.Order;
import com.xhxy.eshop.entity.OrderItem;
import com.xhxy.eshop.entity.Status;
import com.xhxy.eshop.mapper.CartItemMapper;
import com.xhxy.eshop.mapper.CartMapper;
import com.xhxy.eshop.mapper.OrderItemMapper;
import com.xhxy.eshop.mapper.OrderMapper;
import com.xhxy.eshop.service.OrderService;
import com.xhxy.eshop.util.MybatisUtlils;

public class OrderServiceImpl implements OrderService {

	private OrderMapper getOrderMapper() {
		return MybatisUtlils.getSqlSession().getMapper(OrderMapper.class);
	}
	
	private OrderItemMapper getOrderItemMapper() {
		return MybatisUtlils.getSqlSession().getMapper(OrderItemMapper.class);
	}
	
	private CartMapper getCartMapper() {
		return MybatisUtlils.getSqlSession().getMapper(CartMapper.class);
	}
	
	private CartItemMapper getCartItemMapper() {
		return MybatisUtlils.getSqlSession().getMapper(CartItemMapper.class);
	}

	@Override
	public Integer createOrder(Cart cart, Address address) {
		// 数据库操作: 
		//   1. INSERT INTO order (...) - 创建订单
		//   2. SELECT * FROM cart_item WHERE cart_id = ? - 获取购物车项
		//   3. INSERT INTO order_item (...) - 批量插入订单项
		//   4. DELETE FROM cart_item WHERE cart_id = ? - 清空购物车项
		//   5. UPDATE cart SET total = 0 WHERE id = ? - 重置购物车总价
		// 参数: cart - 购物车对象, address - 地址对象
		// 返回: 新创建的订单ID
		Order order = new Order();
		order.setStatus(Status.pending);
		order.setAddress(address);
		order.setCreateTime(new Date());
		order.setTotal(cart.getTotal());
		order.setUser(cart.getUser());
		
		getOrderMapper().save(order);
		
		List<CartItem> cartItemList = getCartItemMapper().findByCartId(cart.getId());
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		
		for(CartItem cartItem : cartItemList) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotal(cartItem.getTotal());
			orderItemList.add(orderItem);
		}
		
		getOrderItemMapper().batchSave(orderItemList);
		
		getCartItemMapper().deleteByCartId(cart.getId());
		getCartMapper().updateTotal(cart.getId(), 0.0F);
		
		return order.getId();
	}

	@Override
	public List<Order> getByUserId(Integer userId) {
		// 数据库操作: SELECT * FROM order WHERE user_id = ?
		// 参数: userId - 用户ID
		// 返回: 该用户的所有订单列表
		return getOrderMapper().getByUserId(userId);
	}

	@Override
	public Order findById(Integer orderId) {
		// 数据库操作: SELECT * FROM order WHERE id = ?
		// 参数: orderId - 订单ID
		// 返回: 订单对象，找不到返回null
		return getOrderMapper().findById(orderId);
	}
}
