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
import com.xhxy.eshop.service.impl.mybatis.EvaluationServiceImpl;
import com.xhxy.eshop.service.impl.mybatis.ProductServiceImpl;

// ProductController: 商品详情控制器
// 访问路径: /product
// 涉及页面: product.jsp(商品详情页)
// 功能: 获取商品详情和商品评价
@WebServlet("/product")
public class ProductController extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productService = new ProductServiceImpl();
	private EvaluationService evaluationService = new EvaluationServiceImpl();
	
	// 商品详情
	// 功能: 获取指定商品的详细信息及其评价列表
	// 参数: id（请求参数）- 商品ID
	// 返回页面: product.jsp（商品详情页）或 404.jsp（商品不存在）
	// 数据库操作: ProductService.findById() -> 根据ID查询商品
	//             EvaluationService.findByProductId() -> 查询商品的评价列表
	public String detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		Product product = productService.findById(id);
		List<Evaluation> evaluationList = evaluationService.findByProductId(id);
		
		if(product != null) {
			request.setAttribute("product", product);
			request.setAttribute("evaluationList", evaluationList);
			return "product.jsp";
		}else {
			return "404.jsp";
		}
		
	}

}
