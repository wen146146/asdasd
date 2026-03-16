package com.xhxy.eshop.entity;

import java.util.List;

/**
 * 
 * 实体类：购物车
 *
 */
public class Cart {
	private Integer id;
	private float total;				// 总额
	private List<CartItem> cartItemList;	// 购物车中的购物项
	private User user;					// 购物车对应的用户对象
	
	// ----- getter/setter ------
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public List<CartItem> getCartItemList() {
		return cartItemList;
	}
	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
