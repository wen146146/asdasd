<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>推荐文章</title>
	<link rel="shortcut icon" href="assets/images/favicon.png" />
	<link href="assets/css/theme-plugin.css" rel="stylesheet" />
	<link href="assets/css/theme.min.css" rel="stylesheet" />
</head>

<body>

<!-- page wrapper start -->

<div class="page-wrapper">
	<%@ include file="header.jsp" %>
	
	<!--hero section start-->
	<section class="bg-light py-6">
	  <div class="container">
	    <div class="row align-items-center">
	      <div class="col-md-6">
	        <h1 class="h2 mb-0">好物推荐</h1>
	      </div>
	      <div class="col-md-6 mt-3 mt-md-0">
	        <nav aria-label="breadcrumb">
	          <ol class="breadcrumb justify-content-md-end bg-transparent p-0 m-0">
	            <li class="breadcrumb-item"><a class="link-title" href="index">首页</a></li>
	            <li class="breadcrumb-item"><a class="link-title" href="blog">好物推荐</a></li>
	            <li class="breadcrumb-item active text-primary" aria-current="page">详情</li>
	          </ol>
	        </nav>
	      </div>
	    </div>
	    <!-- / .row -->
	  </div>
	  <!-- / .container -->
	</section>
	<!--hero section end--> 

	<!--body content start-->
	<div class="page-content">
	<!--blog start-->
	<section>
	  <div class="container">
	    <div class="row">
	      <div class="col-12">
	        <!-- Blog Card -->
	        <div class="card border-0 bg-transparent">
	          <div class="position-relative rounded overflow-hidden">
	          	<div class="position-absolute z-index-1 bg-white text-pink text-center py-2 px-3 my-4 blog-info rounded-right">
	          	<i class="las la-calendar-check"></i> ${blog.createTime} <span>|</span> 
	          	<i class="las la-comment-alt"></i> <a href="#">${fn:length(blog.commentList)} 条评论</a></div>
	            <img src="${blog.pic}" alt="标题图片" />
	          </div>
	          <div class="card-body pt-5 px-0">
	            <h2 class="font-w-6 mb-3 line-h-normal link-title"> ${blog.title}  </h2>
	            <p>${blog.content}</p>
	      	  </div>
	          <div class="comment-area mt-5">
	             <div class="content_title">
	                 <h4>评论</h4>
	             </div>
	             <ul class="list_none comment_list">
					 <c:forEach var="comment" items="${blog.commentList}">
	                    <li class="comment_info">
	                        <div class="d-flex">
	                            <div class="comment_user">
	                                <img src="assets/images/thumbnail/member3.png" alt="user4">
	                            </div>
	                            <div class="comment_content">
	                                 <div class="d-flex">
	                                     <div class="meta_data">
	                                         <h6><a href="#">${comment.user.username} </a></h6>
	                                         <div class="comment-time">${comment.createTime}</div>
	                                     </div>
	                                 </div>
	                                 <p>${comment.content}</p>
	                            </div>
	                        </div>
	                    </li>
	                 </c:forEach>
	              </ul>
	          <div class="post-comments mt-8 bg-light-4 rounded p-5">
	            <c:choose>
				   <c:when test="${username == null}">
				      <div class="mb-5">
			              <h5><a href="user?method=login">登录</a>后，方可评论</h5>
			          </div>
				   </c:when>
				   <c:otherwise>
			          <div class="mb-5">
			             <h5>我要评论</h5>
			          </div>
			          <form class="row" method="post" action="blog?method=saveComment">
			             <input type="hidden" name="blogId" value="${blog.id}">
			             <input type="hidden" name="userId" value="${id}">
			             <div class="messages"></div>
			             <div class="form-group mb-0 col-sm-12">
			               <textarea name="content" class="form-control h-100" placeholder="请文明发言。" rows="4" required data-error="请输入评论。"></textarea>
			               <div class="help-block with-errors"></div>
			             </div>
			             <div class="col-sm-12">
			               <input type="submit" class="btn btn-primary btn-animated mt-5" value="提交"/>
			             </div>
			          </form>
				   </c:otherwise>
		        </c:choose>

	          </div>
	        </div>
	        <!-- End Blog Card -->
	        </div>
	    </div>
	  </div>
	  </div>
	</section>
	<!--blog end-->
	</div>
	<!--body content end--> 
	<!-- page wrapper end -->

	<%@ include file="footer.jsp" %>
	</div>
</body>
</html>
