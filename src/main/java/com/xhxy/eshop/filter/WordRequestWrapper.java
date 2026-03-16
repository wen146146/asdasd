package com.xhxy.eshop.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.xhxy.eshop.util.WordUtils;

// 采用包装模式
public class WordRequestWrapper extends HttpServletRequestWrapper {
	
		public WordRequestWrapper(HttpServletRequest request) {
			super(request);
		}
 
		@Override 	//重写这个方法
		public String getParameter(String param) {
			String words =super.getParameter(param);
//			System.out.println(words);//过滤前的文字
			
			if(words != null) {
				List<String> wordList=WordUtils.getword();	// 取得敏感词列表
				for(String word:wordList){	// 脑残   骂人   ....
					words=words.replace(word, "**");			// 敏感词汇采用**代替
				}
			}
			
			return words;
		}	
}
