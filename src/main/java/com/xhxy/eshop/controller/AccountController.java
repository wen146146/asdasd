package com.xhxy.eshop.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.xhxy.eshop.entity.Address;
import com.xhxy.eshop.entity.Order;
import com.xhxy.eshop.entity.User;
import com.xhxy.eshop.service.AddressService;
import com.xhxy.eshop.service.OrderService;
import com.xhxy.eshop.service.UserService;
import com.xhxy.eshop.service.impl.mybatis.AddressServiceImpl;
import com.xhxy.eshop.service.impl.mybatis.OrderServiceImpl;
import com.xhxy.eshop.service.impl.mybatis.UserServiceImpl;

// 账号中心：处理 订单列表、详情、收货地址的增删改查、个人信息修改
// 访问路径: /account
// 涉及页面: account-dashboard.jsp(首页), account-order-list.jsp(订单列表), account-order-view.jsp(订单详情),
//           account-address-list.jsp(地址列表), account-address-edit.jsp(地址编辑), 
//           account-user-view.jsp(用户信息查看), account-user-edit.jsp(用户信息编辑)
@WebServlet("/account")
@MultipartConfig(location="D:\\",fileSizeThreshold=1024)	// 上传文件的注解
public class AccountController   extends BaseServlet{
	
	private UserService userService = new UserServiceImpl();
	private OrderService orderService = new OrderServiceImpl();
	private AddressService addressService = new AddressServiceImpl();
	
	// 账号中心-->“首页”
	// 功能: 显示当前登录用户的基本信息
	// 参数: 从session获取用户ID
	// 返回页面: account-dashboard.jsp
	public String index(HttpServletRequest request, HttpServletResponse response) {
		Integer id = (Integer)request.getSession().getAttribute("id");
		System.out.println("当前用户ID：" + id);
		User user = userService.findById(id);
		request.getSession().setAttribute("user", user);
		
		return "account-dashboard.jsp";
	}
	
	// 账号中心-->“首页”，功能同index
	// 功能: 显示当前登录用户的基本信息
	// 参数: 从session获取用户ID
	// 返回页面: account-dashboard.jsp
	public String dashboard(HttpServletRequest request, HttpServletResponse response) {
		Integer id = (Integer)request.getSession().getAttribute("id");
		User user = userService.findById(id);
		request.getSession().setAttribute("user", user);
		
		return "account-dashboard.jsp";
	}
	
	// ------ 订单 -> 列表 -------
	// 功能: 获取当前用户的所有订单列表
	// 参数: 从session获取用户ID
	// 返回页面: account-order-list.jsp
	public String orderlist(HttpServletRequest request, HttpServletResponse response) {
		Integer id = (Integer)request.getSession().getAttribute("id");
		
		List<Order> orders = orderService.getByUserId(id);
		request.setAttribute("orders", orders);
		
		User user = userService.findById(id);
		request.setAttribute("user", user);
		
		return "account-order-list.jsp";
	}
	// ------ 订单 -> 查看单个 -------
	// 功能: 查看单个订单的详细信息
	// 参数: orderId（请求参数）
	// 返回页面: account-order-view.jsp
	public String orderView(HttpServletRequest request, HttpServletResponse response) {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		
		Order order = orderService.findById(orderId);
		request.setAttribute("order", order);
		
		return "account-order-view.jsp";
	}
	
