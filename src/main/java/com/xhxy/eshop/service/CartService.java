package com.xhxy.eshop.service;

import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.Product;

public interface CartService {
	// 数据库操作: SELECT * FROM cart WHERE user_id = ?
	// 参数: userId - 用户ID
	// 返回: 购物车对象，找不到返回null
	public Cart findByUserId(Integer userId);
	
	// 数据库操作: INSERT INTO cart (user_id) VALUES (?)
	// 参数: userId - 用户ID
	// 返回: 成功返回true
	public boolean create(Integer userId);
	
	// 数据库操作: INSERT INTO cart_item (...) 或 UPDATE cart_item SET ...
	// 参数: product - 商品对象, quantity - 数量, cartId - 购物车ID
	// 返回: 成功返回true
	public boolean add(Product product, int quantity, int cartId);
	
	// 数据库操作: DELETE FROM cart_item WHERE cart_id = ? + UPDATE cart SET total = 0 WHERE id = ?
	// 参数: cartId - 购物车ID
	// 返回: 成功返回true
	public boolean clear(Integer cartId);
}
