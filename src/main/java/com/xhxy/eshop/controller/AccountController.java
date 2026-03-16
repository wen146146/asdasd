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
import com.xhxy.eshop.service.impl.AddressServiceImpl;
import com.xhxy.eshop.service.impl.OrderServiceImpl;
import com.xhxy.eshop.service.impl.UserServiceImpl;

// 账号中心：处理 订单列表、详情、收货地址的增删改查、个人信息修改
@WebServlet("/account")
@MultipartConfig(location="D:\\",fileSizeThreshold=1024)	// 上传文件的注解
public class AccountController   extends BaseServlet{
	
	private UserService userService = new UserServiceImpl();
	private OrderService orderService = new OrderServiceImpl();
	private AddressService addressService = new AddressServiceImpl();
	
	// 账号中心-->“首页”
	public String index(HttpServletRequest request, HttpServletResponse response) {
		Integer id = (Integer)request.getSession().getAttribute("id");// 已登录用户的id
		User user = userService.findById(id);
		request.getSession().setAttribute("user", user);
		
		return "account-dashboard.jsp";
	}
	
	// 账号中心-->“首页”，同上
	public String dashboard(HttpServletRequest request, HttpServletResponse response) {
		Integer id = (Integer)request.getSession().getAttribute("id");// 已登录用户的id
		User user = userService.findById(id);
		request.getSession().setAttribute("user", user);
		
		return "account-dashboard.jsp";
	}
	
	// ------ 订单 -> 列表 -------
	public String orderlist(HttpServletRequest request, HttpServletResponse response) {
		Integer id = (Integer)request.getSession().getAttribute("id");// 已登录用户的id
		
		List<Order> orders = orderService.getByUserId(id);
		request.setAttribute("orders", orders);
		
		User user = userService.findById(id);
		request.setAttribute("user", user);
		
		return "account-order-list.jsp";
	}
	// ------ 订单 -> 查看单个 -------
	public String orderView(HttpServletRequest request, HttpServletResponse response) {
		Integer orderId = Integer.parseInt(request.getParameter("id")); //订单Id
		
		Order order = orderService.findById(orderId);
		request.setAttribute("order", order);
		
		return "account-order-view.jsp";
	}
	
	// ------ 收货地址 -> 列表 -------
	public String addresslist(HttpServletRequest request, HttpServletResponse response) {
		Integer id = (Integer)request.getSession().getAttribute("id");// 已登录用户的id
		
		List<Address> addressList = addressService.findByUserId(id);
		request.getSession().setAttribute("addressList", addressList);
		
		return "account-address-list.jsp";
	}
	// ------ 收货地址 -> 编辑 -------
	public String editAddress(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));// 收货地址的id
		
		Address address = addressService.findById(id);
		request.getSession().setAttribute("address", address);
		
		return "account-address-edit.jsp";
	}
	// ------ 收货地址 -> 新增 -------
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
		
		return "r:/account?method=addresslist";	//需要用重定向redirect，否则页面依然是旧数据
	}
	// ------ 收货地址 -> 更新 -------
	public String updateAddress(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		// 
		Address address = new Address();
		// 1.获取请求参数
		
		Integer id = Integer.parseInt( request.getParameter("id") );//Address的id属性
		address.setId(id);
		address.setConsigneeName( request.getParameter("consigneeName") );	// 收货人姓名
		address.setConsigneeAddress( request.getParameter("consigneeAddress") ); // 收货具体地址
		address.setConsigneePhone( request.getParameter("consigneePhone") );//收货电话
		address.setPostcode( request.getParameter("postcode") );
		// address的user属性不用改变，因为不会修改user
		
		// 3、让数据层去更新数据
		addressService.update(address);
		// 4.返回表示层
		return "r:/account?method=addresslist"; //需要用重定向redirect，否则页面依然是旧数据
	}
	// ------ 收货地址 -> 删除 -------
	public String deleteAddress(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		// 
		// 1.获取请求参数
		
		Integer id = Integer.parseInt( request.getParameter("id") );//Address的id属性
		
		// 2、让数据层去更新数据
		addressService.delete(id);
		// 4.返回表示层
		return "r:/account?method=addresslist"; //需要用重定向redirect，否则页面依然是旧数据
	}
	
	// ------ 用户信息 -> 查看 -------
	public String viewUser(HttpServletRequest request, HttpServletResponse response) {
//		Integer id = (Integer)request.getSession().getAttribute("id");// 已登录用户的id
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		User user = userService.findById(id);
		request.setAttribute("user", user);
		
		return "account-user-view.jsp";
	}
	
	// ------ 用户信息 -> 编辑 页面-------
	public String editUser(HttpServletRequest request, HttpServletResponse response) {
//		Integer id = (Integer)request.getSession().getAttribute("id");// 已登录用户的id
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		User user = userService.findById(id);
		request.setAttribute("user", user);
		
		return "account-user-edit.jsp";
	}
	// ------ 用户信息 -> 编辑 操作-------
	public String updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		// 1.获取请求参数
		Integer id = Integer.parseInt( request.getParameter("id") );
		String username = request.getParameter("username");
		String password = request.getParameter("password");		// 当前（旧）密码为空，保持密码不变
		String newPassword = request.getParameter("newPassword");//新密码
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		// 2.要不要替换密码
		String currPassword = userService.findPasswordById(id);
		
		if(password !=null && password.equals(currPassword) && newPassword != null && (!newPassword.isEmpty())) {
			password = newPassword;
		}else { // 保持原密码
			password = currPassword; 
		}
		// 3.User的常规属性
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		
		// 4.上传头像文件的处理
		Part part = request.getPart("avatar");
		if(part.getSize() == 0) {	// 若没上传头像，则保持原头像不变
			user.setAvatar(userService.findById(id).getAvatar());	
		}
		else {				// 若上传头像，则
			String filePath = this.getServletContext().getRealPath("/");
			filePath = filePath + "\\member\\" + id;
			File file = new File(filePath);
			if( !file.exists()) {	// 若目录不存在，则创建目录
				file.mkdirs();
			}
			String fileName = part.getSubmittedFileName();
			String avatar = "member\\" + id + "\\" + fileName;	// 在web根文件夹的相对路径文件
			part.write(filePath + "\\" + fileName);
			user.setAvatar(avatar);
		}
		
		// 4、让数据层去更新数据
		userService.update(user);
		
		// 5.返回表示层
		request.setAttribute("user", user);
		return "account-user-view.jsp";
	}
}
