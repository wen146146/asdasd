package com.xhxy.eshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Category;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.service.CategoryService;
import com.xhxy.eshop.service.ProductService;
import com.xhxy.eshop.service.impl.CategoryServiceImpl;
import com.xhxy.eshop.service.impl.ProductServiceImpl;

/**
 * 商品的分类 ：指定分类、全部商品、搜索商品
 */
@WebServlet("/category")
public class CategoryController extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoryService categoryService = new CategoryServiceImpl();
	private ProductService productService = new ProductServiceImpl();

	// 1.展示指定分类的商品
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求参数：分类Id号
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		// 获取全部的顶层分类：用于左侧的分类列表
		List<Category> topCategoryList = categoryService.findTopCategory();
		
		// 获取id对应的分类
		Category category = categoryService.findById(id);//当前的分类
		
		// 获取某个id号的Category里所有的product
		List<Product> productList = productService.findListByCategoryId(id);
		
		// 请求转发
		request.setAttribute("topCategoryList", topCategoryList);
		request.setAttribute("category", category);
		request.setAttribute("productList", productList);
			
		return "category.jsp";
	}
	
	// 2.默认为展示全部分类的商品(调用all()方法)
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return all(request,response);
	}
	
	// 展示全部分类的商品
	public String all(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取全部的顶层分类
		List<Category> topCategoryList = categoryService.findTopCategory();
				
		// 获取所有的product
		List<Product> productList = productService.findAll();
				
		// 请求转发
		request.setAttribute("topCategoryList", topCategoryList);
		request.setAttribute("productList", productList);
					
		return "category.jsp";
	}	
	
	
	
	// 3.展示搜索的商品列表
	public String search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1.获取请求参数：搜索词
		String name = request.getParameter("name"); 	// 商品名称 搜索词
		String brief = request.getParameter("brief"); 	// 商品简介 搜索词
		String detail = request.getParameter("detail"); // 商品详情 搜索词
		if(name.isBlank())
			name = null;
		if(brief.isBlank())
			brief = null;
		if(detail.isBlank())
			detail = null;
		
		// 3.按搜索词进行product搜索
		List<Product> productList = productService.findByKeywords(name,brief,detail);// 不分页
		
		// 4.获取全部的顶层分类：用于左侧的分类列表
		List<Category> topCategoryList = categoryService.findTopCategory();
		
		// 5.设置属性
		request.setAttribute("name",name);					// 请求参数name，再传回去给分页栏
		request.setAttribute("brief",brief);				// 请求参数brief，再传回去给分页栏
		request.setAttribute("detail",detail);				// 请求参数detail，再传回去给分页栏
		request.setAttribute("topCategoryList", topCategoryList);
		request.setAttribute("productList", productList);
		
		// 6.请求转发
		return "category.jsp";
	}
}
