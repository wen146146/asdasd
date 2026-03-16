package com.xhxy.eshop.entity;

import java.util.Date;
import java.util.List;

/**
 * 实体类：推荐文章
 *
 */
public class Blog {
	private Integer id;
	private String title;		// 标题
	private String pic;			// 标题图片
	private String content;		// 正文
	private Date createTime;	// 创建时间
	
	private List<Comment> commentList;	// 评论集

	// getter和setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
}
