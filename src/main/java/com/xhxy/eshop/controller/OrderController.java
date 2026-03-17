package com.xhxy.eshop.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Address;
import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.service.AddressService;
import com.xhxy.eshop.service.CartService;
import com.xhxy.eshop.service.OrderService;
import com.xhxy.eshop.service.impl.mybatis.AddressServiceImpl;
import com.xhxy.eshop.service.impl.mybatis.CartServiceImpl;
import com.xhxy.eshop.service.impl.mybatis.OrderServiceImpl;

// OrderController: 订单控制器
// 访问路径: /order
// 涉及页面: order-create.jsp(创建订单页面), order-complete.jsp(订单完成页面)
// 功能: 1.创建订单页面 2.生成并完成订单
@WebServlet("/order")
public class OrderController extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private CartService cartService = new CartServiceImpl();
	private OrderService orderService = new OrderServiceImpl();
	private AddressService addressService = new AddressServiceImpl();
	
	// 创建订单页面
	// 功能: 获取用户购物车信息和收货地址列表，用于下单
	// 参数: 从session获取用户ID
	// 返回页面: order-create.jsp
	// 数据库操作: CartService.findByUserId() -> 查询用户购物车
	//             AddressService.findByUserId() -> 查询用户收货地址列表
	public String create(HttpServletRequest request, HttpServletResponse response) {
		int userId = (int)request.getSession().getAttribute("id");
		
		Cart cart = cartService.findByUserId(userId);
		request.setAttribute("cart", cart);
		
		List<Address> addressList = addressService.findByUserId(userId);
		request.setAttribute("addressList", addressList);
		
		return "order-create.jsp";
	}
	
	// 生成并完成订单
	// 功能: 根据购物车和选中的收货地址创建订单
	// 参数: addressId（请求参数）- 收货地址ID，用户ID从session获取
	// 返回页面: order-complete.jsp
	// 数据库操作: CartService.findByUserId() -> 查询用户购物车
	//             AddressService.findById() -> 查询收货地址
	//             OrderService.createOrder() -> 创建订单（包含多个数据库操作）
	public String complete(HttpServletRequest request, HttpServletResponse response) {
		
		Integer id = (Integer)request.getSession().getAttribute("id");
		Cart cart = cartService.findByUserId(id);
		
		Integer addressId = Integer.parseInt( request.getParameter("addressId"));
		Address address = addressService.findById(addressId);
		
		orderService.createOrder(cart, address); 
		
		return "/order-complete.jsp";
	}
}
