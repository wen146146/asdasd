package com.xhxy.eshop.dao;

import java.util.List;

import com.xhxy.eshop.entity.Picture;

public interface PictureDao{

	public List<Picture> findByProductId(Integer productId);
}
