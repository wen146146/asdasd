package com.xhxy.eshop.service.impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Evaluation;
import com.xhxy.eshop.mapper.EvaluationMapper;
import com.xhxy.eshop.service.EvaluationService;
import com.xhxy.eshop.util.MybatisUtlils;

public class EvaluationServiceImpl implements EvaluationService {

	private EvaluationMapper getEvaluationMapper() {
		return MybatisUtlils.getSqlSession().getMapper(EvaluationMapper.class);
	}
	
	@Override
	public List<Evaluation> findByProductId(Integer productId) {
		// 数据库操作: SELECT * FROM evaluation WHERE product_id = ?
		// 参数: productId - 商品ID
		// 返回: 该商品的所有评价列表
		return getEvaluationMapper().findByProductId(productId);
	}
}
