package com.xhxy.eshop.listener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 会话监听器
 *
 */
@WebListener
public class MySessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public MySessionListener() {
    }

	/**
     * 创建会话时；同步
     */
    public synchronized void sessionCreated(HttpSessionEvent se)  { 
		HttpSession session = se.getSession();
		
		String filePath = session.getServletContext().getRealPath("/WEB-INF/classes/eshop.properties");
		
		try {
			// 1. 读取 属性
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			
			Properties props = new Properties();
			props.load(in);
			int onlineUser = Integer.parseInt(props.getProperty("onlineUser"));
			
			in.close();
			// 2. 设置 属性
			onlineUser++;
			props.setProperty("onlineUser", String.valueOf(onlineUser));
			
			// 3.写入 属性文件
			OutputStream out = new BufferedOutputStream(new FileOutputStream(filePath));
			props.store(out,"eshop-index-properties");
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	/**
     * 销毁会话时
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
		HttpSession session = se.getSession();
		
		String filePath = session.getServletContext().getRealPath("/WEB-INF/classes/eshop.properties");
		
		try {
			// 1. 读取 属性
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			
			Properties props = new Properties();
			props.load(in);
			int onlineUser = Integer.parseInt(props.getProperty("onlineUser"));
			
			in.close();
			// 2. 设置 属性
			onlineUser--;
			props.setProperty("onlineUser", String.valueOf(onlineUser));
			
			// 3.写入 属性文件
			OutputStream out = new BufferedOutputStream(new FileOutputStream(filePath));
			props.store(out,"eshop-index-properties");
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
}
