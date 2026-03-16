package com.xhxy.eshop.util;

import java.util.ArrayList;
import java.util.List;

public class WordUtils {
		//采用单例模式
		private static List<String> wordList =new ArrayList<String>();//我们可以从这访问数据库中存的敏感词汇，封装成list返回
		static {	//手动添加几个
			wordList.add("坑货");
			wordList.add("骂人");
			wordList.add("脑残");
		}
		
		public static List<String> getword(){
			return wordList;
		}
		 
		 public static void addWord(String name){
			 wordList.add(name);
		 }
		 
		 public static void sava(){
			 // 以后可以把list里面的数据存到数据库中，方便维护，当然也可以写增删改查等
		 }
}
