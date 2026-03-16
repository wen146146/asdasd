package com.xhxy.eshop.service.impl;

import java.util.List;

import com.xhxy.eshop.dao.CartDao;
import com.xhxy.eshop.dao.CartItemDao;
import com.xhxy.eshop.dao.impl.CartDaoImpl;
import com.xhxy.eshop.dao.impl.CartItemDaoImpl;
import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.CartItem;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.service.CartService;

public class CartServiceImpl implements CartService {

	private CartDao cartDao = new CartDaoImpl();
	private CartItemDao cartItemDao = new CartItemDaoImpl();
	
	@Override
	public Cart findByUserId(Integer userId) {
		return cartDao.findByUserId(userId);
	}

	// 将本Cart清空(包括其cartItem)
	@Override
	public boolean clear(Integer cartId) {
		// 1. 先删该cart中的所有cartItem项
		cartItemDao.deleteByCartId(cartId);	// 删掉该cart的所有cartItem
		// 2. 再将该cart的total项设为0
		cartDao.updateTotal(cartId, 0.0F);			// 将该cart的total值设为0
		return true;
	}

	@Override
	public boolean add(Product product, int quantity, int cartId) {
		// 1.检查该cart中是否已有该商品；
		Integer productId = product.getId();
		Integer cartItemId = cartItemDao.findByCartIdAndProductId(cartId,productId);
		// 1.1 若有，则将原商品数量 加1
		if(cartItemId != null && cartItemId > 0) {
			CartItem cartItem= cartItemDao.findById(cartItemId);		//取得该cartItem
			quantity = quantity + cartItem.getQuantity();				// 新购买数量 + 原购买数量 
			Float total = cartItem.getProduct().getPrice() * quantity; 	// 重新计算总价
			
			cartItemDao.update(cartItemId, quantity,total);			// 更新该cartItem的数量和总价
		}
		// 1.2 若无，则创建新的购物项cartItem
		else {
			Float total = product.getPrice() * quantity; 	// 计算总价
			cartItemDao.add(product, quantity, total, cartId);	// 插入新的cartItem行
		}
		// 2.重新计算该购物车的总额
		List<CartItem> cartItemList = cartItemDao.findByCartId(cartId);
		Float cartTotal = 0.0F;
		for(CartItem cartItem:cartItemList) {
			cartTotal = cartTotal + cartItem.getTotal();	// 将每项cartItem的总价加进购物车的总价
		}
		cartDao.updateTotal(cartId, cartTotal);
		return true;
	}


}
