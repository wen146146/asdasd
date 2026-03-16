<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!--footer start-->
  
	<footer class="py-7 bg-dark-grey">
	  <div class="container">
	    <div class="row">
	      <div class="col-12 col-lg-4"> <a class="footer-logo text-white h2 mb-0" href="index.html">
	              <img class="img-fluid" src="assets/images/logo3.png" alt="">
	            </a>
	        <p class="my-3 text-muted pr-6">本网站是国内知名的购物网站，口碑良好，质美价廉.</p>
	        <ul class="list-inline mb-0">
	          <li class="list-inline-item"><a class="text-muted ic-2x" href="#"><i class="la la-wechat"></i></a>
	          </li>
	          <li class="list-inline-item"><a class="text-muted ic-2x" href="#"><i class="la la-weibo"></i></a>
	          </li>
	          <li class="list-inline-item"><a class="text-muted ic-2x" href="#"><i class="la la-qq"></i></a>
	          </li>
	          <li class="list-inline-item"><a class="text-muted ic-2x" href="#"><i class="la la-twitter"></i></a>
	          </li>
	          <li class="list-inline-item"><a class="text-muted ic-2x" href="#"><i class="la la-github"></i></a>
	          </li>
	        </ul>
	      </div>
	      <div class="col-12 col-lg-8 mt-6 mt-lg-0">
	        <div class="row">
	          <div class="col-12 col-sm-4 navbar-white">
	            <h6 class="mb-4 text-white font-w-5 txt-transform">快速链接</h6>
	            <ul class="navbar-nav list-unstyled mb-0">
	              <li class="mb-3 nav-item"><a class="nav-link text-muted font-w-4" href="#">物流快递</a>
	              </li>
	              <li class="mb-3 nav-item"><a class="nav-link text-muted font-w-4" href="#">关于我们</a>
	              </li>
	              <li class="mb-3 nav-item"><a class="nav-link text-muted font-w-4" href="#">上门自提</a>
	              </li>
	              <li class="mb-3 nav-item"><a class="nav-link text-muted font-w-4" href="#">海外配送</a>
	              </li>
	            </ul>
	          </div>
	          
	          <div class="col-12 col-sm-4 mt-6 mt-sm-0 navbar-white">
	            <h6 class="mb-4 text-white font-w-5 txt-transform">支付方式</h6>
	            <ul class="navbar-nav list-unstyled mb-0">
	              <li class="mb-3 nav-item"><a class="nav-link text-muted font-w-4" href="#">货到付款</a>
	              </li>
	              <li class="mb-3 nav-item"><a class="nav-link text-muted font-w-4" href="#">在线支付</a>
	              </li>
	              <li class="mb-3 nav-item"><a class="nav-link text-muted font-w-4" href="#">分期支付</a>
	              </li>
	              <li class="mb-3 nav-item"><a class="nav-link text-muted font-w-4" href="#">京东支付</a>
	              </li>
	            </ul>
	          </div>
	          <div class="col-12 col-sm-4 mt-6 mt-sm-0 navbar-white">
	            <h6 class="mb-4 text-white font-w-5 txt-transform">购物指南</h6>
	            <ul class="navbar-nav list-unstyled mb-0">
	              <li class="mb-3 nav-item"><a class="nav-link text-muted font-w-4" href="faq">常见问题</a>
	              </li>
	              <li class="mb-3 nav-item"><a class="nav-link text-muted font-w-4" href="#">购物流程</a>
	              </li>
	              <li class="mb-3 nav-item"><a class="nav-link text-muted font-w-4" href="#">会员权限</a>
	              </li>
	              <li class="mb-3 nav-item"><a class="nav-link text-muted font-w-4" href="#">联系客服</a>
	              </li>
	            </ul>
	          </div>
	        </div>
	      </div>
	      
	    </div>
	    <div class="row pt-9">
	        <div class="col-lg-4 col-md-6 col-sm-12">
	          <div class="d-flex footer-box">
	            <div class="mr-4"> <i class="las la-map-marker-alt ic-2x icon-img"></i> </div>
	            <div>
	              <h6 class="mb-1 text-white font-w-5">注册地址</h6>
	              <p class="mb-0 font-w-4 text-muted">${initParam.registerAddress}</p>
	            </div>
	          </div>
	        </div>
	        <div class="col-lg-4 col-md-6 col-sm-12">
	          <div class="d-flex footer-box">
	            <div class="mr-4"> <i class="las la-envelope ic-2x icon-img"></i> </div>
	            <div>
	              <h6 class="mb-1 text-white font-w-5">邮箱</h6>
	              <a class="text-muted font-w-4" href="mailto:${initParam.email}">${initParam.email}</a> </div>
	          </div>
	        </div>
	        <div class="col-lg-4 col-md-12 col-sm-12 mt-md-3 mt-lg-0">
	          <div class="d-flex footer-box">
	            <div class="mr-4"> <i class="las la-mobile ic-2x icon-img"></i> </div>
	            <div>
	              <h6 class="mb-1 text-white font-w-5">服务电话</h6>
	              <p class="mb-0 font-w-4 text-muted">${initParam.phone}</p>
	            </div>
	          </div>
	        </div>
	    </div>
	    <hr class="my-6 light-border">
	    <div class="row text-muted align-items-center">
	      <div class="col-md-7 font-w-4"><small>版权所有 &copy; 2021.${initParam.webName} 保留所有权利</small>
	      </div>
	      <div class="col-md-5 text-md-right mt-3 mt-md-0">
	        <ul class="list-inline mb-0">
	          <li class="list-inline-item"><a class="text-muted ic-2x" href="#"><i class="la la-wechat"></i></a>
	          </li>
	          <li class="list-inline-item"><a class="text-muted ic-2x" href="#"><i class="la la-weibo"></i></a>
	          </li>
	          <li class="list-inline-item"><a class="text-muted ic-2x" href="#"><i class="la la-qq"></i></a>
	          </li>
	          <li class="list-inline-item"><a class="text-muted ic-2x" href="#"><i class="la la-twitter"></i></a>
	          </li>
	          <li class="list-inline-item"><a class="text-muted ic-2x" href="#"><i class="la la-github"></i></a>
	          </li>
	        </ul>
	      </div>
	    </div>
	  </div>
	</footer>

	<!-- page wrapper end -->


	<!--back-to-top start-->
	<div class="scroll-top"><a class="smoothscroll" href="#top"><i class="las la-angle-up"></i></a></div>
	<!--back-to-top end-->

	<!-- inject js start -->
	<script src="assets/js/jquery-3.5.1.min.js"></script>
	<script src="assets/js/popper.min.js"></script> 
	<script src="assets/js/bootstrap.min.js"></script> 
	<script src="assets/js/owl.carousel.min.js"></script> 
	<script src="assets/js/light-slider.js"></script> 
	<script src="assets/js/parallax.js"></script> 
	<script src="assets/js/magnific-popup.min.js"></script> 
	<script src="assets/js/jquery.countdown.min.js"></script> 
	<script src="assets/js/jquery.dd.min.js"></script> 
	<script src="assets/js/validator.js"></script> 
	<script src="assets/js/wow.js"></script> 
	<script src="assets/js/jquery.elevatezoom.js"></script> 
	<script src="assets/js/theme-script.js"></script> 
	<!-- inject js end -->
  <!--footer end--> 