	// ------ 收货地址 -> 列表 -------
	// 功能: 获取当前用户的所有收货地址
	// 参数: 从session获取用户ID
	// 返回页面: account-address-list.jsp
	public String addresslist(HttpServletRequest request, HttpServletResponse response) {
		Integer id = (Integer)request.getSession().getAttribute("id");
		
		List<Address> addressList = addressService.findByUserId(id);
		request.getSession().setAttribute("addressList", addressList);
		
		return "account-address-list.jsp";
	}
	// ------ 收货地址 -> 编辑 -------
	// 功能: 打开收货地址编辑页面（修改地址时使用）
	// 参数: id（请求参数）- 收货地址ID
	// 返回页面: account-address-edit.jsp
	public String editAddress(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		Address address = addressService.findById(id);
		request.getSession().setAttribute("address", address);
		
		return "account-address-edit.jsp";
	}
	// ------ 收货地址 -> 新增 -------
	// 功能: 新增收货地址
	// 参数: consigneeName(收货人), consigneeAddress(地址), consigneePhone(手机), postcode(邮编), userId(用户ID)
	// 返回: 重定向到地址列表（r:表示重定向）
	public String addAddress(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		
		String consigneeName = request.getParameter("consigneeName");
		String consigneeAddress = request.getParameter("consigneeAddress");
		String consigneePhone = request.getParameter("consigneePhone");
		String postcode = request.getParameter("postcode");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		
		User user = userService.findById(userId);
		
		Address address = new Address();
		address.setConsigneeAddress(consigneeAddress);
		address.setConsigneeName(consigneeName);
		address.setConsigneePhone(consigneePhone);
		address.setPostcode(postcode);
		address.setUser(user);
		
		addressService.add(address);
		
		return "r:/account?method=addresslist";
	}
	// ------ 收货地址 -> 更新 -------
	// 功能: 更新收货地址
	// 参数: id(地址ID), consigneeName, consigneeAddress, consigneePhone, postcode
	// 返回: 重定向到地址列表
	public String updateAddress(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Address address = new Address();
		Integer id = Integer.parseInt( request.getParameter("id") );
		address.setId(id);
		address.setConsigneeName( request.getParameter("consigneeName") );
		address.setConsigneeAddress( request.getParameter("consigneeAddress") );
		address.setConsigneePhone( request.getParameter("consigneePhone") );
		address.setPostcode( request.getParameter("postcode") );
		
		addressService.update(address);
		return "r:/account?method=addresslist";
	}
	// ------ 收货地址 -> 删除 -------
	// 功能: 删除收货地址
	// 参数: id（请求参数）- 地址ID
	// 返回: 重定向到地址列表
	public String deleteAddress(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Integer id = Integer.parseInt( request.getParameter("id") );
		
		addressService.delete(id);
		return "r:/account?method=addresslist";
	}
	
	// ------ 用户信息 -> 查看 -------
	// 功能: 查看用户详细信息
	// 参数: id（请求参数）- 用户ID
	// 返回页面: account-user-view.jsp
	public String viewUser(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		User user = userService.findById(id);
		request.setAttribute("user", user);
		
		return "account-user-view.jsp";
	}
	
	// ------ 用户信息 -> 编辑 页面 -------
	// 功能: 打开用户信息编辑页面
	// 参数: id（请求参数）- 用户ID
	// 返回页面: account-user-edit.jsp
	public String editUser(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		User user = userService.findById(id);
		request.setAttribute("user", user);
		
		return "account-user-edit.jsp";
	}
	// ------ 用户信息 -> 编辑 操作 -------
	// 功能: 更新用户信息（包括头像上传）
	// 参数: id, username, password, newPassword, email, phone, avatar(文件上传)
	// 返回页面: account-user-view.jsp
	public String updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		Integer id = Integer.parseInt( request.getParameter("id") );
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		String currPassword = userService.findPasswordById(id);
		
		if(password !=null && password.equals(currPassword) && newPassword != null && (!newPassword.isEmpty())) {
			password = newPassword;
		}else { 
			password = currPassword; 
		}
		
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		
		Part part = request.getPart("avatar");
		if(part.getSize() == 0) {	
			user.setAvatar(userService.findById(id).getAvatar());	
		}
		else {				
			String filePath = this.getServletContext().getRealPath("/");
			filePath = filePath + "\\member\\" + id;
			File file = new File(filePath);
			if( !file.exists()) {	
				file.mkdirs();
			}
			String fileName = part.getSubmittedFileName();
			String avatar = "member\\" + id + "\\" + fileName;
			part.write(filePath + "\\" + fileName);
			user.setAvatar(avatar);
		}
		
		userService.update(user);
		
		request.setAttribute("user", user);
		return "account-user-view.jsp";
	}
}
