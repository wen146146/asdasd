<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>订单完成</title>
	<link rel="shortcut icon" href="assets/images/favicon.png" />
	<link href="assets/css/theme-plugin.css" rel="stylesheet" />
	<link href="assets/css/theme.min.css" rel="stylesheet" />
</head>

<body class="bg-light-4">

<!-- page wrapper start -->

<div class="page-wrapper"> 
  
  <%@ include file="header.jsp" %>

<!--hero section start-->

<section class="bg-light py-6">
  <div class="container">
    <div class="row align-items-center">
      <div class="col-md-6">
        <h1 class="h2 mb-0">订单完成</h1>
      </div>
      <div class="col-md-6 mt-3 mt-md-0">
        <nav aria-label="breadcrumb">
          <ol class="breadcrumb justify-content-md-end bg-transparent p-0 m-0">
            <li class="breadcrumb-item"><a class="link-title" href="index.jsp">首页</a>
            </li>
            <li class="breadcrumb-item"><a class="link-title" href="account">我的订单</a></li>
            <li class="breadcrumb-item active text-primary" aria-current="page">订单完成页</li>
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

<section class="text-center pb-11">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h4 class="mb-4 font-w-6">您的订单已完成，感谢购买！</h4>
        <a class="btn btn-primary btn-animated" href="index">返回首页</a>
        <a class="btn btn-dark btn-animated" href="category?method=all">继续购物</a>      
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
