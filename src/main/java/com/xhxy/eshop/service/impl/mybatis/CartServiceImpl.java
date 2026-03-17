package com.xhxy.eshop.service.impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.CartItem;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.mapper.CartItemMapper;
import com.xhxy.eshop.mapper.CartMapper;
import com.xhxy.eshop.service.CartService;
import com.xhxy.eshop.util.MybatisUtlils;

public class CartServiceImpl implements CartService {

	private CartMapper cartMapper = MybatisUtlils.getSqlSession().getMapper(CartMapper.class);
	private CartItemMapper cartItemMapper = MybatisUtlils.getSqlSession().getMapper(CartItemMapper.class);
	
	@Override
	public Cart findByUserId(Integer userId) {
		// 数据库操作: SELECT * FROM cart WHERE user_id = ?
		// 参数: userId - 用户ID
		// 返回: 购物车对象，找不到返回null
		return cartMapper.findByUserId(userId);
	}

	@Override
	public boolean clear(Integer cartId) {
		// 数据库操作: DELETE FROM cart_item WHERE cart_id = ? + UPDATE cart SET total = 0 WHERE id = ?
		// 参数: cartId - 购物车ID
		// 返回: 成功返回true
		cartItemMapper.deleteByCartId(cartId);
		cartMapper.updateTotal(cartId, 0.0F);
		MybatisUtlils.getSqlSession().commit();
		return true;
	}

	@Override
	public boolean add(Product product, int quantity, int cartId) {
		// 数据库操作: 
		//   1. SELECT * FROM cart_item WHERE cart_id = ? AND product_id = ?
		//   2. 如果存在: UPDATE cart_item SET quantity = ?, total = ? WHERE id = ?
		//   3. 如果不存在: INSERT INTO cart_item (...)
		//   4. 重新计算购物车总价: UPDATE cart SET total = ?
		// 参数: product - 商品对象, quantity - 数量, cartId - 购物车ID
		// 返回: 成功返回true
		Integer productId = product.getId();
		Integer cartItemId = cartItemMapper.findByCartIdAndProductId(cartId, productId);
		
		if(cartItemId != null && cartItemId > 0) {
			CartItem cartItem = cartItemMapper.findById(cartItemId);
			quantity = quantity + cartItem.getQuantity();
			Float total = cartItem.getProduct().getPrice() * quantity;
			cartItemMapper.update(cartItemId, quantity, total);
		}else {
			Float total = product.getPrice() * quantity;
			cartItemMapper.add(product, quantity, total, cartId);
		}
		
		List<CartItem> cartItemList = cartItemMapper.findByCartId(cartId);
		Float cartTotal = 0.0F;
		for(CartItem cartItem : cartItemList) {
			cartTotal = cartTotal + cartItem.getTotal();
		}
		cartMapper.updateTotal(cartId, cartTotal);
		MybatisUtlils.getSqlSession().commit();
		return true;
	}
}
