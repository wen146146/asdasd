<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>商品分类页</title>
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
        <h1 class="h2 mb-0">${category.name}</h1>
      </div>
      <div class="col-md-6 mt-3 mt-md-0">
        <nav aria-label="breadcrumb">
          <ol class="breadcrumb justify-content-md-end bg-transparent p-0 m-0">
            <li class="breadcrumb-item"><a class="link-title" href="index.jsp">首页</a>
            </li>
            <li class="breadcrumb-item"><a class="link-title" href="category?method=all">全部分类商品</a></li>
            <li class="breadcrumb-item active text-primary" aria-current="page">${category.name}</li>
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

<section>
  <div class="container">
    <div class="row">
    
      <div class="col-lg-9 col-md-12 order-lg-1">
       
        <div class="row">
        
          <c:forEach var="product" items="${productList}">
          <div class="col-lg-4 col-md-6 mb-5">
            <div class="card product-card card--default rounded-0">
                        <div class="sale-label">${product.category.name}</div>
                        <a class="card-img-hover d-block" href="product?method=detail&id=${product.id}"> <img class="card-img-back" src="${product.pic}" alt="..."> <img class="card-img-front" src="${product.pic2}" alt="..."> </a>
                        <div class="card-info">
                          <div class="card-body">
                            <div class="product-title font-w-5"><a class="link-title" href="product?method=detail&id=${product.id}">${product.name}</a> </div>
                            <div class="mt-1"> <span class="product-price text-pink"><del class="text-muted">￥${product.originalPrice}</del> ${product.price}</span>
                            </div>
                          </div>
                          <div class="card-footer bg-transparent border-0">
                            <div class="product-link d-flex align-items-center justify-content-center">
                              <a class="btn-cart btn btn-pink mx-3" type="button" href="cart?method=add&id=${product.id}">
                              	<i class="las la-shopping-cart mr-1"></i> 加入购物车 </a>
                            </div>
                          </div>
                        </div>
                      </div>
          	</div>
          </c:forEach>

        </div>
        <nav aria-label="Page navigation">
          <ul class="pagination justify-content-end">
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
      
      <div class="col-lg-3 col-md-12 sidebar mt-8 mt-lg-0">
        <div>
          <div class="widget widget-categories mb-4 border rounded p-4">
            <h5 class="widget-title mb-3">分类</h5>
            <div id="accordion" class="accordion">
             
              <c:forEach var="topCategory" items="${topCategoryList}" varStatus="status">
              <div class="card border-0 mb-2">
                <div class="card-header">
                  <h6 class="mb-0">
	              	<a class="link-title" href="#collapse${status.index}"  data-toggle="collapse" data-parent="#accordion" aria-expanded="false"><i class="${topCategory.icon}"></i>&nbsp;${topCategory.name}</a>
	              </h6>
                </div>
                <div id="collapse${status.index}" class="collapse" data-parent="#accordion">
                  <div class="card-body text-muted">
                    <ul class="list-unstyled">
                		<c:forEach var="childCategory" items="${topCategory.children}">
                        <li> <a href="category?method=list&id=${childCategory.id}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${childCategory.name}</a></li>
                        </c:forEach>
                    </ul>
                  </div>
                </div>
              </div>
              </c:forEach>

            </div>
          </div>
          
          <div class="widget widget-categories mb-4 border rounded p-4">
            <h6 class="widget-title mb-3">多条件搜索商品<br>[在Mybatis课程实现]</h6>
            <form action="category?method=search" method="post" class="row">
	              <div class="form-group col-md-12">
	                <label>商品名称</label>
		              <input id="form_name" type="text" name="name" class="form-control">
		          </div>
		          <div class="form-group col-md-12">
	                <label>商品简介</label>
		              <input id="form_name" type="text" name="brief" class="form-control">
		          </div>
		          <div class="form-group col-md-12">
	                <label>商品详情</label>
		              <input id="form_name" type="text" name="detail" class="form-control">
		          </div>
		          <div class="form-group col-md-12">
	                <input type="submit" class="btn btn-primary btn-animated" value="搜索" />
		          </div>
              </form>
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
