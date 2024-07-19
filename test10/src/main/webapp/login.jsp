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
<link rel="shortcut icon" href="images/loo6.png" />
<title>Login</title>
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

			<form action="login" method="post">
				<center>
					<h4  style="color: blue">${erro}</h4>
					<table class="login-table" border: 5px>
						<tr class="login-head">
							<th colspan="3"><center>
									<h1>
										<fmt:message>login</fmt:message>
									</h1>
								</center></th>
						</tr>
						<tr class="login-body">
							<td><fmt:message>username</fmt:message> :</td>
							<td colspan="2"><input type="text" name="taikhoan" required ></td>
						</tr>
						<tr class="login-body">
							<td  colspan="1"><fmt:message>pass</fmt:message> :</td>
							<td  colspan="2"><input type="password" name="matkhau" required ></td>
						</tr>
						<tr class="login-foot">
							<th class="foot-item"><input class="button" type="submit"
								name="submit" value=" <fmt:message>send</fmt:message> "
								style="margin-left: 150px"></th>

						</tr>
						<tr>
							<th class="foot-item"><a class="text" href="register.jsp">
									<fmt:message>register</fmt:message>?
							</a></th>


						</tr>
						<tr>
							<th class="foot-item"><a
								href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:8080/test10/loginGoogle&response_type=code
    &client_id=1089316008342-fllevmop2b90sd7mmsqotdo1neb934sg.apps.googleusercontent.com&approval_prompt=force">
									<button class="btn google-btn social-btn" type="button">
										<span><i class="fab fa-google-plus-g"></i> Đăng nhập
											với Google+</span>
									</button>
							</a></th>


						</tr>
						<tr>
							<th class="foot-item"><a
								href="https://www.facebook.com/v19.0/dialog/oauth?client_id=1586113255570962&redirect_uri=http://localhost:8080/test10/loginFacebook&scope=email">
									<button class="btn fa-book-btn social-btn" type="button"
										style="background-color: #3b5998; color: white;">
										<span><i class="fab fa-facebook-f"></i> Đăng nhập với
											Facebook</span>
									</button>
							</a></th>
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
	<script>
		window.fbAsyncInit = function() {
			FB.init({
				appId : '{your-app-id}',
				cookie : true,
				xfbml : true,
				version : '{api-version}'
			});

			FB.AppEvents.logPageView();

		};

		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) {
				return;
			}
			js = d.createElement(s);
			js.id = id;
			js.src = "https://connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
</body>


</html>