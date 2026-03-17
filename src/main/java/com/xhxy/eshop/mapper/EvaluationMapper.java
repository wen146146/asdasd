package com.xhxy.eshop.mapper;

import com.xhxy.eshop.entity.Evaluation;
import java.util.List;

public interface EvaluationMapper {
	// 数据库操作: SELECT * FROM evaluation WHERE product_id = ?
	// 参数: productId - 商品ID
	// 返回: 该商品的所有评价列表
	List<Evaluation> findByProductId(Integer productId);
}
