<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>订单</title>
	<link rel="shortcut icon" href="assets/images/favicon.png" />
	<link href="assets/css/theme-plugin.css" rel="stylesheet" />
	<link href="assets/css/theme.min.css" rel="stylesheet" />
</head>

<body class="">

	<!-- page wrapper start -->

	<div class="page-wrapper">
		<%@ include file="header.jsp"%>

		<!--hero section start-->
		<section class="bg-light py-6">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-md-6">
						<h1 class="h2 mb-0">生成订单</h1>
					</div>
					<div class="col-md-6 mt-3 mt-md-0">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb justify-content-md-end bg-transparent p-0 m-0">
								<li class="breadcrumb-item"><a class="link-title" href="index.jsp">首页</a></li>
								<li class="breadcrumb-item active text-primary" aria-current="page">生成订单</li>
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

			<section class="checkout-page">
				<div class="container">
					<div class="row">
						<div class="col-lg-12 col-md-12 mt-5 mt-lg-0">
						<form class="row" action="order?method=complete" method="post">
						    <input type="hidden" name="userId" value="${userId}"/>
							<div class="border bg-light-4 p-3 col-lg-11">
								<div class="mb-7">
									<h6 class="mb-3 font-w-6">商品列表</h6>
									<ul class="list-unstyled">
									    <c:forEach var="cartItem" items="${cart.cartItemList}">
										<li class="mb-3 border-bottom pb-3 d-flex">
											<span class="mr-auto"> ${cartItem.product.name}</span> <span>x ${cartItem.quantity} </span> <span>${cartItem.total}</span>
										</li>
										</c:forEach>
										<li class="mb-3 border-bottom pb-3 d-flex"><span
											class="mr-auto"> 配送费(未实现) </span> <span>0.00</span></li>
										<li class="d-flex"><span class="mr-auto"><strong
												class="cart-total"> 总计 :</strong></span> <strong
											class="cart-total">${cart.total} </strong></li>
									</ul>
								</div>
								<div class="mb-7">
									<h6 class="mb-3 font-w-6">收货地址</h6>
									<div class="col-md-12">
										<div class="form-group col-md-6">
											<!-- <input type="text" id="address" name="address"
												class="form-control" placeholder="输入你的收货地址"> -->
											<select class="selectpicker form-control" name="addressId">
												<c:forEach var="address" items="${addressList}">
											    <option value="${address.id}">${address.consigneeAddress}</option>
											    </c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="cart-detail my-5">
									<h6 class="mb-3 font-w-6">支付(未实现)</h6>
									<div class="form-group">
										<div class="custom-control custom-radio">
											<input type="radio" id="customRadio1" name="pay"
												class="custom-control-input"> 
											<label
												class="custom-control-label" for="customRadio1">云闪付</label>
										</div>
									</div>
									<div class="form-group">
										<div class="custom-control custom-radio">
											<input type="radio" id="customRadio2" name="pay"
												class="custom-control-input"> 
											<label class="custom-control-label" for="customRadio2">微信支付</label>
										</div>
									</div>
									<div class="form-group">
										<div class="custom-control custom-radio">
											<input type="radio" id="customRadio3" name="pay"
												class="custom-control-input"> 
											<label class="custom-control-label" for="customRadio3">支付宝</label>
										</div>
									</div>
								</div>
								<button class="btn btn-primary btn-animated btn-block col-lg-3">生成并完成订单</button>
							</div>
						</form>
						</div>
					</div>
				</div>
			</section>

		</div>

		<!--body content end-->

		<%@ include file="footer.jsp"%>
		</div>
</body>
</html>
