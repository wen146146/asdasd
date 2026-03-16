package com.xhxy.eshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xhxy.eshop.dao.OrderItemDao;
import com.xhxy.eshop.dao.ProductDao;
import com.xhxy.eshop.entity.OrderItem;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.util.JdbcUtils;

public class OrderItemDaoImpl implements OrderItemDao {
	@Override
	public List<OrderItem> findByOrderId(Integer orderId) {
		String sql = "select * from orderitem where order_id=?";
		try(
				Connection conn = JdbcUtils.getConnection();//JdbcUtils的静态方法
				PreparedStatement pstmt = conn.prepareStatement(sql)
					)
			{
				pstmt.setInt(1, orderId);
				ResultSet rs = pstmt.executeQuery();
				if(rs != null ) {
					List<OrderItem> orderItemList = new ArrayList<OrderItem>();
					while(rs.next()) {
						OrderItem orderItem = new OrderItem();
						orderItem.setId(rs.getInt("id"));

						ProductDao productDao = new ProductDaoImpl();
						Product product = productDao.findById(rs.getInt("product_id"));
						orderItem.setProduct(product);
						
						orderItem.setQuantity(rs.getInt("quantity"));
						orderItem.setTotal(product.getPrice() * orderItem.getQuantity());
						
						orderItemList.add(orderItem);
					}
					return orderItemList;
				}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			return null;
	}

	
	@Override
	public Integer save(OrderItem orderItem) {
		String sql = "insert into orderitem(product_id,quantity,order_id,total) values(?,?,?,?)";
		try(
				Connection conn = JdbcUtils.getConnection();//JdbcUtils的静态方法
				PreparedStatement pstmt = conn.prepareStatement(sql)
			)
			{
				pstmt.setInt(1, orderItem.getProduct().getId());
				pstmt.setInt(2, orderItem.getQuantity());
				pstmt.setInt(3, orderItem.getOrder().getId());
				pstmt.setFloat(4, orderItem.getTotal());
				
				return pstmt.executeUpdate();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			return 0;
	}


	@Override
	public Integer batchSave(List<OrderItem> orderItemList) {
		String sql = "insert into orderitem(product_id,quantity,order_id,total) values(?,?,?,?)";
		try(
				Connection conn = JdbcUtils.getConnection();//JdbcUtils的静态方法
				PreparedStatement pstmt = conn.prepareStatement(sql)
			)
			{
				for(OrderItem orderItem:orderItemList) {
					pstmt.setInt(1, orderItem.getProduct().getId());
					pstmt.setInt(2, orderItem.getQuantity());
					pstmt.setInt(3, orderItem.getOrder().getId());
					pstmt.setFloat(4, orderItem.getTotal());
				
					pstmt.executeUpdate();
				}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			return 0;
	}

}
