package com.xhxy.eshop.service.impl.mybatis;

import com.xhxy.eshop.dao.FaqDao;
import com.xhxy.eshop.dao.impl.FaqDaoImpl;
import com.xhxy.eshop.entity.Faq;
import com.xhxy.eshop.mapper.FaqMapper;
import com.xhxy.eshop.service.FaqService;
import com.xhxy.eshop.util.MybatisUtlils;

import java.util.List;

public class FaqServiceImpl implements FaqService {

	private FaqMapper faqMapper = MybatisUtlils.getSqlSession().getMapper(FaqMapper.class);
	
	@Override
	public List<Faq> findAll() {
		return faqMapper.findAll();
	}

}
