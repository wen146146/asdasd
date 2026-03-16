package com.xhxy.eshop.service;

import java.util.List;

import com.xhxy.eshop.entity.OrderItem;

public interface OrderItemService {
	// 根据id获取
	public List<OrderItem> findByOrderId(Integer orderId);
		
	// 保存（插入）一个OrderItem
	public Integer save(OrderItem orderItem);
}
