package com.xhxy.eshop.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Faq;
import com.xhxy.eshop.service.FaqService;
import com.xhxy.eshop.service.impl.mybatis.FaqServiceImpl;

// FaqController: 常见问题（FAQ）控制器
// 访问路径: /faq
// 涉及页面: faq.jsp(常见问题页面)
// 功能: 获取常见问题列表
@WebServlet("/faq")
public class FaqController  extends BaseServlet {

	private FaqService faqService = new FaqServiceImpl();
	
	// 常见问题列表
	// 功能: 获取所有常见问题
	// 参数: 无
	// 返回页面: faq.jsp
	// 数据库操作: FaqService.findAll() -> 查询所有常见问题
	public String index(HttpServletRequest request, HttpServletResponse response) {
		List<Faq> faqs = faqService.findAll();
		
		request.setAttribute("faqs", faqs);
		return "faq.jsp";
	}
}
