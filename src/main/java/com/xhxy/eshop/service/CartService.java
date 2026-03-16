package com.xhxy.eshop.service;

import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.Product;

public interface CartService {
	// 根据用户Id，返回Cart
	public Cart findByUserId(Integer userId);
	
	// 把某个Product，放入购物车
	public boolean add(Product product, int quantity, int cartId);
	
	// 将某个Cart清空（在订单中使用）
	public boolean clear(Integer cartId);
}
