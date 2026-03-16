package com.xhxy.eshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Evaluation;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.service.EvaluationService;
import com.xhxy.eshop.service.ProductService;
import com.xhxy.eshop.service.impl.EvaluationServiceImpl;
import com.xhxy.eshop.service.impl.ProductServiceImpl;

/**
 * 商品：详情页
 */
@WebServlet("/product")
public class ProductController extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productService = new ProductServiceImpl();
	private EvaluationService evaluationService = new EvaluationServiceImpl();
	
	// 商品详情
	public String detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.获取请求参数
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		// 2.从Service层获取product对象和对其的评价
		Product product = productService.findById(id);
		List<Evaluation> evaluationList = evaluationService.findByProductId(id);
		
		// 3.将数据对象设为属性，返回页面
		if(product != null) {
			request.setAttribute("product", product);
			request.setAttribute("evaluationList", evaluationList);
			return "product.jsp";
		}else {
			return "404.jsp";
		}
		
	}

}
