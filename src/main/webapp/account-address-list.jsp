<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>收货地址列表-个人中心</title>
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
	        <h1 class="h2 mb-0">我的账号中心</h1>
	      </div>
	      <div class="col-md-6 mt-3 mt-md-0">
	        <nav aria-label="breadcrumb">
	          <ol class="breadcrumb justify-content-md-end bg-transparent p-0 m-0">
	            <li class="breadcrumb-item"><a class="link-title" href="index">首页</a></li>
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
	                        <a class="nav-link" href="account?method=dashboard&id=${id}">
	                        主页面</a>
	                      </li>
	                      <li class="nav-item">
	                        <a class="nav-link" href="account?method=orderlist&id=${id}">
	                        我的订单</a>
	                      </li>
	                      <li class="nav-item">
	                        <a class="nav-link active" href="account?method=addresslist&id=${id}">
	                        收货地址</a>
	                      </li>
	                      <li class="nav-item">
	                        <a class="nav-link" href="account?method=viewUser&id=${id}">
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
						<div class="" id="address">
	                    	<div class="card border-0">
	                            <h5 class="font-w-6">收货地址列表</h5>
	                            <div class="card-body">
	                    			<div class="table-responsive">
	                                    <table class="table">
	                                        <thead>
	                                            <tr>
	                                                <th>姓名</th>
	                                                <th>收货详细地址</th>
	                                                <th>手机号</th>
	                                                <th>邮编</th>
	                                                <th>操作</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:forEach var="address" items="${addressList}">
	                                            <tr>
	                                                <td>${address.consigneeName}</td>
	                                                <td>${address.consigneeAddress}</td>
	                                                <td>${address.consigneePhone}</td>
	                                                <td>${address.postcode}</td>
	                                                <td>
	                                                <a href="account?method=editAddress&id=${address.id}" class="btn btn-fill-out btn-sm">修改</a>
	                                                <a href="account?method=deleteAddress&id=${address.id}" class="btn btn-fill-out btn-sm" data-original-title="不进行确认，直接删除" data-toggle="tooltip">删除</a>
	                                                </td>
	                                            </tr>
												</c:forEach>
	                                        </tbody>
	                                    </table>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="row">
	                        	<button class="btn btn-primary" data-toggle="modal" data-target="#addAddressModal">新建收货地址</button>
	                        	<!-- 新增收货地址模态框（Modal） -->
								<div class="modal fade" id="addAddressModal" tabindex="-1" role="dialog" aria-labelledby="addAddressModalLabel" aria-hidden="true">
								    <div class="modal-dialog">
								        <div class="modal-content">
										    <form action="account?method=addAddress" method="post">
										    	<input name="userId" value="${id}" type="hidden"/>
									            <div class="modal-header">
									                <h5 class="modal-title">新增收货地址</h5>
									            </div>
									            <div class="modal-body">
				                                    <div class="row">
				                                        <div class="form-group col-md-12">
				                                        	<label>收货人姓名 <span class="required">*</span></label>
				                                            <input required class="form-control" name="consigneeName" type="text">
				                                         </div>
				                                        <div class="form-group col-md-12">
				                                        	<label>收货地址 <span class="required">*</span></label>
				                                            <input required class="form-control" name="consigneeAddress" type="text">
				                                        </div>
				                                        <div class="form-group col-md-12">
				                                        	<label>收货手机号 <span class="required">*</span></label>
				                                            <input required class="form-control" name="consigneePhone" type="text">
				                                        </div>
				                                        <div class="form-group col-md-12">
				                                        	<label>收货邮编 <span class="required">*</span></label>
				                                            <input required class="form-control" name="postcode" type="text">
				                                        </div>				                                        
				                                    </div>
												</div>
									            <div class="modal-footer">
									                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									                <button type="submit" class="btn btn-primary">提交</button>
									            </div>
				                            </form>
								        </div><!-- /.modal-content -->
								    </div><!-- /.modal 结束 -->
								</div>
	                        </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<!-- "收货地址详情" 模态框（Modal） -->
	<div class="modal fade" id="addressDetailModal" tabindex="-1" role="dialog" aria-labelledby="addressDetailModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">收货地址详情</h5>
				</div>
				<div class="modal-body">
				    <div class="row">
				       <div class="form-group col-md-12">
				           <label>收货人姓名</label>
				           <input readonly class="form-control" name="consigneeName" type="text" value="${address.consigneeName}">
				       </div>
				       <div class="form-group col-md-12">
				            <label>收货地址</label>
				            <input readonly class="form-control" name="consigneeAddress" type="text"  value="${address.consigneeAddress}">
				       </div>
				       <div class="form-group col-md-12">
				            <label>收货手机号</label>
				            <input readonly class="form-control" name="consigneePhone" type="text" value="${address.consigneePhone}">
				       </div>
				       <div class="form-group col-md-12">
				            <label>收货邮编</label>
				            <input readonly class="form-control" name="postcode" type="text" value="${address.postcode}">
				       </div>				                                        
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal 结束 -->
	</div>
	
	
	</div>
	<!--body content end--> 

	<%@ include file="footer.jsp" %>	  
	</div>
</body>
</html>
