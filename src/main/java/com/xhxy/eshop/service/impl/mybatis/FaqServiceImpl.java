package com.xhxy.eshop.service.impl.mybatis;

import com.xhxy.eshop.entity.Faq;
import com.xhxy.eshop.mapper.FaqMapper;
import com.xhxy.eshop.service.FaqService;
import com.xhxy.eshop.util.MybatisUtlils;

import java.util.List;

public class FaqServiceImpl implements FaqService {

	private FaqMapper getFaqMapper() {
		return MybatisUtlils.getSqlSession().getMapper(FaqMapper.class);
	}
	
	@Override
	public List<Faq> findAll() {
		// 数据库操作: SELECT * FROM faq
		// 参数: 无
		// 返回: 所有常见问题列表
		return getFaqMapper().findAll();
	}

}
