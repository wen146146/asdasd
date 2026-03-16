package com.xhxy.eshop.entity;

import java.util.Date;

/**
 * 实体类：商品评价
 *
 */
public class Evaluation {
	private Integer id;
	private String content;		// 内容
	private Date createTime;	// 评价时间
	
	private User user;			// 评价人
	private Product product;			// 被评商品
	
	// getter和setter
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
