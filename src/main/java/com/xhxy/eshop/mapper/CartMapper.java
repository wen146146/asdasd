package com.xhxy.eshop.mapper;

import org.apache.ibatis.annotations.Param;

import com.xhxy.eshop.entity.Cart;

public interface CartMapper {
	// 数据库操作: INSERT INTO cart (user_id)
	// 参数: userId - 用户ID
	// 返回: 受影响的行数（成功返回1）
	Integer add(Integer userId);
	
	// 数据库操作: SELECT * FROM cart WHERE id = ?
	// 参数: id - 购物车ID
	// 返回: 购物车对象，找不到返回null
	Cart findById(Integer id);
	
	// 数据库操作: SELECT * FROM cart WHERE user_id = ?
	// 参数: userId - 用户ID
	// 返回: 购物车对象，找不到返回null
	Cart findByUserId(Integer userId);
	
	// 数据库操作: UPDATE cart SET total = ? WHERE id = ?
	// 参数: cartId - 购物车ID, cartTotal - 购物车总价
	// 返回: 受影响的行数（成功返回1）
	Integer updateTotal(@Param("cartId") Integer cartId, @Param("cartTotal") Float cartTotal);
}
