package com.xhxy.eshop.mapper;

import com.xhxy.eshop.entity.Faq;

import java.util.List;

public interface FaqMapper {
	// 数据库操作: SELECT * FROM faq
	// 参数: 无
	// 返回: 所有常见问题列表
	List<Faq> findAll();
}
