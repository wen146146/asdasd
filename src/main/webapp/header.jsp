<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
      
  <div id="ht-preloader">
    <div class="loader clear-loader"> <img class="img-fluid" src="assets/images/loader.gif" alt=""> </div>
  </div>
  
  <!--header start-->
  <header class="site-header header-5">
    <div class="header-top bg-blue pt-1">
      <div class="container">
        <div class="col-md-12">
          <div class="row justify-content-end">
            <div class="language-selection font-w-3">
              <div class="d-flex align-items-center justify-content-center justify-content-md-end">
                
                <div class="lng_dropdown">
                  <select name="countries" class="custome_select">
                    <option value='cn' data-image="assets/images/cn.png" data-title="France">中文</option>
                    <option value='en' data-image="assets/images/eng.png" data-title="English">英文</option>
                  </select>
                </div>
              </div>
            </div>
            <ul class="header_list">
              <c:choose>
				   <c:when test="${username == null}">
				      <li><a href="user?method=login"><i class="icon fa fa-user"></i><span>登录</span></a></li>
				      <li><a href="signup.jsp"><i class="icon fa fa-user-plus"></i><span>注册</span></a></li>
				   </c:when>
				   <c:otherwise>
				   		<li><a href="account"><i class="icon fa fa-user"></i><span>${username}</span></a></li>
              			<li><a href="cart?method=view&id=${id}"><i class="icon fa fa-shopping-cart"></i><span>购物车</span></a></li>
				        <li><a href="user?method=logout"><i class="icon fa fa-power-off"></i><span>退出</span></a></li>
				   </c:otherwise>
		        </c:choose>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="py-md-4 py-4">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-md-12 col-lg-3 d-none d-md-flex align-items-center"> <a class="navbar-brand logo d-none d-lg-block" href="index.html"> <img class="img-fluid" src="assets/images/logo3.png" alt=""> </a> </div>
          <div class="col-md-12 col-lg-9 col-sm-12 col-12">
            <div class="right-nav align-items-center d-flex justify-content-end">
              <form class="form-inline w-100">
                <select class="custom-select rounded-left form-control d-none d-lg-inline d-md-inline">
                  <option selected value="0">所有分类</option>
                  <c:forEach var="category" items="${topCategoryList}">
                  <option value="${category.id}">${category.name}</option>
                  </c:forEach>
                </select>
                <input class="form-control border-0 rounded-0 border-left col" type="search" placeholder="输入搜索词(将在实训课中实现)" aria-label="Search">
                <button class="btn btn-yellow text-white col-auto rounded-right" type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="header-wrap" class="shadow-sm">
      <div class="container">
        <div class="row m-0"> 
          <!--menu start-->
          <div class="col p-0">
            <nav class="navbar navbar-expand-lg navbar-light position-static"> <a class="navbar-brand logo d-lg-none" href="index.html"> <img class="img-fluid" src="assets/images/logo3.png" alt=""> </a>
              <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
              <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                  <li class="nav-item"> <a class="nav-link active" href="index">首页</a></li>
                  <li class="nav-item"> <a class="nav-link" href="category?method=all">分类商品</a></li>
                  <li class="nav-item"> <a class="nav-link" href="miaosha.jsp">秒杀</a></li>
                  <li class="nav-item"> <a class="nav-link" href="blog">好物推荐</a></li>
                  <li class="nav-item"> <a class="nav-link" href="faq">常见问题</a></li>
                  <li class="nav-item"> <a class="nav-link" href="#"> </a></li>
                </ul>
              </div>
            </nav>
          </div>
          <!--menu end--> 
        </div>
      </div>
    </div>
  </header>  
  <!--header end--> 