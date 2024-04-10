<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!-- Site Metas -->
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="author" content="" />
<link rel="shortcut icon" href="images/logo2.png" />
<!-- bootstrap core css -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/cart.css">
<!--owl slider stylesheet -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />

<!-- font awesome style -->
<link href="css/font-awesome.min.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="css/style.css" rel="stylesheet" />
<!-- responsive style -->
<link href="css/responsive.css" rel="stylesheet" />
<script type="text/javascript">
function doLogout() {
	if (confirm("Are you Logout?")) {
		window.location = "logout";
	}
	
}
</script>
</head>

<body class="sub_page">
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
					<a class="navbar-brand" href="index.jsp"><img alt="logo"
						style="width: 120px" src="images/logo.png"> </a>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav  mx-auto ">
							<li class="nav-item "><a class="nav-link" href="index.jsp"><fmt:message>menu.home</fmt:message>
							</a></li>
							<li class="nav-item"><a class="nav-link" href="menu"><fmt:message>menu.menu</fmt:message></a>
							</li>
							<li class="nav-item"><a class="nav-link" href="about.jsp"><fmt:message>menu.about</fmt:message></a>
							</li>
							<li class="nav-item"><a class="nav-link"
								href="contact.jsp"><fmt:message>menu.contact</fmt:message></a></li>
						</ul>
						<div class="user_option">
							<div class="language">
								<a href="?lang_local=vi_VN" class="lang">VN </a> <a
									href="?lang_local=en_US" class="lang"> EN </a>
							</div>

							<a href="user?role=${user.role}" class="user_link"> <i
								class="fa fa-user" aria-hidden="true">${user.userName}</i>

							</a>

							<c:if test="${not empty user}">
								<a href="#" onclick="doLogout()" class="user_link"><img width="30px" alt=""
									src="images/logout3.png"> </a>
							</c:if>
							<a href="cart?shoppingCartId=${user.shoppingCartId}"
								class="user_link"><img width="30px" alt=""
								src="images/cart.png"> </a>

						</div>
					</div>
				</nav>
			</div>
		</header>
		<!-- end header section -->
	</div>

	<!-- book section -->
	<section class="book_section layout_padding">
		<div class="container">

			<h1>
				<fmt:message>SHOPPINGCART</fmt:message>
			</h1>

			<div class="cart">
				<c:set var="list" value="${ requestScope.listAll}" />
				<div class="products">

					<c:forEach var="o" items="${list}">

						<div class="product">

							<img src="${o.imageName}">

							<div class="product-info">

								<h3 class="product-name">
									<fmt:message>nameItem</fmt:message>
									: ${o.name}
								</h3>

								<h4 class="product-price">
									<fmt:message>price</fmt:message>
									: ${o.unitPrice} VND
								</h4>

								
								
							<h4 class="product-offer">
							<fmt:message>QUANTITYAVAILABLE</fmt:message>:${o.quantity}
								</h4>
								<h4 class="product-price">
									<fmt:message>totalPrice</fmt:message>
									: ${o.price} VND
								</h4>

								<p class="product-remove">

									<a class="fa fa-trash" aria-hidden="true"
										href="editcart?cartId=${user.shoppingCartId}&itemId=${o.id}"></a>

								</p>

							</div>

						</div>
					</c:forEach>



				</div>
				<c:set var="checkout" value="${ requestScope.checkout}" />
				<c:set var="slg" value="${ requestScope.slg}" />
				<div class="cart-total">

					<p>

						<span><fmt:message>numberOfItem</fmt:message></span> <span>${slg}</span>

					</p>

					<p>

						<span><fmt:message>totalPrice</fmt:message></span> <span>
							${checkout.orderPrice}VND</span>

					</p>


					<a href="checkout?cartId=${user.shoppingCartId}"><fmt:message>checkout</fmt:message></a>

				</div>



			</div>

		</div>



	</section>
	<!-- end book section -->

	<!-- footer section -->
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