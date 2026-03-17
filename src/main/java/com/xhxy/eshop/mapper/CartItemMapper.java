package com.xhxy.eshop.mapper;

import com.xhxy.eshop.entity.CartItem;
import com.xhxy.eshop.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartItemMapper {
	// 数据库操作: SELECT * FROM cart_item WHERE id = ?
	// 参数: id - 购物车项ID
	// 返回: 购物车项对象，找不到返回null
	CartItem findById(Integer id);
	
	// 数据库操作: SELECT * FROM cart_item WHERE cart_id = ?
	// 参数: cartId - 购物车ID
	// 返回: 购物车项列表
	List<CartItem> findByCartId(Integer cartId);
	
	// 数据库操作: INSERT INTO cart_item (product_id, quantity, total, cart_id)
	// 参数: product - 商品对象, quantity - 数量, total - 总价, cartId - 购物车ID
	// 返回: 受影响的行数（成功返回1）
	Integer add(@Param("product") Product product, @Param("quantity") int quantity, @Param("total") Float total, @Param("cartId") int cartId);
	
	// 数据库操作: DELETE FROM cart_item WHERE cart_id = ?
	// 参数: cartId - 购物车ID
	// 返回: 受影响的行数
	Integer deleteByCartId(Integer cartId);
	
	// 数据库操作: SELECT id FROM cart_item WHERE cart_id = ? AND product_id = ?
	// 参数: cartId - 购物车ID, productId - 商品ID
	// 返回: 购物车项ID，找不到返回null
	Integer findByCartIdAndProductId(@Param("cartId") Integer cartId, @Param("productId") Integer productId);
	
	// 数据库操作: UPDATE cart_item SET quantity = ?, total = ? WHERE id = ?
	// 参数: cartItemId - 购物车项ID, quantity - 数量, total - 总价
	// 返回: 受影响的行数（成功返回1）
	Integer update(@Param("cartItemId") Integer cartItemId, @Param("quantity") Integer quantity, @Param("total") Float total);
}
