package com.xhxy.eshop.service.impl.jdbc;

import java.util.List;

import com.xhxy.eshop.dao.EvaluationDao;
import com.xhxy.eshop.dao.impl.EvaluationDaoImpl;
import com.xhxy.eshop.entity.Evaluation;
import com.xhxy.eshop.service.EvaluationService;

public class EvaluationServiceImpl implements EvaluationService {

	private EvaluationDao evaluationDao = new EvaluationDaoImpl();
	
	@Override
	public List<Evaluation> findByProductId(Integer productId) {
		return evaluationDao.findByProductId(productId);
	}

}
