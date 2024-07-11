<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!-- Site Metas -->
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="author" content="" />
<link rel="shortcut icon" href="images/loo6.png" />
<title>Register</title>
<!-- bootstrap core css -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

<!--owl slider stylesheet -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />

<!-- font awesome style -->
<link href="css/font-awesome.min.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="css/style.css" rel="stylesheet" />
<!-- responsive style -->
<link href="css/responsive.css" rel="stylesheet" />

</head>

<body class="sub_page">
	<fmt:setLocale value="${sessionScope.lang}" />
	<fmt:setBundle basename="languages.lang" />
						<c:set var="user" value="${sessionScope.user}"/>

	<div class="hero_area">
		<div class="bg-box">
			<img src="images/bg.jpg" alt="">
		</div>
		<!-- header section strats -->
			<header class="header_section">
			<div class="container">
				<nav class="navbar navbar-expand-lg custom_nav-container ">
					<a class="navbar-brand" href="index"><img alt="logo"
						style="width: 120px" src="images/log5.png"> </a>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav  mx-auto ">
							<li class="nav-item "><a class="nav-link"
								href="index.jsp"><fmt:message>menu.home</fmt:message> </a></li>
							<li class="nav-item"><a class="nav-link" href="menu?type=0"><fmt:message>menu.menu</fmt:message></a>
							</li>
							<li class="nav-item"><a class="nav-link" href="about.jsp"><fmt:message>menu.about</fmt:message></a>
							</li>
							<li class="nav-item"><a class="nav-link" href="contact.jsp"><fmt:message>menu.contact</fmt:message></a>
							</li>
						</ul>
						<div class="user_option">
							<div class="language">
								<a href="?lang_local=vi_VN" class="lang">VN </a> <a
									href="?lang_local=en_US" class="lang"> EN </a>
							</div>

							<a href="user" class="user_link"> <i class="fa fa-user"
								aria-hidden="true">${user.userName}</i>

							</a>

							<c:if test="${not empty user}">
								<a href="#" onclick="doLogout()" class="user_link"><img
									width="30px" alt="" src="images/logout3.png"> </a>
									<c:if test="${user.role != 1}">
								<a href="admin" class="user_link"><img
									width="30px" alt="" src="images/admin.png"> </a>
									</c:if>
							</c:if>
							<a href="shoppingcart" class="user_link"><img width="30px"
								alt="" src="images/cart.png"> </a>

						</div>
					</div>
				</nav>
			</div>
		</header>
		<!-- end header section -->
	</div>

	<!-- book section -->
		<section class="book_section layout_padding">
	<c:set var="erro" value="${requestScope.erro}" />

	<div class="loginn">

		<form action="register" method="post">
		<center>
							<c:set var="EMAIL" value="${requestScope.EMAIL }" />
							<c:set var="PHONE" value="${requestScope.PHONE }" />
							<c:set var="PASSWORD" value="${requestScope.PASSWORD }" />
							<c:set var="USERNAME" value="${requestScope.USER_NAME }" />
		
				<h4 style="color: red"&"front-size:10px">${erro}</h4>
			<table class="login-table">
				<tr class="login-head">
					<th colspan="3"><center><h1> <fmt:message>signup</fmt:message> </h1></center></th>
				</tr>
				<tr class="login-body">
					<td> <fmt:message>username</fmt:message>* :</td>
					<td><input type="text" name="tentaikhoan" value="${USERNAME}"  minlength="6" maxlength="20" required ></td>
					<c:if test="${er}">
								<td><p style="color: red">${us} </p></td>
							</c:if>
				</tr>
				<tr class="login-body">
					<td> <fmt:message>pass</fmt:message> *:</td>
					<td><input type="password"  minlength="6" maxlength="20"
                                       data-validation="required" name="matkhau" value="${PASSWORD}" required></td>
					<c:if test="${er}">
								<td><p style="color: red">${pas} </p></td>
							</c:if>
				</tr>
				<tr class="login-body">
				<c:set var="name" value="${requestScope.name }"></c:set>
					<td> <fmt:message>fullname</fmt:message> :</td>
					<td><input type="text" name="hoten" value="${name }"  minlength="6" maxlength="20"></td>
					<c:set var="u" value="${requestScope.us }" />
					<c:set var="pas" value="${requestScope.pas }" />
					<c:set var="ph" value="${requestScope.phone }" />
					<c:set var="m" value="${requestScope.mail }" />
					
				</tr>
				<tr class="login-body">
					<td> <fmt:message>phone</fmt:message>* :</td>
					<td><input type="text" name="sodienthoai" value="${PHONE}" minlength="10" maxlength="10" required></td>
					<c:if test="${er}">
								<td><p style="color: red">${ph} </p></td>
							</c:if>
				</tr>

				
				<tr class="login-body">
					<td> <fmt:message>contact.email</fmt:message>* :</td>
					<td><input type="text" name="email"  data-validation="email"
                                       pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,63}$"
                                       data-validation-error-msg="Email sai định dạng" value="${EMAIL}" required></td>
					<c:if test="${er}">
								<td><p style="color: red">${m} </p></td>
							</c:if>
				</tr>
				<tr class="login-body">
					<td> <fmt:message>gender</fmt:message> :</td>
					<td><input type="radio" name="gioitinh" value="1" checked> <fmt:message>male</fmt:message> 
						<input type="radio" name="gioitinh" value="2"> <fmt:message>female</fmt:message> </td>
				</tr>
				<tr class="login-foot">
				<td class="foot-item"><a href="login.jsp" style="font-size: 20px"> <fmt:message>login</fmt:message> </a></td>
					<td class="foot-item"><input class="button" type="submit"
						name="submit" value=" <fmt:message>signup</fmt:message> "></td>
					
				</tr>
			</table>
			</center>
		</form>
	</div>
	</section>
	<!-- end book section -->

	<!-- footer section -->
	<footer class="footer_section">
		<div class="container">
			<div class="row">
				<div class="col-md-4 footer-col">
					<div class="footer_contact">
						<h4><fmt:message>menu.contact</fmt:message></h4>
						<div class="contact_link_box">
							<a href=""> <i class="fa fa-map-marker" aria-hidden="true"></i>
								<span><fmt:message>university</fmt:message> </span>
							</a> <a href=""> <i class="fa fa-phone" aria-hidden="true"></i> <span>
									0364811595 </span>
							</a> <a href=""> <i class="fa fa-envelope" aria-hidden="true"></i>
								<span> 21130553@st.hcmuaf.edu.vn </span>
							</a>
						</div>
					</div>
				</div>
				<div class="col-md-4 footer-col">
					<div class="footer_detail">
						<a href="" class="footer-logo"><fmt:message>home.mr</fmt:message></a>
						
						<div class="footer_social">
							<a href=""> <i class="fa fa-facebook" aria-hidden="true"></i>
							</a> <a href=""> <i class="fa fa-twitter" aria-hidden="true"></i>
							</a> <a href=""> <i class="fa fa-linkedin" aria-hidden="true"></i>
							</a> <a href=""> <i class="fa fa-instagram" aria-hidden="true"></i>
							</a> <a href=""> <i class="fa fa-pinterest" aria-hidden="true"></i>
							</a>
						</div>
					</div>
				</div>
				<div class="col-md-4 footer-col">
					<h4><fmt:message>openhour</fmt:message></h4>
					<p><fmt:message>Everyday</fmt:message></p>
					<p>8.00 Am -10.00 Pm</p>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>