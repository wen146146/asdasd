<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>推荐文章列表</title>
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
	            <li class="breadcrumb-item active text-primary" aria-current="page">好物推荐</li>
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
      
      <c:forEach var="blog" items="${blogs}">
      <div class="col-12 col-lg-4 col-md-6 mt-5">
        <!-- Blog Card -->
        <div class="card border-0 bg-transparent">
              <div class="position-relative rounded overflow-hidden">
                <div class="position-absolute z-index-1 bg-white text-pink text-center py-1 px-3 my-4">
                    ${blog.createTime}</div>
                <img class="card-img-top hover-zoom" src="${blog.pic}" alt="Image"> </div>
              <div class="card-body px-0 pb-0">
                <h4 class="h5 font-w-5 mt-2 mb-0"> 
                <a class="link-title" href="blog?method=detail&id=${blog.id}">${blog.title}</a> </h4>
              </div>
              <div></div>
            </div>
        <!-- End Blog Card -->
      </div>
      </c:forEach>
      
    </div>
    <div class="row mt-10">
      <div class="col-12  justify-content-end">
        <nav aria-label="Page navigation">
          <ul class="pagination justify-content-center">
            <li class="page-item"><a class="page-link" href="#" data-original-title="将在Mybatis课程实现" data-toggle="tooltip">前一页</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">2</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">3</a>
            </li>
            <li class="page-item"><a class="page-link" href="#" data-original-title="将在Mybatis课程实现" data-toggle="tooltip">后一页</a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>
</section>

<!--blog end-->

</div>

<!--body content end--> 


</div>

<!-- page wrapper end -->

	<%@ include file="footer.jsp" %> 
</body>
</html>
