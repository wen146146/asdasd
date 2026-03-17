package com.xhxy.eshop.service.impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.CartItem;
import com.xhxy.eshop.mapper.CartItemMapper;
import com.xhxy.eshop.service.CartItemService;
import com.xhxy.eshop.util.MybatisUtlils;

public class CartItemServiceImpl implements CartItemService {

	private CartItemMapper cartItemMapper = MybatisUtlils.getSqlSession().getMapper(CartItemMapper.class);
	
	@Override
	public List<CartItem> findByCartId(Integer cartId) {
		// 数据库操作: SELECT * FROM cart_item WHERE cart_id = ?
		// 参数: cartId - 购物车ID
		// 返回: 购物车项列表
		return cartItemMapper.findByCartId(cartId);
	}
}
