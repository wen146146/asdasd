package com.xhxy.eshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xhxy.eshop.dao.CartDao;
import com.xhxy.eshop.dao.CartItemDao;
import com.xhxy.eshop.dao.ProductDao;
import com.xhxy.eshop.entity.Cart;
import com.xhxy.eshop.entity.CartItem;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.util.JdbcUtils;

public class CartItemDaoImpl implements CartItemDao {

	@Override
	public List<CartItem> findByCartId(Integer cartId) {
		String sql = "select * from cartitem where cart_id=?";
		try(
				Connection conn = JdbcUtils.getConnection();// JdbcUtils的静态方法
				PreparedStatement pstmt = conn.prepareStatement(sql)
					)
			{
				pstmt.setInt(1, cartId);
				ResultSet rs = pstmt.executeQuery();
				if(rs != null ) {
					List<CartItem> cartItemList = new ArrayList<CartItem>();
					while(rs.next()) {
						CartItem cartItem = new CartItem();
						cartItem.setId(rs.getInt("id"));
						cartItem.setQuantity(rs.getInt("quantity"));
						cartItem.setTotal(rs.getFloat("total"));
						
						// 设置CartItem的cart属性。此处使用，会造成cartItem和cart递归获取对方。
//						CartDao cartDao = new CartDaoImpl();
//						Cart cart = cartDao.findById(rs.getInt("cart_id"));
//						cartItem.setCart(cart);
						
						// 设置CartItem的product属性
						ProductDao productDao = new ProductDaoImpl();
						Product product = productDao.findById(rs.getInt("product_id"));
						cartItem.setProduct(product);
						
						cartItemList.add(cartItem);
					}
					return cartItemList;
				}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			return null;
	}

	
	@Override
	public Integer add(Product product, int quantity, Float total, int cartId) {
		
		// 1.先检查 该购物车中是否有该项，若有，则更新其quantity + 1即可
		
		// 2.若无此项，则插入
		String sql = "insert into cartitem(product_id,quantity,cart_id,total) values(?,?,?,?)";
		try(
			Connection conn = JdbcUtils.getConnection();//JDBC工具类的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)
				)
		{
			pstmt.setInt(1, product.getId());
			pstmt.setInt(2, quantity);
			pstmt.setInt(3, cartId);
			pstmt.setFloat(4, total);
			
			pstmt.executeUpdate();
			
			ResultSet generatedKeys = pstmt.getGeneratedKeys();	// 获得刚插入行的主键（自增长）

			while (generatedKeys.next()) {

			   return generatedKeys.getInt(1);

			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}


	@Override
	public Integer deleteByCartId(Integer cartId) {
		String sql = "delete from cartitem where cart_id = ?";
		try(
			Connection conn = JdbcUtils.getConnection();	// JdbcUtils的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
				)
		{
			pstmt.setInt(1, cartId);
			return pstmt.executeUpdate(); //返回删除的行数
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}


	@Override
	public Integer findByCartIdAndProductId(Integer cartId, Integer productId) {
		String sql = "select * from cartitem where cart_id = ? and product_id = ?";
		try(
				Connection conn = JdbcUtils.getConnection();// JdbcUtils的静态方法
				PreparedStatement pstmt = conn.prepareStatement(sql)
			)
			{
				pstmt.setInt(1, cartId);
				pstmt.setInt(2, productId);
				ResultSet rs = pstmt.executeQuery();
				if(rs != null && rs.next()) {
					return rs.getInt("id");
				}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			return null;
	}


	@Override
	public Integer update(Integer cartItemId, Integer quantity, Float total) {
		String sql = "update cartitem set quantity = ?, total = ? where id = ?";
		try(
				Connection conn = JdbcUtils.getConnection();// JdbcUtils的静态方法
				PreparedStatement pstmt = conn.prepareStatement(sql)
			)
			{
				pstmt.setInt(1, quantity);
				pstmt.setInt(2, cartItemId);
				pstmt.setFloat(3, total);
				
				return pstmt.executeUpdate();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			return null;
	}


	@Override
	public CartItem findById(Integer id) {
		// SQL：查询某个cartItem
		String sql = "select * from cartitem where id = ?";
		try(
			Connection conn = JdbcUtils.getConnection();	//JdbcUtils工具类的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
			)
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if( rs!=null & rs.next()) {
				CartItem cartItem = new CartItem();
				cartItem.setId(rs.getInt("id"));
				cartItem.setQuantity(rs.getInt("quantity"));
				cartItem.setTotal(rs.getFloat("total"));
				
				// 设置CartItem的cart属性
				CartDao cartDao = new CartDaoImpl();
				Cart cart = cartDao.findById(rs.getInt("cart_id"));
				cartItem.setCart(cart);
				
				// 设置CartItem的product属性
				ProductDao productDao = new ProductDaoImpl();
				Product product = productDao.findById(rs.getInt("product_id"));
				cartItem.setProduct(product);
				
				return cartItem;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
