<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>登录</title>
	<link rel="shortcut icon" href="assets/images/favicon.png" />
	<link href="assets/css/theme-plugin.css" rel="stylesheet" />
	<link href="assets/css/theme.min.css" rel="stylesheet" />
</head>

<body class="bg-light-4">

<!-- page wrapper start -->

<div class="page-wrapper"> 
  
  	<%@ include file="header.jsp" %>

	<div class="page-content">

	<!--login start-->

	<section class="bg-light">
	  <div class="container">
	    <div class="row justify-content-center">
	      <div class="col-12 col-lg-6 col-md-8 col-sm-11">
	        <div class="shadow p-6 login bg-white">
	         <div class="store-name">eshop</div>
	          <h4 class="text-left mb-3 font-w-5">用户登录</h4>
	          
	          <form id="login-form" method="post" action="user?method=login">
	            <div class="messages">${message}</div>
	            <div class="form-group">
	              <input id="form_name" type="text" name="username" class="form-control" placeholder="用户名" required data-error="用户名是必须字段">
	              <div class="help-block with-errors"></div>
	            </div>
	            <div class="form-group">
	              <input id="form_password" type="password" name="password" class="form-control" placeholder="Password" required data-error="密码是必须字段">
	              <div class="help-block with-errors"></div>
	            </div>
	            <div class="form-group mt-4 mb-5">
	              <div class="remember-checkbox d-flex align-items-center justify-content-between">
	                <div class="checkbox">
	                  <input type="checkbox" name="autologin" value="checked" />
	                  <label>记住我，下次自动登录</label>
	                </div>
	                 <a href="#" data-original-title="将在SpringMVC课程实现" data-toggle="tooltip">忘记密码?</a>
	              </div>
	            </div> 
	            <!-- <input type="checkbox" name="autologin" value="checked" /> -->
	            <!-- <a href="UserServlet" class="btn btn-primary btn-block" onclick="document.getElementById('login-form').submit();">登录</a>  -->
	            <input type="submit" value="登录" class="btn btn-primary btn-block"/>
	          </form>
	          
	          
	          <div class="d-flex align-items-center text-center justify-content-center mt-4">
	                <span class="text-muted mr-1">还没有账号?</span><a href="signup.jsp">注册</a>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</section>
	
	<!--login end-->
	
	</div>

    <%@ include file="footer.jsp" %>
	</div>
</body>
</html>
