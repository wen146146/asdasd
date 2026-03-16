package com.xhxy.eshop.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * 对敏感词进行过滤，用**代替
 */
@WebFilter(filterName = "wordFilter", description = "敏感词过滤", urlPatterns = { "/*" })
public class WordFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public WordFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    	// TODO Auto-generated method stub
    }
    
    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	// TODO Auto-generated method stub
    	// 调用包装类
    	HttpServletRequest req = (HttpServletRequest)request;	// 转换为HTTPServletRequest类型
    	WordRequestWrapper wrapper =new WordRequestWrapper(req);
    	// 继续沿 过滤链 往下传
    	chain.doFilter(wrapper, response);
    }
    
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
