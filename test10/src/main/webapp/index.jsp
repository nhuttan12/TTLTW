<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<meta charset="UTF-8">
<!-- bootstrap core css -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

<!--owl slider stylesheet -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />
<!-- nice select  -->
<link href="css/font-awesome.min.css" rel="styleSheet" />
<link href="css/style.css" rel="styleSheet" />
<link href="css/responsive.css" rel="styleSheet" />
<title>Home Page</title>
<link rel="shortcut icon" href="images/loo6.png" />
<script type="text/javascript">
	function doLogout() {
		if (confirm("Are you Logout?")) {
			window.location = "logout";
		}

	}
</script>
</head>
<body>
	<fmt:setLocale value="${sessionScope.lang}" />
	<fmt:setBundle basename="languages.lang" />
	<c:set var="user" value="${sessionScope.user}" />

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
							<li class="nav-item active"><a class="nav-link"
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

		<!-- slider section -->
		<section class="slider_section ">
			<div id="customCarousel1" class="carousel slide" data-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<div class="container ">
							<div class="row">
								<div class="col-md-7 col-lg-6 ">
									<div class="detail-box">
										<h1>
											<fmt:message>hello</fmt:message>
										</h1>
										<h2>
											<fmt:message>slogan</fmt:message>
										</h2>
										<div class="btn-box">
											<a href="menu.jsp" class="btn1"><fmt:message>menu.oder</fmt:message></a>
										</div>
									</div>
								</div>
								<div class="col-md-6 ">
									<div class="img-box">
										<img src="images/home.png" alt="">
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
		</section>
		<!-- end slider section -->
	</div>

	<!-- offer section -->

	<section class="offer_section layout_padding-bottom">
		<div class="offer_container">
			<div class="container ">
				<div class="row">
					<div class="col-md-6  ">
						<div class="box ">
							<div class="img-box">
								<img src="images/chickenjoy/2m.png" alt="">
							</div>
							<div class="detail-box">
								<h5>
									<fmt:message>chickenjoy</fmt:message>
								</h5>
								<br> <a href="menu"> <fmt:message>menu.oder</fmt:message></a>
							</div>
						</div>
					</div>
					<div class="col-md-6  ">
						<div class="box ">
							<div class="img-box">
								<img src="images/burger/bo.png" alt="">
							</div>
							<div class="detail-box">
								<h5>
									<fmt:message>burger</fmt:message>
								</h5>
								<br> <a href="menu"> <fmt:message>menu.oder</fmt:message>
								</a>
							</div>
						</div>
					</div>
					<div class="col-md-6  ">
						<div class="box ">
							<div class="img-box">
								<img src="images/spicynoodle/bo.png" alt="">
							</div>
							<div class="detail-box">
								<h5>
									<fmt:message>spicynoodles</fmt:message>
								</h5>
								<br> <a href="menu"> <fmt:message>menu.oder</fmt:message>
								</a>
							</div>
						</div>
					</div>
					<div class="col-md-6  ">
						<div class="box ">
							<div class="img-box">
								<img src="images/drink/7up.png" alt="">
							</div>
							<div class="detail-box">
								<h5>
									<fmt:message>drinks</fmt:message>
								</h5>
								<br> <a href="menu"> <fmt:message>menu.oder</fmt:message></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<!-- end offer section -->

	<!-- about section -->

	<section class="about_section layout_padding">
		<div class="container  ">

			<div class="row">
				<div class="col-md-6 ">
					<div class="img-box">
						<img src="images/logo3.png" alt="">
					</div>
				</div>
				<div class="col-md-6">
					<div class="detail-box">
						<div class="heading_container">
							<h2>
								<fmt:message>home.mr</fmt:message>
							</h2>
						</div>
						<p>
							<fmt:message>home.content</fmt:message>
						</p>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- end about section -->

	<!-- contact section -->
	<section class="book_section layout_padding">
		<div class="container">
			<div class="heading_container">
				<h2>
					<fmt:message>menu.contact</fmt:message>
				</h2>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form_container">
						<form action="mailcontact">

							<div>
								<input type="text" name="name" class="form-control"
									placeholder="<fmt:message>contact.name</fmt:message>" />
							</div>

							<div>
								<input type="email" name="email" class="form-control"
									placeholder="<fmt:message>contact.email</fmt:message>" />
							</div>

							<div>
								<input type="text" name="message" class="form-control"
									placeholder="<fmt:message>contact.message</fmt:message>" />
							</div>
							<div class="btn_box">
								<button type="submit">
									<fmt:message>send</fmt:message>
								</button>
							</div>
						</form>
					</div>
				</div>

			</div>
		</div>
	</section>
	<!-- end book section -->
	<footer class="footer_section">
		<div class="container">
			<div class="row">
				<div class="col-md-4 footer-col">
					<div class="footer_contact">
						<h4>
							<fmt:message>menu.contact</fmt:message>
						</h4>
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
					<h4>
						<fmt:message>openhour</fmt:message>
					</h4>
					<p>
						<fmt:message>Everyday</fmt:message>
					</p>
					<p>8.00 Am -10.00 Pm</p>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>