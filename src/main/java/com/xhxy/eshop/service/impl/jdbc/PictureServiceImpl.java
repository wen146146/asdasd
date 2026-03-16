package com.xhxy.eshop.service.impl.jdbc;

import java.util.List;

import com.xhxy.eshop.dao.PictureDao;
import com.xhxy.eshop.dao.impl.PictureDaoImpl;
import com.xhxy.eshop.entity.Picture;
import com.xhxy.eshop.service.PictureService;

public class PictureServiceImpl implements PictureService {

	private PictureDao pictureDao = new PictureDaoImpl();
	
	@Override
	public List<Picture> findByProductId(Integer productId) {
		return pictureDao.findByProductId(productId);
	}

}
