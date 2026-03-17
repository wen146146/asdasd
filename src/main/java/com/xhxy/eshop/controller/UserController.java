package com.xhxy.eshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.User;
import com.xhxy.eshop.service.CartService;
import com.xhxy.eshop.service.UserService;
import com.xhxy.eshop.service.impl.mybatis.CartServiceImpl;
import com.xhxy.eshop.service.impl.mybatis.UserServiceImpl;

// UserController: 用户控制器
// 访问路径: /user
// 涉及页面: login.jsp(登录页面), signup.jsp(注册页面)
// 功能: 1.用户登录 2.用户注册 3.用户退出
@WebServlet("/user")
public class UserController extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private CartService cartService = new CartServiceImpl();

	// 用户登录
	// 功能: 验证用户登录，支持自动登录和普通登录两种方式
	// 参数: username(用户名), password(密码), autologin(是否自动登录)
	// 返回: 登录成功重定向到首页（r:/index），登录失败返回login.jsp
	// 数据库操作: UserService.login() -> 验证用户名密码
	//             CartService.findByUserId() -> 获取用户购物车
	// 特殊逻辑: 
	//   1.优先检查Cookie中的自动登录信息
	//   2.登录成功后将用户ID和用户名存入session
	//   3.若选择自动登录，则保存Cookie（有效期7天）
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 首先检查是否能自动登录
		String usernameCookie="",passwordCookie="";
		Cookie[] cookies = request.getCookies();
		// if(cookies != null) {
		// 	for(Cookie cookie : cookies) {
		// 		if(cookie != null && cookie.getName().equals("username")) {
		// 			usernameCookie = cookie.getValue();
		// 		}
		// 		if(cookie != null && cookie.getName().equals("password")) {
		// 			passwordCookie = cookie.getValue();
		// 		}
		// 	}
		// 	Integer id = userService.login(usernameCookie, passwordCookie);
		// 	if ( id != null && id > 0) {
		// 		request.getSession().setAttribute("username", usernameCookie);
		// 		request.getSession().setAttribute("id", id);
				
		// 		Cart cart = cartService.findByUserId(id);
		// 		request.getSession().setAttribute("cart", cart);
		// 		return "r:/index";
		// 	}
		// }
		
		// 普通登录方式
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Integer id = userService.login(username, password);
		if ( id != null && id > 0) {

			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("id", id);
			

			Cart cart = cartService.findByUserId(id);
			
			// // 判断是否需要自动登录
			// if ( request.getParameter("autologin")!=null && request.getParameter("autologin").equals("checked")) {
			// 	Cookie nameCookie = new Cookie("username",username);
			// 	Cookie pswdCookie = new Cookie("password",password);
				
			// 	nameCookie.setMaxAge(60*60*24*7);
			// 	pswdCookie.setMaxAge(60*60*24*7);
				
			// 	response.addCookie(nameCookie);
			// 	response.addCookie(pswdCookie);
			// }
			request.getSession().setAttribute("cart", cart);
			return "r:/index";
		}else {
			request.setAttribute("message", "登录失败，请重新登录");
			return "login.jsp";
		}
		
	}
	
	// 用户注册
	// 功能: 创建新用户账号
	// 参数: username(用户名), password(密码), phone(手机号), email(邮箱)
	// 返回: 注册成功跳转到登录页面（login.jsp），注册失败返回signup.jsp
	// 数据库操作: UserService.addUser() -> 添加新用户
	public String signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
				
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
				
		if(userService.addUser(user) > 0) {
			return "login.jsp";
		}else {
			String message =  "注册失败，请重新输入";
			request.setAttribute("message", message);
					
			return "signup.jsp";
		}
	
	}
	
	// 用户退出
	// 功能: 清除session中的用户信息，实现退出登录
	// 参数: 无
	// 返回: 重定向到登录页面（r:/login.jsp）
	// 特殊逻辑: 调用session.invalidate()销毁整个会话
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		
		return "r:/login.jsp";
	}
}
