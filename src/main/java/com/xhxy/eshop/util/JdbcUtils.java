package com.xhxy.eshop.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcUtils {
	// 静态方法：查找并返回数据源对象
		public static DataSource getDataSource() {
			DataSource dataSource = null;
			
			try {
				Context context = new InitialContext();
				dataSource = (DataSource)context.lookup("java:comp/env/jdbc/eshopDS");
			} catch (NamingException e) {
				e.printStackTrace();
			}
			return dataSource;
		}
		
		// 静态方法：返回连接对象
		public static Connection getConnection() {
			DataSource dataSource = getDataSource();	// 调用上面的静态方法，获取数据源对象
			Connection conn = null;
			
			try {
				conn = dataSource.getConnection();		// 获得到数据源的连接
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;	// 返回连接
		}
}
