package com.xhxy.eshop.service.impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.OrderItem;
import com.xhxy.eshop.mapper.OrderItemMapper;
import com.xhxy.eshop.service.OrderItemService;
import com.xhxy.eshop.util.MybatisUtlils;

public class OrderItemServiceImpl implements OrderItemService {

	private OrderItemMapper getOrderItemMapper() {
		return MybatisUtlils.getSqlSession().getMapper(OrderItemMapper.class);
	}
	
	@Override
	public List<OrderItem> findByOrderId(Integer orderId) {
		// 数据库操作: SELECT * FROM order_item WHERE order_id = ?
		// 参数: orderId - 订单ID
		// 返回: 订单项列表
		return getOrderItemMapper().findByOrderId(orderId);
	}

	@Override
	public Integer save(OrderItem orderItem) {
		// 数据库操作: INSERT INTO order_item (...)
		// 参数: orderItem - 订单项对象（需包含订单ID、商品、数量、总价等）
		// 返回: 受影响的行数（成功返回1）
		int result = getOrderItemMapper().save(orderItem);
		return result;
	}
}
