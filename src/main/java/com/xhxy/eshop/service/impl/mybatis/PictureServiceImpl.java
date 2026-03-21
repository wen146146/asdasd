package com.xhxy.eshop.service.impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Picture;
import com.xhxy.eshop.mapper.PictureMapper;
import com.xhxy.eshop.service.PictureService;
import com.xhxy.eshop.util.MybatisUtlils;

public class PictureServiceImpl implements PictureService {

	private PictureMapper getPictureMapper() {
		return MybatisUtlils.getSqlSession().getMapper(PictureMapper.class);
	}
	
	@Override
	public List<Picture> findByProductId(Integer productId) {
		// 数据库操作: SELECT * FROM picture WHERE product_id = ?
		// 参数: productId - 商品ID
		// 返回: 该商品的所有图片列表
		return getPictureMapper().findByProductId(productId);
	}
}
