package com.xhxy.eshop.dao;

import java.util.List;

import com.xhxy.eshop.entity.Comment;
import com.xhxy.eshop.entity.Evaluation;

public interface EvaluationDao {
	// 查询某商品的全部评价evaluation
	public List<Evaluation> findByProductId(Integer productId);
}
