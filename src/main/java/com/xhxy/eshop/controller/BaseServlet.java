package com.xhxy.eshop.controller;

import java.io.IOException;
import java.lang.reflect.Method;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xhxy.eshop.service.CategoryService;
import com.xhxy.eshop.service.impl.CategoryServiceImpl;

// 所有Controller（Servlet）的父类：解析请求的method参数，转到对应的Controller的同名方法中
public class BaseServlet extends HttpServlet {
	
	private CategoryService categoryService = new CategoryServiceImpl();
 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
		// 1、获得请求的操作名称
		String methodName = request.getParameter("method");
		if(methodName == null) {	// 若没有method参数，则默认为index方法
			methodName = "index";
		}
		Method method = null;
		
		
		// 2、通过方法名和方法所需要的参数获得Method对象
		try {
			method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("调用的方法：" + methodName + "不存在", e);
		}
 
		// 3、通过Method对象调用方法
		try {
			request.setAttribute("topCategoryList", categoryService.findTopCategory());//将全部顶层分类放入request中，用于搜索栏
			String result = (String) method.invoke(this, request, response);
			if (result != null && result.trim().length() > 0) {// 如果返回的result不为空
				int index = result.indexOf(":");// 获得第一个冒号的位置
				if (index == -1) {// 如果没有冒号，就使用转发
					request.getRequestDispatcher(result).forward(request, response);
				} else {// 如果有冒号
					String start = result.substring(0, index);// 截取前缀
					String path = result.substring(index + 1);// 截取路径
					if (start.equalsIgnoreCase("f")) {// 前缀为f表示使用转发
						request.getRequestDispatcher(path).forward(request, response);
					} else if (start.equalsIgnoreCase("r")) {// 前缀为r表示使用重定向
						response.sendRedirect(request.getContextPath() + path);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}