package com.xhxy.eshop.entity;
/**
 * 
 * 实体类：订单项
 *
 */
public class OrderItem {
	private Integer id;
	private Product product;	// 所定商品
	private Integer quantity;	// 商品数量
	private Float total;		// 本项总价
	private Order order;		// 所属订单
	
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
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
