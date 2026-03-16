package com.xhxy.eshop.listener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Application Lifecycle Listener implementation class MyContextListener
 *
 */
@WebListener
public class MyContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * 关闭网站时:1.取消驱动程序的注册；2.关闭线程
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	//0.手动取消c3p0数据库连接池
//        try {
//            DataSource.destroy(JdbcUtils.getDataSource());	//getDataSource方法获取数据源
//            System.out.println("关闭数据库连接池成功!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //1.手动取消驱动程序的注册：
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = (Driver) drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("取消注册jdbc driver: "+driver);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error deregistering driver"+driver);
            }
        }
        //2.手动停止名为[mysql-cj-abandoned-connection-cleanup]的线程
        AbandonedConnectionCleanupThread.uncheckedShutdown();
    }

	/**
     * 启动网站时
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    }
	
}
