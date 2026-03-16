package com.xhxy.eshop.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Blog;
import com.xhxy.eshop.entity.Comment;
import com.xhxy.eshop.service.BlogService;
import com.xhxy.eshop.service.CommentService;
import com.xhxy.eshop.service.UserService;
import com.xhxy.eshop.service.impl.BlogServiceImpl;
import com.xhxy.eshop.service.impl.CommentServiceImpl;
import com.xhxy.eshop.service.impl.UserServiceImpl;

// 推荐文章：列表、详情、评论
@WebServlet("/blog")
public class BlogController  extends BaseServlet {

	private BlogService blogService = new BlogServiceImpl();
	private UserService userService = new UserServiceImpl();
	private CommentService commentService = new CommentServiceImpl();
	
	// 文章列表
	public String index(HttpServletRequest request, HttpServletResponse response) {
		List<Blog> blogs = blogService.findAll();
		
		request.setAttribute("blogs", blogs);
		return "blog-list.jsp";
	}
	
	// 文章详情
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		Integer blogId = Integer.parseInt(request.getParameter("id"));
		Blog blog = blogService.findByBlogId(blogId);
		
		request.setAttribute("blog", blog);
		return "blog-detail.jsp";
	}
	// 提交评论
	public String saveComment(HttpServletRequest request, HttpServletResponse response) {
		Integer blogId = Integer.parseInt(request.getParameter("blogId"));
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String content = request.getParameter("content");
		
		Comment comment = new Comment();
		comment.setBlog(blogService.findByBlogId(blogId));
		comment.setUser(userService.findById(userId));
		comment.setContent(content);
		comment.setCreateTime(new java.util.Date());
		
		commentService.save(comment);
		
		return "r:/blog?method=detail&id="+blogId;	// 使用重定向
	}
}
