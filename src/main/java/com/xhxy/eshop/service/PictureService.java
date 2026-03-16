package com.xhxy.eshop.service;

import java.util.List;

import com.xhxy.eshop.entity.Picture;

public interface PictureService {
	public List<Picture> findByProductId(Integer productId);
}
