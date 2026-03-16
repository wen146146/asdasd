package com.xhxy.eshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.service.CartService;
import com.xhxy.eshop.service.ProductService;
import com.xhxy.eshop.service.impl.CartServiceImpl;
import com.xhxy.eshop.service.impl.ProductServiceImpl;

/**
 * 购物车的控制器类：查看购物车、添加商品到购物车
 */
@WebServlet("/cart")
public class CartController extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private CartService cartService = new CartServiceImpl();
	private ProductService productService = new ProductServiceImpl();

	// 查看购物车
	public String view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求参数
		Integer userId = (Integer)request.getSession().getAttribute("id");// 会话中的已登录的用户id		
		
		Cart cart = cartService.findByUserId(userId);
		
		if(cart != null) {
			request.setAttribute("cart", cart);
			
			return "cart.jsp";
		}else {
			return "404.jsp";
		}
	}
	
	// 增加商品到购物车
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求参数
		Integer userId = (Integer)request.getSession().getAttribute("id");	// 会话中的已登录的用户id
		Integer productId = Integer.parseInt(request.getParameter("id"));	// 请求参数的商品id
		
		Product product = productService.findById(productId);				// 取得商品product
		Integer cartId = cartService.findByUserId(userId).getId();			// 取得该用户的购物车id
		
		cartService.add(product, 1, cartId);	//将1件product放入购物车cartId
		
		return "cart?method=view";
	}
	
	// 尚未实现：删除购物车商品
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求参数
//		String id = request.getParameter("id");
		return null;
	}

}
