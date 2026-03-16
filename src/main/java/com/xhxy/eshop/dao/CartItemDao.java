package com.xhxy.eshop.dao;

import java.util.List;

import com.xhxy.eshop.entity.CartItem;
import com.xhxy.eshop.entity.Product;

public interface CartItemDao{
	
	// 根据id获取cartItem
	public CartItem findById(Integer id);
	
	// 根据购物车的id获取其全部cartItem
	public List<CartItem> findByCartId(Integer cartId);
	
	// 由product创建购物项
	public Integer add(Product product,int quantity, Float total, int cartId);
	
	// 删掉某个cart的所有cartItem项
	public Integer deleteByCartId(Integer cartId);
	
	// 查找某个cart中是否已有某个Product
	public Integer findByCartIdAndProductId(Integer cartId, Integer productId);
	
	// 更改某个cartItem的数量和总额
	public Integer update(Integer cartItemId, Integer quantity, Float total);
}