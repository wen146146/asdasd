package com.xhxy.eshop.mapper;

import com.xhxy.eshop.entity.OrderItem;
import java.util.List;

public interface OrderItemMapper {
	// 数据库操作: SELECT * FROM order_item WHERE order_id = ?
	// 参数: orderId - 订单ID
	// 返回: 订单项列表
	List<OrderItem> findByOrderId(Integer orderId);
	
	// 数据库操作: INSERT INTO order_item (...)
	// 参数: orderItem - 订单项对象
	// 返回: 受影响的行数（成功返回1）
	Integer save(OrderItem orderItem);
	
	// 数据库操作: INSERT INTO order_item (...) 批量插入
	// 参数: orderItemList - 订单项列表
	// 返回: 受影响的行数
	Integer batchSave(List<OrderItem> orderItemList);
}
