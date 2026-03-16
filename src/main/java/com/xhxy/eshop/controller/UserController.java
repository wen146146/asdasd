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
import com.xhxy.eshop.service.impl.CartServiceImpl;
import com.xhxy.eshop.service.impl.UserServiceImpl;

/**
 * 用户Servlet：处理登录、注册、退出
 */
@WebServlet("/user")
public class UserController extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	private CartService cartService = new CartServiceImpl();
	
	// "登录"操作
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 首先检查 是否能自动登录
		// 是否自动登录
		String usernameCookie="",passwordCookie="";
		Cookie[] cookies = request.getCookies();	// 读取客户端发来的全部Cookie
		if(cookies != null) {
			for(Cookie cookie : cookies) {	// 遍历cookie数组
				if(cookie != null && cookie.getName().equals("username")) {	// 若是username，则取其值
					usernameCookie = cookie.getValue();
				}
				if(cookie != null && cookie.getName().equals("password")) { // 若是password，则取其值
					passwordCookie = cookie.getValue();
				}
			}
			Integer id = userService.login(usernameCookie, passwordCookie);	// 用cookie取到的username和password尝试登录
			if ( id != null && id > -1) { // 登录成功
				request.getSession().setAttribute("username", usernameCookie);	// 设置会话里的username属性
				request.getSession().setAttribute("id", id);					// 设置会话里的id属性
				
				// 获取该用户的购物车
				Cart cart = cartService.findByUserId(id);
				request.getSession().setAttribute("cart", cart);	// 购物车cart放入session中
				// 重定向到首页
				return "r:/index";	//此处需要：重定向redirect
			}
		}
		
		// 若不能自动登录，则继续获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 验证是否登录成功
		Integer id = userService.login(username, password);
		if ( id != null && id > -1) { // 登录成功
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("id", id);
			
			// 获取该用户的购物车
			Cart cart = cartService.findByUserId(id);
			// 判断是否需要自动登录
			if ( request.getParameter("autologin")!=null && request.getParameter("autologin").equals("checked")) {
				Cookie nameCookie = new Cookie("username",username);
				Cookie pswdCookie = new Cookie("password",password);
				
				nameCookie.setMaxAge(60*60*24*7);	// 自动登录 默认 7天
				pswdCookie.setMaxAge(60*60*24*7);
				
				response.addCookie(nameCookie);		// 加入两个Cookie
				response.addCookie(pswdCookie);
			}
			request.getSession().setAttribute("cart", cart);//放入session
			// 登录成功，重定向到首页
			return "r:/index";	//此处需要：重定向redirect
		}else { // 登录失败
			request.setAttribute("message", "登录失败，请重新登录");
			return "login.jsp";
		}
		
	}
	
	// "注册"操作
	public String signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
				
		// 创建User对象
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
				
		// 调用UserDao插入新用户
		if(userService.addUser(user) > 0) {
			return "login.jsp";
		}else {
			String message =  "注册失败，请重新输入";
			request.setAttribute("message", message);
					
			return "signup.jsp";
		}
	
	}
	
	// "退出" 操作
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		
		return "r:/login.jsp"; //此处需要：重定向redirect
	}
}
