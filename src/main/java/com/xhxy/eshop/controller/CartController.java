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
import com.xhxy.eshop.service.impl.mybatis.CartServiceImpl;
import com.xhxy.eshop.service.impl.mybatis.ProductServiceImpl;

// CartController: 购物车控制器
// 访问路径: /cart
// 涉及页面: cart.jsp(购物车页面)
// 功能: 1.查看购物车 2.添加商品到购物车 3.删除购物车商品(未实现)
@WebServlet("/cart")
public class CartController extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private CartService cartService = new CartServiceImpl();
	private ProductService productService = new ProductServiceImpl();

	// 查看购物车
	// 功能: 获取当前用户的购物车信息，包括购物车中的商品列表
	// 参数: 从session获取用户ID
	// 返回页面: cart.jsp
	// 数据库操作: CartService.findByUserId() -> 根据用户ID查询购物车
	public String view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userId = (Integer)request.getSession().getAttribute("id");	
		
		Cart cart = cartService.findByUserId(userId);
		
		if(cart != null) {
			request.setAttribute("cart", cart);
			
			return "cart.jsp";
		}else {
			return "404.jsp";
		}
	}
	
	// 添加商品到购物车
	// 功能: 将指定商品添加到当前用户的购物车中
	// 参数: id（请求参数）- 商品ID，用户ID从session获取
	// 返回: 重定向到购物车页面查看
	// 数据库操作: ProductService.findById() -> 查询商品信息
	//             CartService.add() -> 添加商品到购物车
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userId = (Integer)request.getSession().getAttribute("id");
		Integer productId = Integer.parseInt(request.getParameter("id"));
		
		Product product = productService.findById(productId);
		Integer cartId = cartService.findByUserId(userId).getId();
		
		cartService.add(product, 1, cartId);
		
		return "cart?method=view";
	}
	
	// 删除购物车商品（尚未实现）
	// 功能: 从购物车中删除指定商品
	// 参数: id（请求参数）- 购物车商品ID
	// 返回: 暂无
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return null;
	}

}
