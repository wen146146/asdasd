package com.xhxy.eshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xhxy.eshop.dao.AddressDao;
import com.xhxy.eshop.dao.UserDao;
import com.xhxy.eshop.entity.Address;
import com.xhxy.eshop.entity.User;
import com.xhxy.eshop.util.JdbcUtils;

public class AddressDaoImpl implements AddressDao {

	@Override
	public List<Address> findByUserId(Integer userId) {
		// SQL：查询某用户的全部收货地址
		String sql = "select * from address where user_id=?";
		try(
			Connection conn = JdbcUtils.getConnection();//JdbcUtils的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
			)
		{
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			List<Address> addressList = new ArrayList<Address>();
			while( rs.next()) {
				Address address = new Address();
				address.setId(rs.getInt("id"));
				address.setConsigneeName(rs.getString("consigneeName"));
				address.setConsigneeAddress(rs.getString("consigneeAddress"));
				address.setConsigneePhone(rs.getString("consigneePhone"));
				address.setPostcode(rs.getString("postcode"));
				
				addressList.add(address);
			}
			return addressList;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Address findById(Integer id) {
		// SQL：查询某个收货地址
		String sql = "select * from address where id = ?";
		try(
			Connection conn = JdbcUtils.getConnection();	//JdbcUtils工具类的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
			)
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if( rs!=null & rs.next()) {
				Address address = new Address();
				address.setId(rs.getInt("id"));
				address.setConsigneeName(rs.getString("consigneeName"));
				address.setConsigneeAddress(rs.getString("consigneeAddress"));
				address.setConsigneePhone(rs.getString("consigneePhone"));
				address.setPostcode(rs.getString("postcode"));
				
				// 设置Address的user属性
				UserDao userDao = new UserDaoImpl();
				User user = userDao.findById(rs.getInt("user_id"));
				address.setUser(user);
				
				return address;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Integer add(Address address) {
		String sql = "insert into address(consigneeName,consigneeAddress,consigneePhone,postcode,user_id)"
				+ " value(?,?,?,?,?)";
		try(
			Connection conn = JdbcUtils.getConnection();//JdbcUtils的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
			)
		{
			pstmt.setString(1, address.getConsigneeName());
			pstmt.setString(2, address.getConsigneeAddress());
			pstmt.setString(3, address.getConsigneePhone());
			pstmt.setString(4, address.getPostcode());
			
			pstmt.setInt(5, address.getUser().getId());
			
			int rows = pstmt.executeUpdate();
			if(rows >= 1)	// 若受影响的行数超过或等于1，则返回该值
				return rows;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public Integer update(Address address) {
		String sql = "update address set consigneeName=?,consigneeAddress=?,consigneePhone=?,postcode=? where id = ?";
		try(
			Connection conn = JdbcUtils.getConnection();	//JdbcUtils的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
			)
		{
			pstmt.setString(1, address.getConsigneeName());
			pstmt.setString(2, address.getConsigneeAddress());
			pstmt.setString(3, address.getConsigneePhone());
			pstmt.setString(4, address.getPostcode());
			
			pstmt.setInt(5, address.getId());
			
			int rows = pstmt.executeUpdate();
			return rows;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public Integer delete(Integer id) {
		String sql = "delete from address where id = ?";
		try(
			Connection conn = JdbcUtils.getConnection();	//JdbcUtils的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
			)
		{
			pstmt.setInt(1, id);
			
			int rows = pstmt.executeUpdate();
			return rows;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}


}
