package com.xhxy.eshop.mapper;

import com.xhxy.eshop.entity.Picture;
import java.util.List;

public interface PictureMapper {
	// 数据库操作: SELECT * FROM picture WHERE product_id = ?
	// 参数: productId - 商品ID
	// 返回: 该商品的所有图片列表
	List<Picture> findByProductId(Integer productId);
}
