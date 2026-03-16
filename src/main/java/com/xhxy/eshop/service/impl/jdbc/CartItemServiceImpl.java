package com.xhxy.eshop.service.impl.jdbc;

import java.util.List;

import com.xhxy.eshop.dao.CartItemDao;
import com.xhxy.eshop.dao.impl.CartItemDaoImpl;
import com.xhxy.eshop.entity.CartItem;
import com.xhxy.eshop.service.CartItemService;

public class CartItemServiceImpl implements CartItemService {

	private CartItemDao cartItemDao = new CartItemDaoImpl();
	
	@Override
	public List<CartItem> findByCartId(Integer cartId) {
		return cartItemDao.findByCartId(cartId);
	}

}
