package com.xhxy.eshop.controller;

import java.io.IOException;
import java.lang.reflect.Method;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xhxy.eshop.service.CategoryService;
import com.xhxy.eshop.service.impl.mybatis.CategoryServiceImpl;

// BaseServlet: 所有Controller（Servlet）的父类
// 功能: 1.解析请求的method参数，根据参数值调用对应的Controller方法
//      2.获取顶层分类列表，放入request中供页面使用（搜索栏等）
//      3.处理返回结果，判断是转发(forward)还是重定向(redirect)
// 转发格式: "xxx.jsp" 或 "f:/xxx.jsp"
// 重定向格式: "r:/xxx?method=xxx"
public class BaseServlet extends HttpServlet {
	
	private CategoryService categoryService = new CategoryServiceImpl();
 
	// 核心服务方法：处理所有Controller请求的分发
	// 流程:
	//   1.获取method参数，确定要调用的方法名（默认为index）
	//   2.通过反射获取对应的Method对象
	//   3.调用该方法，获取返回值
	//   4.根据返回值格式，决定是转发还是重定向
	//   5.将顶层分类列表放入request，供所有页面使用
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1、获取请求的操作名称（method参数）
		// 例如: /account?method=orderlist 中的 method=orderlist
		String methodName = request.getParameter("method");
		if(methodName == null) {
			methodName = "index";  // 默认方法为index
		}
		Method method = null;
		
		// 2、通过反射机制获取Method对象
		// this.getClass()获取当前Controller类
		// methodName是方法名，HttpServletRequest.class和HttpServletResponse.class是参数类型
		try {
			method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("调用的方法：" + methodName + "不存在", e);
		}
 
		// 3、通过反射调用目标方法
		// method.invoke(this, request, response) 执行方法并获取返回值
		try {
			// 获取顶层分类列表，放入request供页面使用（如导航栏、搜索栏）
			request.setAttribute("topCategoryList", categoryService.findTopCategory());
			
			// 调用具体的Controller方法
			String result = (String) method.invoke(this, request, response);
			
			// 4、处理返回结果
			if (result != null && result.trim().length() > 0) {
				int index = result.indexOf(":");  // 查找冒号位置
				
				if (index == -1) {
					// 没有冒号：直接转发到jsp页面
					// 例如: "account-dashboard.jsp"
					request.getRequestDispatcher(result).forward(request, response);
				} else {
					// 有冒号：解析前缀和路径
					// 例如: "r:/account?method=orderlist"
					String start = result.substring(0, index);   // 前缀: "f" 或 "r"
					String path = result.substring(index + 1);   // 路径: "/account?method=orderlist"
					
					if (start.equalsIgnoreCase("f")) {
						// "f"开头：使用请求转发(forward)
						request.getRequestDispatcher(path).forward(request, response);
					} else if (start.equalsIgnoreCase("r")) {
						// "r"开头：使用重定向(redirect)
						response.sendRedirect(request.getContextPath() + path);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
