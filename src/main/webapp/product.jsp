<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>${product.name}商品的详情页</title>
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
        <h1 class="h2 mb-0">商品详情</h1>
      </div>
      <div class="col-md-6 mt-3 mt-md-0">
        <nav aria-label="breadcrumb">
          <ol class="breadcrumb justify-content-md-end bg-transparent p-0 m-0">
            <li class="breadcrumb-item"><a class="link-title" href="index">首页</a> </li>
            <li class="breadcrumb-item"><a class="link-title" href="category?method=list&id=${product.category.id}">${product.category.name}</a></li>
            <li class="breadcrumb-item active text-primary" aria-current="page">${product.name}</li>
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

	<!--product details start-->
	
	<section>
	  <div class="container">
	    <div class="row">
	      <div class="col-lg-6 col-12">
	        <div class="product-image">
	            <div class="product_img_box">
	                <img id="product_img" src="${product.pic}" data-zoom-image="${product.pic}" alt="product_img1" />
	                <a href="#" class="product_img_zoom" title="Zoom">
	                    <span class="linearicons-zoom-in"></span>
	                </a>
	             </div>
	             <div id="pr_item_gallery" class="product_gallery_item slick_slider no-pb owl-carousel owl-2" data-dots="false" data-nav="false" data-items="5" data-md-items="2" data-sm-items="1">
	                 <c:forEach var="picture" items="${product.pictureList}">
	                     <div class="item">
	                         <a href="#" class="product_gallery_item" data-image="${picture.file}" data-zoom-image="${picture.file}">
	                             <img src="${picture.file}"/>
	                         </a>
	                     </div>
	                  </c:forEach>
	               </div>
	          </div>
	      </div>
	      <div class="col-lg-6 col-12 mt-5 mt-lg-0">
	        <div class="product-details">
	          <h1 class="h4 mb-0 font-w-6">${product.name}</h1>
	          <span class="product-price h5 text-pink">${product.price} <del class="text-muted h6">￥${product.originalPrice}</del></span>
	          <ul class="list-unstyled my-5">
	            <li><small>库存: <span class="text-green">${product.stock>0?'有货':'无货'}</span> </small></li>
	            <li class="font-w-4"><small>分类:<span class="text-muted">${product.category.name}</span></small></li>
	            <!-- <li class="font-w-4"><small>标签tags:<span class="text-muted">多个tags</span></small> </li>  -->
	          </ul>
	          <p class="mb-5 border-top border-bottom pb-5 pt-5 desc">${product.brief}</p>
	          <div class="d-sm-flex align-items-center mb-5">
	            <div class="d-flex align-items-center mr-sm-4">
	              <button class="btn-product btn-product-up"> <i class="las la-minus"></i></button>
	              <input class="form-product" type="number" name="form-product" value="1">
	              <button class="btn-product btn-product-down"> <i class="las la-plus"></i></button>
	            </div>
	          </div>
	          <div class="d-sm-flex align-items-center mt-5">
	            <a class="btn btn-primary btn-animated mr-sm-3 mb-3 mb-sm-0" href="cart?method=add&id=${product.id}"><i class="las la-shopping-cart mr-2"></i>加入购物车</a>
	          </div>
	          
	        </div>
	      </div>
	    </div>
	  </div>
	</section>
	
	<!--product details end-->


	<!--tab start-->
	
	<section class="pt-0 pb-8">
	  <div class="container">
	    <div class="row">
	      <div class="col-md-12">
	        <div class="tab product-tab">
	          <!-- Nav tabs -->
	          <nav>
	            <div class="nav nav-tabs" id="nav-tab" role="tablist"> 
	              <a class="nav-item nav-link active ml-0" id="nav-tab1" data-toggle="tab" href="#tab3-1" role="tab" aria-selected="true">商品详情</a>
	              <a class="nav-item nav-link" id="nav-tab3" data-toggle="tab" href="#tab3-3" role="tab" aria-selected="false">商品评价</a>
	            </div>
	          </nav>
	          <!-- Tab panes -->
	          <div class="tab-content pt-5 p-0">
	            <div role="tabpanel" class="tab-pane fade show active" id="tab3-1">
	              <div class="row align-items-center">
	                <div class="col-md-12">
	                  <p>${product.detail}</p>
	                </div>
	              </div>
	            </div>
	            <div role="tabpanel" class="tab-pane fade" id="tab3-3">
	              <div class="comment-area mt-5">
	                    <ul class="list_none comment_list">
	                    	<c:forEach var="evaluation" items="${evaluationList}">
	                        <li class="comment_info">
	                            <div class="d-flex">
	                                <div class="comment_user">
	                                    <img src="assets/images/thumbnail/member1.png" alt="user2">
	                                </div>
	                                <div class="comment_content">
	                                    <div class="d-flex">
	                                        <div class="meta_data">
	                                            <h6><a href="#">${evaluation.user.username}</a></h6>
	                                            <div class="comment-time">${evaluation.createTime}</div>
	                                        </div>
	                                    </div>
	                                    <p>${evaluation.content}</p>
	                                </div>
	                            </div>
	                        </li>
	                        </c:forEach>
	                    </ul>
	        		</div>
	 
	            </div>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</section>
	
	<!--tab end-->

</div>

<!--body content end--> 

    <%@ include file="footer.jsp" %>	
	</div>
</body>
</html>
