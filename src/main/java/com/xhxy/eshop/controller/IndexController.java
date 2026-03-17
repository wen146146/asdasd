package com.xhxy.eshop.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Blog;
import com.xhxy.eshop.entity.Category;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.service.BlogService;
import com.xhxy.eshop.service.CategoryService;
import com.xhxy.eshop.service.ProductService;
import com.xhxy.eshop.service.impl.mybatis.BlogServiceImpl;
import com.xhxy.eshop.service.impl.mybatis.CategoryServiceImpl;
import com.xhxy.eshop.service.impl.mybatis.ProductServiceImpl;

// IndexController: 首页控制器
// 访问路径: /index
// 涉及页面: index.jsp(首页)
// 功能: 展示首页数据，包括分类导航、热销商品、新品推荐、推荐文章、站点统计信息
@WebServlet("/index")
public class IndexController  extends BaseServlet {

	private CategoryService categoryService = new CategoryServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	private BlogService blogService = new BlogServiceImpl();
	
	// 首页展示
	// 功能: 获取首页所需的全部数据，包括分类、热销商品、新品、文章推荐、统计信息
	// 参数: 无
	// 返回页面: index.jsp
	// 数据库操作: 
	//   - CategoryService.findTopCategory() -> 查询顶层分类列表（左侧导航）
	//   - ProductService.findHot(10) -> 查询10条热销商品
	//   - ProductService.findLatest(10) -> 查询10条最新商品
	//   - BlogService.findLatestBlog(3) -> 查询3篇推荐文章
	public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 1.从Service层，获取首页所需的各个数据对象
		// 获取顶层分类：用于左侧菜单导航
		List<Category> topCategoryList = categoryService.findTopCategory();
		
		// 获取10条热销商品
		List<Product> hotProductList = productService.findHot(10);
		
		// 获取10条最新商品
		List<Product> latestProductList = productService.findLatest(10);
		
		// 获取3篇推荐文章
		List<Blog> blogList = blogService.findLatestBlog(3);
		
		// 从properties属性文件中读取站点统计信息
		Properties props = new Properties();
		InputStream in = new BufferedInputStream(getClass().getResourceAsStream("/com/xhxy/eshop/eshop.properties"));
		props.load(in);
		
		// 2.将数据对象设置到request域中，供页面使用
		request.setAttribute("topCategoryList", topCategoryList);
		request.setAttribute("hotProductList", hotProductList);
		request.setAttribute("latestProductList", latestProductList);
		request.setAttribute("blogList", blogList);
		
		// 站点统计信息
		request.setAttribute("allRequest", props.getProperty("allRequest"));     // 总请求数
		request.setAttribute("indexRequest", props.getProperty("indexRequest")); // 首页访问数
		request.setAttribute("onlineUser", props.getProperty("onlineUser"));   // 在线用户数
		request.setAttribute("allUser", props.getProperty("allUser"));           // 总用户数
		
		// 3.返回首页视图
		return "index.jsp";
	}
}
