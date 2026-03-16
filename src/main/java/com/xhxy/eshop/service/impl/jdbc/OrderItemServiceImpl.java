package com.xhxy.eshop.service.impl.jdbc;

import java.util.List;

import com.xhxy.eshop.dao.OrderItemDao;
import com.xhxy.eshop.dao.impl.OrderItemDaoImpl;
import com.xhxy.eshop.entity.OrderItem;
import com.xhxy.eshop.service.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {

	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	
	@Override
	public List<OrderItem> findByOrderId(Integer orderId) {
		return orderItemDao.findByOrderId(orderId);
	}

	@Override
	public Integer save(OrderItem orderItem) {
		return orderItemDao.save(orderItem);
	}

}
