<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>购物车</title>
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
	        <h1 class="h2 mb-0">购物车</h1>
	      </div>
	      <div class="col-md-6 mt-3 mt-md-0">
	        <nav aria-label="breadcrumb">
	          <ol class="breadcrumb justify-content-md-end bg-transparent p-0 m-0">
	            <li class="breadcrumb-item"><a class="link-title" href="index">首页</a>
	            </li>
	            <li class="breadcrumb-item"><a class="link-title" href="#">购物车</a></li>
	            <li class="breadcrumb-item active text-primary" aria-current="page">我的购物车</li>
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
		      <div class="col-lg-12">
		        <div class="table-responsive">
		          <table class="cart-table table">
		            <thead>
		              <tr>
		                <th scope="col">商品</th>
		                <th scope="col">价格</th>
		                <th scope="col">数量</th>
		                <th scope="col">合计</th>
		              </tr>
		            </thead>
		            <tbody>
		            
		              <c:forEach var="cartItem" items="${cart.cartItemList}">
		              <tr>
		                <td>
		                  <div class="cart-thumb media align-items-center">
		                    <a href="product?method=detail&id=${cartItem.product.id}">
		                      <img class="img-fluid" src="${cartItem.product.pic}" alt="商品图片">
		                    </a>
		                    <div class="media-body ml-3">
		                      <div class="product-title mb-2"><a class="link-title" href="#">${cartItem.product.name}</a>
		                      </div>
		                    </div>
		                  </div>
		                </td>
		                <td> <span class="product-price text-muted">${cartItem.product.price}</span>
		                </td>
		                <td>
		                  <div class="d-flex align-items-center">
		                    <button class="btn-product btn-product-up"> <i class="las la-minus" data-original-title="未实现" data-toggle="tooltip"></i>
		                    </button>
		                    <input class="form-product" type="number" name="form-product" value="${cartItem.quantity}">
		                    <button class="btn-product btn-product-down"> <i class="las la-plus" data-original-title="未实现" data-toggle="tooltip"></i>
		                    </button>
		                  </div>
		                </td>
		                <td><span class="product-price text-dark font-w-6">${cartItem.total}</span></td>
		              </tr>
		              </c:forEach>
		              
		            </tbody>
		          </table>
		        </div>
		        
		      </div>
		      </div>
		      <div class="row">
			      <div class="col-lg-12 pl-lg-12 mt-8 mt-lg-0">
			        <div class="border rounded p-5 bg-light-4">
			          <div class="d-flex justify-content-between align-items-center pt-3 mb-5"> <span class="text-dark h5">合计</span>  <span class="text-dark font-w-6 h5">${cart.total}</span> 
			          </div> 
			          <a class="btn btn-primary btn-animated btn-block col-lg-3" href="order?method=create">生成订单</a>
			          <a class="btn btn-dark btn-animated mt-3 btn-block col-lg-3" href="category?method=all">继续购物</a>
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
