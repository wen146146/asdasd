<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>首页</title>
	<link rel="shortcut icon" href="assets/images/favicon.png" />
	<link href="assets/css/theme-plugin.css" rel="stylesheet" />
	<link href="assets/css/theme.min.css" rel="stylesheet" />
	<link href="assets/css/eshop.css" rel="stylesheet" />
</head>

<body class="bg-light-4">

<!-- page wrapper start -->

<div class="page-wrapper"> 
  
  <%@ include file="header.jsp" %>	
  
  <!--分类与滚屏广告section start-->
  <section class="banner pos-r p-0 mt-5">
    <div class="container">
      <div class="row">
        <div class="col-lg-3">
          <div class="categories_wrap">
            <div class="head"><i class="las la-bars"></i> 全部分类</div>
            <div id="navCatContent" class="nav_cat navbar">
              <ul>
              	<c:forEach var="topCategory" items="${topCategoryList}" varStatus="status">
                <li class="dropdown dropdown-mega-menu"> 
                	<a class="dropdown-item nav-link dropdown-toggler" href="#" data-toggle="dropdown">
                		<i class="${topCategory.icon}"></i> <span>${topCategory.name}</span></a>
                  <div class="dropdown-menu">
                    <ul class="mega-menu d-lg-flex">
                      <li class="mega-menu-col col-lg-5">
                        <ul class="d-lg-flex mt-0">
                          <li class="mega-menu-col col-lg-12">
                            <ul>
                                <c:forEach var="childCategory" items="${topCategory.children}">
                                <li> <a href="category?method=list&id=${childCategory.id}">${childCategory.name}</a></li>
                                </c:forEach>
                            </ul>
                          </li>
                        </ul>
                      </li>
                      <li class="mega-menu-col col-lg-7">
                        <div class="header-banner2"> <img src="assets/images/electronic/product-ad/04.jpg" alt="menu_banner">
                          <div class="banne_info">
                            <h6>优惠20%</h6>
                            <h4>笔记本电话</h4>
                            <a href="product?method=detail&id=3">查看商品详情</a> </div>
                        </div>
                      </li>
                    </ul>
                  </div>
                </li>
                </c:forEach>
              </ul>
            </div>
          </div>
        </div>
        <div class="col-lg-9">
          <div class="banner-slider banner-3 owl-carousel no-pb h-100" data-dots="true" data-margin="5">
            <div class="item" data-bg-img="assets/images/electronic/bg/01.jpg">
              <div class="container h-100">
                <div class="row h-100 align-items-center">
                  <div class="col py-8 pl-0">
                    <div class="animated3"> <span class="dark-yellow d-inline-block text-white mb-0">首页滚屏广告位1</span> <br>
                      <h5 class="dark-red d-inline-block text-white font-w-6">全系列优惠30%</h5>
                    </div>
                    <h1 class="mt-4 animated3"><span>智能电视</span><br>
                      小米智能屏</h1>
                  </div>
                </div>
              </div>
            </div>
            <div class="item" data-bg-img="assets/images/electronic/bg/02.jpg">
              <div class="container h-100">
                <div class="row h-100 align-items-center">
                  <div class="col py-8 pl-0">
                    <div class="animated3"> <span class="dark-yellow d-inline-block text-white mb-0">首页滚屏广告位2</span> <br>
                      <h5 class="dark-red d-inline-block text-white font-w-6">全系列六折起</h5>
                    </div>
                    <h1 class="mt-4 animated3"><span>精准计时，首选</span><br>
                      欧米茄</h1>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  
  <!--body content start-->
  
  <div class="page-content"> 
    
    <!-- 热销商品 start-->
    <section class="pb-0 pt-5">
      <div class="container">
        <div class="product-block product-block-div">
          <div class="row justify-content-left text-left row_title">
            <h2 class="mb-0 font-w-5 bg-dark-blue">热销商品</h2>
          </div>
          <div class="row">
               <div class="owl-carousel no-pb owl-2" data-dots="false" data-nav="true" data-items="5" data-md-items="2" data-sm-items="1">
               <c:forEach var="hotProduct" items="${hotProductList}">
               <div class="item">
                  <div class="card product-card card--default">
                    <a class="card-img-hover d-block" href="product?method=detail&id=${hotProduct.id}">
            			<img class="card-img-top" src="${hotProduct.pic}" alt="商品图片">
                    </a>
                    <div class="card-info">
                      <div class="card-body">
                        <div class="product-title font-w-4"><a class="link-title" href="product?method=detail&id=${hotProduct.id}">${hotProduct.name}</a>
                        </div>
                        <div class="mt-1"> <span class="product-price text-dark"><del class="text-muted">${hotProduct.originalPrice}</del> ￥${hotProduct.price}</span>
                        </div>
                      </div>
                      <div class="card-footer bg-transparent border-0">
                        <div class="product-link d-flex align-items-center justify-content-center">
                        <a class="btn-cart btn btn-yellow-dark mx-3" type="button" href="cart?method=add&id=${hotProduct.id}">
                        	<i class="las la-shopping-cart mr-1"></i> 加入购物车
                        </a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                </c:forEach>
              </div>
            </div>
        </div>
      </div>
    </section>
    
    <!-- 新品 start-->
    <section class="pb-0 pt-5">
      <div class="container">
        <div class="product-block product-block-div pb-1">
          <div class="row justify-content-left text-left row_title">
            <h2 class="mb-0 font-w-5 bg-yellow text-dark">上市新品</h2>
          </div>
              <div class="row pl-3">
              <div class="owl-carousel no-pb owl-2" data-dots="false" data-nav="true" data-items="3" data-md-items="2" data-sm-items="1">
	               <c:forEach var="latestProduct" items="${latestProductList}">
	               <div class="item">
	                  <div class="col-lg-12 col-md-12 pt-5">
		                  <div class="media align-items-center mb-4"> 
		                  	<a class="d-block mr-3" href="product?method=detail&id=${latestProduct.id}"> 
		                  		<img class="border" src="${latestProduct.pic}" alt="商品图片" width="100"> </a>
		                    <div class="media-body">
		                        <div class="product-title">
		                      		<a class="link-title" href="product?method=detail&id=${latestProduct.id}">${latestProduct.name}</a> 
		                        </div>
		                        <span class="product-price"><del class="text-muted">￥${latestProduct.originalPrice}</del> ￥${latestProduct.price}</span> 
		                    </div>
		                  </div>
	                   </div>
	                </div>
	                </c:forEach>
                </div>
              </div>
            </div>
          </div>
    </section>
    
    <!-- 最新推荐文章 start-->
	  <section class="pb-5 pt-5">
	  <div class="container">
	      <div class="product-block product-block-div">
	    <div class="row justify-content-left text-left row_title">
	        <h2 class="mb-0 font-w-6 bg-redish">优品推荐文章</h2>
	    </div>
	    <!-- / .row -->
	    <div class="row m-0">
	      <c:forEach var="blog" items="${blogList}" begin="0" end="3">
	      <div class="col-12 col-lg-4 mt-5">
	        <!-- Blog Card -->
	        <div class="card border-0 bg-transparent">
	          <div class="position-relative overflow-hidden">
	            <div class="position-absolute z-index-1 bottom-0 bg-white text-primary shadow-primary text-center py-1 px-2 rounded ml-3 mb-3">${blog.createTime}</div>
	            <img class="card-img-top hover-zoom" src="${blog.pic}" alt="文章图片">
	          </div>
	          <div class="card-body px-0 pb-0">
	            <h2 class="h5 font-w-5 mt-2 mb-0">
	                <a class="link-title" href="blog?method=detail&id=${blog.id}">${blog.title}</a>
	              </h2>
	          </div>
	          <div></div>
	        </div>
	        <!-- End Blog Card -->
	      </div>
	      </c:forEach>
	    </div>
	  </div>  
	  </div>
	</section>
    <!-- end-->
    
    <!-- 在线人数统计 -->
	<section  class="pb-5 pt-5 w3lcounts">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-6 product-block product-block-div counter_grid">
					<div class="count-box text-center">
						<i class="fas fa-users"></i>
						<p>&nbsp;</p>
						<h2>${allRequest}</h2>
						<p>网站访问总次数</p>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 mt-5 mt-md-0 product-block product-block-div counter_grid">
					<div class="count-box text-center">
						<i class="fas fa-images"></i>
						<p>&nbsp;</p>
						<h2>${indexRequest}</h2>
						<p>首页访问总次数</p>
					</div>
				</div>
	
				<div class="col-lg-3 col-md-6 mt-5 mt-lg-0 product-block  product-block-div counter_grid">
					<div class="count-box text-center">
						<i class="fas fa-headset"></i>
						<p>&nbsp;</p>
						<h2>${onlineUser}</h2>
						<p>目前在线用户数</p>
					</div>
				</div>
	
				<div class="col-lg-3 col-md-6 mt-5 mt-lg-0 product-block product-block-div counter_grid">
					<div class="count-box text-center">
						<i class="fas fa-user-tie"></i>
						<p>&nbsp;</p>						
						<h2>${allUser}</h2>
						<p>注册用户数</p>
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
