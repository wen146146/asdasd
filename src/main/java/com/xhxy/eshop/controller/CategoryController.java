package com.xhxy.eshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.xhxy.eshop.entity.Category;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.service.CategoryService;
import com.xhxy.eshop.service.ProductService;
import com.xhxy.eshop.service.impl.mybatis.CategoryServiceImpl;
import com.xhxy.eshop.service.impl.mybatis.ProductServiceImpl;

// CategoryController: 商品分类控制器
// 访问路径: /category
// 涉及页面: category.jsp(商品分类页面)
// 功能: 1.展示指定分类的商品 2.展示全部分类的商品 3.搜索商品
@WebServlet("/category")
public class CategoryController extends BaseServlet {
	private static final Logger logger = LogManager.getLogger(CategoryController.class);
	private static final long serialVersionUID = 1L;
	
	private CategoryService categoryService = new CategoryServiceImpl();
	private ProductService productService = new ProductServiceImpl();

	// 展示指定分类的商品
	// 功能: 根据分类ID获取该分类下的所有商品
	// 参数: id（请求参数）- 分类ID
	//       page（请求参数）- 页码，默认1
	//       size（请求参数）- 每页数量，默认6
	// 返回页面: category.jsp
	// 数据库操作: CategoryService.findTopCategory() -> 查询顶层分类列表
	//             CategoryService.findById() -> 根据ID查询分类
	//             ProductService.findBypage() -> 根据分类ID分页查询商品列表
	//             ProductService.countByCategoryId() -> 查询该分类的商品总数
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		// 获取分页参数
		String pageStr = request.getParameter("page");
		String sizeStr = request.getParameter("size");
		
		Integer page = (pageStr != null && !pageStr.isEmpty()) ? Integer.parseInt(pageStr) : 1;
		Integer size = (sizeStr != null && !sizeStr.isEmpty()) ? Integer.parseInt(sizeStr) : 6;
		
		// 查询顶层分类列表
		List<Category> topCategoryList = categoryService.findTopCategory();
		for (Category topCategory : topCategoryList){
			topCategory.setChildren(categoryService.findChildCategory(topCategory.getId()));
		}
		// 查询指定分类
		Category category = categoryService.findById(id);
		
		// 分页查询指定分类下的商品列表
		List<Product> productList = productService.findBypage(id, page, size);
		
		// 查询该分类的商品总数
		Integer totalCount = productService.countByCategoryId(id);
		
		// 计算总页数
		Integer totalPages = (totalCount + size - 1) / size;
		
		request.setAttribute("topCategoryList", topCategoryList);
		request.setAttribute("category", category);
		request.setAttribute("productList", productList);
		request.setAttribute("currentPage", page);
		request.setAttribute("pageSize", size);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("totalCount", totalCount);
			
		return "category.jsp";
	}
	
	// 默认入口：展示全部分类的商品
	// 功能: 调用all()方法显示所有商品
	// 参数: 无
	// 返回页面: category.jsp
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return all(request,response);
	}
	
	// 展示全部分类的商品
	// 功能: 获取所有商品列表
	// 参数: page（请求参数）- 页码，默认1
	//       size（请求参数）- 每页数量，默认6
	// 返回页面: category.jsp
	// 数据库操作: CategoryService.findTopCategory() -> 查询顶层分类列表
	//             ProductService.findBypage() -> 分页查询所有商品
	//             ProductService.countAll() -> 查询所有商品总数
	public String all(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取分页参数
		String pageStr = request.getParameter("page");
		String sizeStr = request.getParameter("size");
		
		Integer page = (pageStr != null && !pageStr.isEmpty()) ? Integer.parseInt(pageStr) : 1;
		Integer size = (sizeStr != null && !sizeStr.isEmpty()) ? Integer.parseInt(sizeStr) : 6;
		
		List<Category> topCategoryList = categoryService.findTopCategory();
		for (Category category : topCategoryList){
			category.setChildren(categoryService.findChildCategory(category.getId()));
		}
		
		// 分页查询所有商品（categoryId 为 null 表示查询所有）
		List<Product> productList = productService.findBypage(null, page, size);
		
		// 查询所有商品总数
		Integer totalCount = productService.countAll();
		
		// 计算总页数
		Integer totalPages = (totalCount + size - 1) / size;
		
		request.setAttribute("topCategoryList", topCategoryList);
		request.setAttribute("productList", productList);
		request.setAttribute("currentPage", page);
		request.setAttribute("pageSize", size);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("totalCount", totalCount);

		logger.info("全部分类页面 - 顶层分类数量: {}, 商品数量: {}, 总页数: {}",
				topCategoryList != null ? topCategoryList.size() : 0,
				totalCount,
				totalPages);
		return "category.jsp";
	}	
	
	// 搜索商品
	// 功能: 根据关键词搜索商品（支持按名称、简介、详情搜索）
	// 参数: name(商品名称), brief(商品简介), detail(商品详情)
	// 返回页面: category.jsp
	// 数据库操作: ProductService.findByKeywords() -> 根据关键词搜索商品
	//             CategoryService.findTopCategory() -> 查询顶层分类列表
	public String search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String brief = request.getParameter("brief");
		String detail = request.getParameter("detail");
		if(name.isBlank())
			name = null;
		if(brief.isBlank())
			brief = null;
		if(detail.isBlank())
			detail = null;
		
		List<Product> productList = productService.findByKeywords(name,brief,detail);
		
		List<Category> topCategoryList = categoryService.findTopCategory();
		
		request.setAttribute("name",name);
		request.setAttribute("brief",brief);
		request.setAttribute("detail",detail);
		request.setAttribute("topCategoryList", topCategoryList);
		request.setAttribute("productList", productList);
		
		return "category.jsp";
	}
}
