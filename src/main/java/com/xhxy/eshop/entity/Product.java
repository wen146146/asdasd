package com.xhxy.eshop.entity;

import java.util.List;

/**
 * 实体类：商品
 *
 */
public class Product {
	private Integer id;
	private String name;		// 商品名称
	private Float price;		// 商品价格
	private Float originalPrice;// 商品原价
	private String brief;		// 商品简介
	private String detail;		// 商品详情
	private Integer stock;		// 商品库存
	private String pic;			// 商品的主图片
	private String pic2;		// 商品的辅图片（鼠标移入商品时，会切换pic2）
	private Boolean hot;		// 是否热销商品（1：热销，0：不是热销）
	private Boolean latest;		// 是否新品（1：新品，0：不是新品）
	
	private Category category;	// 商品所属分类
	private List<Picture> pictureList;// 商品的所有图片
	private List<Evaluation> evaluationList;	// 对商品的所有评价

	// getter和setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public List<Picture> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<Picture> pictureList) {
		this.pictureList = pictureList;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getPic2() {
		return pic2;
	}
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	public Boolean getHot() {
		return hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}

	public Boolean getLatest() {
		return latest;
	}

	public void setLatest(Boolean latest) {
		this.latest = latest;
	}

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Evaluation> getEvaluationList() {
		return evaluationList;
	}

	public void setEvaluationList(List<Evaluation> evaluationList) {
		this.evaluationList = evaluationList;
	}
	
	
}
