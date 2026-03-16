<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>个人中心</title>
	<link rel="shortcut icon" href="assets/images/favicon.png" />
	<link href="assets/css/theme-plugin.css" rel="stylesheet" />
	<link href="assets/css/theme.min.css" rel="stylesheet" />
</head>

<body class="">

<!-- page wrapper start -->

<div class="page-wrapper"> 
  
  <!--header start-->
    <%@ include file="header.jsp" %>	
  <!--header end--> 

	<!--hero section start-->
	<section class="bg-light py-6">
	  <div class="container">
	    <div class="row align-items-center">
	      <div class="col-md-6">
	        <h1 class="h2 mb-0">我的账号</h1>
	      </div>
	      <div class="col-md-6 mt-3 mt-md-0">
	        <nav aria-label="breadcrumb">
	          <ol class="breadcrumb justify-content-md-end bg-transparent p-0 m-0">
	            <li class="breadcrumb-item"><a class="link-title" href="index.jsp">首页</a>
	            </li>
	            <li class="breadcrumb-item active text-primary" aria-current="page">个人中心</li>
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

	<section class="dashboard-page">
	  <div class="container">
	        <div class="row">
	            <div class="col-lg-3 col-md-4">
	                <div class="dashboard_menu">
	                    <ul class="nav nav-tabs border-0 flex-column" role="tablist">
	                      <li class="nav-item">
	                        <a class="nav-link active" href="account?method=dashboard&id=${id}" aria-selected="true">
	                        主页面</a>
	                      </li>
	                      <li class="nav-item">
	                        <a class="nav-link" href="account?method=orderlist&id=${id}" aria-selected="false">
	                        我的订单</a>
	                      </li>
	                      <li class="nav-item">
	                        <a class="nav-link" href="account?method=addresslist&id=${id}" aria-selected="false">
	                        收货地址</a>
	                      </li>
	                      <li class="nav-item">
	                        <a class="nav-link" href="account?method=viewUser&id=${id}" aria-selected="false">
	                        账号信息</a>
	                      </li>
	                      <li class="nav-item">
	                        <a class="nav-link" href="cart?method=view&id=${id}">
	                        购物车</a>
	                      </li>
	                      <li class="nav-item">
	                        <a class="nav-link" href="user?method=logout">
	                        退出</a>
	                      </li>
	                    </ul>
	                </div>
	            </div>
	            <div class="col-lg-9 col-md-8 mt-5 mt-lg-0 mt-md-0">
	                <div class="dashboard_content">
	                  	<div class="tab-pane active show" id="dashboard" role="tabpanel" aria-labelledby="dashboard-tab">
	                    	<div class="card border-0">
	                        	
	                            <div class="card-body">
	                    			<div class="welcome-msg">
					                <h6>您好, ${username}！</h6>
					                <p>欢迎您使用个人中心。可以查看和修改本人的信息，也可以新增、查看、编辑收货地址，查看订单信息。</p>
					              </div>
	                            </div>
	                        </div>
	                  	</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	</div>
	<!--body content end--> 

	<%@ include file="footer.jsp" %>	  
	</div>
</body>
</html>
