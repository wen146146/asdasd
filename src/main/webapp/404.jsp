<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>找不到网页404</title>
	<link rel="shortcut icon" href="assets/images/favicon.png" />
	<link href="assets/css/theme-plugin.css" rel="stylesheet" />
	<link href="assets/css/theme.min.css" rel="stylesheet" />
</head>

<body class="bg-light-4">

	<div class="page-wrapper"> 
		<%@ include file="header.jsp" %>
	
		<div class="page-content">
			<section class="bg-light">
			  <div class="container">
			    <div class="row justify-content-center text-center">
			      <div class="col-10 col-lg-6 col-md-10 col-sm-10 error-page">
			        <h2>404</h2>
			        <h4>抱歉, 您请求的网页不存在。请检查地址是否正确。 </h4>
			      </div>
			    </div>
			  </div>
			</section>
		</div>
	
	  	<%@ include file="footer.jsp" %>
	</div>
</body>
</html>
