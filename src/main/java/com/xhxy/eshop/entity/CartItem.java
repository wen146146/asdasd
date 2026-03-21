package com.xhxy.eshop.entity;

import java.io.Serializable;

/**
 * 
 * 实体类：购物项
 *
 */
public class CartItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Product product;	// 所购商品
	private Integer quantity;	// 购物数量
	private Float total;		// 本项总价
	
	private Cart cart;			// 所属购物车
	// ----- getter/setter ------
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
}
