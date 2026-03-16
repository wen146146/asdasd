package com.xhxy.eshop.entity;

import java.util.Date;

/**
 * 实体类：文章评论
 *
 */
public class Comment {
	private Integer id;
	private String content;		// 内容
	private Date createTime;	// 创建时间
	
	private User user;			// 评论人
	private Blog blog;			// 被评文章
	
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
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
}
