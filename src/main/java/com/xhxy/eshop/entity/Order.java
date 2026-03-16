package com.xhxy.eshop.entity;

import java.util.Date;
import java.util.List;

/**
 * 
 * 实体类：订单
 *
 */
public class Order {
	private Integer id;
	private Status status;		// 状态
	private Date createTime;	// 生成时间
	private float total;		// 订单总额
	
	private List<OrderItem> orderItemList;	// 订单项
	private User user;			// 所属的用户
	private Address address;	// 收货地址
	
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
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
