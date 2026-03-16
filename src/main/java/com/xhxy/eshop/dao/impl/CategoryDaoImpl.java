package com.xhxy.eshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xhxy.eshop.dao.CategoryDao;
import com.xhxy.eshop.entity.Category;
import com.xhxy.eshop.util.JdbcUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public Category findById(Integer id) {
		// SQL：查询某个分类
		String sql = "select * from category where id=?";
		try(
			Connection conn = JdbcUtils.getConnection();//JDBC工具类
			PreparedStatement pstmt = conn.prepareStatement(sql)
		)
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setGrade(rs.getInt("grade"));
				return category;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> findAll() {
		// SQL：查询全部分类
		String sql = "select * from category";
		try(
			Connection conn = JdbcUtils.getConnection();//JDBC工具类
			PreparedStatement pstmt = conn.prepareStatement(sql)
				)
		{
			ResultSet rs = pstmt.executeQuery();
			List<Category> categoryList = new ArrayList<Category>();
			while( rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setGrade(rs.getInt("grade"));
				
				// 将该category 放入列表中
				categoryList.add(category);
			}
			return categoryList;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> findTopCategory() {
		// SQL：查询顶层分类
		String sql = "select * from category where grade = 0";
		try(
			Connection conn = JdbcUtils.getConnection();//JDBC工具类
			PreparedStatement pstmt = conn.prepareStatement(sql)
				)
		{
			ResultSet rs = pstmt.executeQuery();
			List<Category> topCategoryList = new ArrayList<Category>();
			while( rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setGrade(rs.getInt("grade"));
				category.setIcon(rs.getString("icon"));
				
				// 查询该顶层分类的子分类列表
				CategoryDao categoryDao = new CategoryDaoImpl();
				List<Category> childCategoryList = categoryDao.findChildCategory(rs.getInt("id"));
				category.setChildren(childCategoryList);
				
				topCategoryList.add(category);
			}
			return topCategoryList;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> findChildCategory(Integer parentId) {
		// SQL：查询某顶层分类的子分类
		String sql = "select * from category where parent = ?";
		try(
			Connection conn = JdbcUtils.getConnection();//JDBC工具类
			PreparedStatement pstmt = conn.prepareStatement(sql)
				)
		{
			pstmt.setInt(1, parentId);
			ResultSet rs = pstmt.executeQuery();
			List<Category> childCategoryList = new ArrayList<Category>();
			while( rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setGrade(rs.getInt("grade"));
				
				childCategoryList.add(category);
			}
			return childCategoryList;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
