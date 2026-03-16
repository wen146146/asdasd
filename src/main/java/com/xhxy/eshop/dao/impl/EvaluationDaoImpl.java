package com.xhxy.eshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xhxy.eshop.dao.EvaluationDao;
import com.xhxy.eshop.dao.UserDao;
import com.xhxy.eshop.entity.Evaluation;
import com.xhxy.eshop.entity.User;
import com.xhxy.eshop.util.JdbcUtils;

public class EvaluationDaoImpl implements EvaluationDao{

	@Override
	public List<Evaluation> findByProductId(Integer productId) {
		// SQL：查询某个分类
		String sql = "select * from evaluation where product_id=?";
		try(
			Connection conn = JdbcUtils.getConnection();//JDBC工具类的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
		)
		{
			pstmt.setInt(1, productId);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null) {
				List<Evaluation> evaluationList = new ArrayList<>();
				while( rs.next()) {
					Evaluation evaluation = new Evaluation();
					evaluation.setId(rs.getInt("id"));
					evaluation.setContent(rs.getString("content"));
					evaluation.setCreateTime(rs.getDate("createtime"));
					
					//评价人
					UserDao userDao = new UserDaoImpl();
					User user = userDao.findById(rs.getInt("user_id"));
					evaluation.setUser(user);
					
					evaluationList.add(evaluation);
				}
				return evaluationList;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
