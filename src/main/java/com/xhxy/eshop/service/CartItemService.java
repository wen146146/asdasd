package com.xhxy.eshop.service;

import java.util.List;

import com.xhxy.eshop.entity.CartItem;

public interface CartItemService {
	// 根据id获取
	public List<CartItem> findByCartId(Integer cartId);
}
