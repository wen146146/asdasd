package com.xhxy.eshop.service;

import java.util.List;

import com.xhxy.eshop.entity.Evaluation;

public interface EvaluationService {
	List<Evaluation> findByProductId(Integer productId);
}
