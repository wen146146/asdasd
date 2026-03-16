package com.xhxy.eshop.listener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * 请求监听器
 *
 */
@WebListener
public class MyRequestListener implements ServletRequestListener {

	/**
	 * 初始化request（即生成）;方法需同步 synchronized，否则会出现读写问题
	 * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
	 */
	public synchronized void requestInitialized(ServletRequestEvent sre)  { 
		HttpServletRequest request = (HttpServletRequest)sre.getServletRequest();
		String filePath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/eshop.properties");
//		System.out.println(request.getRequestURI());
		
		try {
			// 读取 属性
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			
			Properties props = new Properties();
			props.load(in);
			
			int allRequest = Integer.parseInt(props.getProperty("allRequest"));
			int indexRequest = Integer.parseInt(props.getProperty("indexRequest"));
			
			in.close();
			// 设置 属性
			allRequest++;
			props.setProperty("allRequest", String.valueOf(allRequest));
			
			if(request.getRequestURI().endsWith("index")) {
				indexRequest++;
				props.setProperty("indexRequest", String.valueOf(indexRequest));
			}

			// 写入 属性文件
			OutputStream out = new BufferedOutputStream(new FileOutputStream(filePath));
			props.store(out,"eshop-index-properties");
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent sre)  { 
         // TODO Auto-generated method stub
    }

	
}
