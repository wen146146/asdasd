<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>常见问题</title>
	<link rel="shortcut icon" href="assets/images/favicon.png" />
	<link href="assets/css/theme-plugin.css" rel="stylesheet" />
	<link href="assets/css/theme.min.css" rel="stylesheet" />
</head>

<body class="">

<!-- page wrapper start -->

<div class="page-wrapper"> 
  
    <%@ include file="header.jsp" %>
	<!--hero section start-->

		<section class="bg-light py-6">
		  <div class="container">
		    <div class="row align-items-center">
		      <div class="col-md-6">
		        <h1 class="h2 mb-0">常见问题</h1>
		      </div>
		      <div class="col-md-6 mt-3 mt-md-0">
		        <nav aria-label="breadcrumb">
		          <ol class="breadcrumb justify-content-md-end bg-transparent p-0 m-0">
		            <li class="breadcrumb-item"><a class="link-title" href="index.jsp">首页</a></li>
		            <li class="breadcrumb-item active text-primary" aria-current="page">常见问题</li>
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

		<section class="faq-page">
		  <div class="container">
		    <div class="row align-items-center justify-content-center">
		      <c:forEach var="faq" items="${faqs}" varStatus="status">
		      <div class="col-12 col-lg-12">
		        <div id="accordion" class="accordion">
		          <div class="card border-bottom">
		            <div class="card-header">
		              <h6 class="mb-0"> 
		              <a class="text-dark font-w-5" data-toggle="collapse" data-parent="#accordion" href="#collapse${status.index}">${faq.question}</a>
		              </h6>
		            </div>
		            <div id="collapse${status.index}" class="collapse" data-parent="#accordion">
		              <div class="card-body text-muted">${faq.answer}</div>
		            </div>
		          </div>
		        </div>
		      </div>
		      </c:forEach>
		    </div>
		  </div>
		</section>

	</div>
	<!--body content end--> 
	  <%@ include file="footer.jsp" %>
	  </div>
</body>
</html>
