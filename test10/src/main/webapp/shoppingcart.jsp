<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
<!-- bootstrap core css -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/cart2.css">
<!--owl slider stylesheet -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />

<!-- font awesome style -->
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!-- select2 -->
   <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
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
	function name() {

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
					<a class="navbar-brand" href="index"><img alt="logo"
						style="width: 120px" src="images/log5.png"> </a>



					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav  mx-auto ">
							<li class="nav-item "><a class="nav-link" href="index.jsp"><fmt:message>menu.home</fmt:message>
							</a></li>
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
									<a href="admin" class="user_link"><img width="30px" alt=""
										src="images/admin.png"> </a>

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
		<div class="container">

			<h1>
				<fmt:message>SHOPPINGCART</fmt:message>
			</h1>

			<div class="cart">
				<c:set var="listCart" value="${ requestScope.listCart}" />
				<c:set var="listItem" value="${ requestScope.listItem}" />

				<div class="products">

					<c:if test="${not empty listCart}">
						<c:forEach var="i" begin="0" end="${fn:length(listCart)-1}"
							step="1">

							<div class="product" >
								<div style="margin: auto;" ><img style="width: 120px;height: auto" src="${listItem[i].imageName}"></div>
								

								<div class="product-info">

									<h4 class="product-name">
										<span>${listItem[i].name}</span>
									</h4>

									<h6 class="product-price">
										<fmt:message>price</fmt:message>
										: ${listItem[i].price} VND
									</h6>
									<h6 class="product-offer">
										<fmt:message>QUANTITYAVAILABLE</fmt:message>
										: <a
											href="editcart?action=decrease&cartID=${listCart[i].id}&itemId=${listItem[i].id}">
											<i class="fa fa-minus"></i>
										</a> ${listCart[i].quantity} <a
											href="editcart?action=increase&cartID=${listCart[i].id}&itemId=${listItem[i].id}">
											<i class="fa fa-plus"></i>
										</a>
									</h6>
									<h6 class="product-price">
										<fmt:message>totalPrice</fmt:message>
										: ${listCart[i].totalPrice} VND
									</h6>

									<p class="product-remove">

										<a class="fa fa-trash" aria-hidden="true"
											href="editcart?action=remove&cartID=${listCart[i].id}&itemId=${listItem[i].id}"></a>
									</p>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
				<div class="cart-total">
					<div id="error_message" style="color: red; display: none;"></div>
					<p> <select id="promotion_select">
							<option value=""><fmt:message>VOUCHER</fmt:message></option>
							<c:forEach var="pro" items="${requestScope.list_Promotion}">
							<option value=${pro.discount} >
								<div><fmt:message>DISCOUNT</fmt:message></div>
								<div>${requestScope.tongtien_giohang*pro.discount/100}<span>VND</span></div>
							</option>
							</c:forEach>
					</select>
</p>
					
					<p>

						<span><fmt:message>numberOfItem</fmt:message></span> <span>${requestScope.total_quantity}</span>

					</p>

					<p>

						<span><fmt:message>totalPrice</fmt:message></span> <span id="total">${requestScope.tongtien_giohang}VND</span>

					</p>


					<a href="#"><fmt:message>checkout</fmt:message></a>

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
	<script type="text/javascript">
	  $(document).ready(function() {
          $('#promotion_select').select2({
              tags: true,
              placeholder: "Voucher",
              allowClear: true,
              createTag: function (params) {
                  return {
                      id: params.term,
                      text: params.term,
                      newOption: true
                  }
              }
          });
          $('#promotion_select').on('change', function() {
        	    var promoCode = $('#promotion_select').val();
        	    $.ajax({
        	        url: 'shoppingcart', // URL đến servlet xử lý mã khuyến mãi
        	        type: 'POST',
        	        data: { code: promoCode },
        	        success: function(response) {
        	        	var a=response.total;
        	        	var b=response.error;
        	        	  if (a !== undefined) {
        	                  $('#total').text(a);
        	                  $('#error_message').text(''); // Xóa thông báo lỗi nếu có
        	              } else if (b !== undefined) {
        	                  $('#error_message').text(b).show();
        	                 
        	              }
        	      
        	        },
        	        error: function(response) {
        	            alert('Đã xảy ra lỗi khi gửi mã khuyến mãi.');
        	        }
        	    });
        	});
      });
	</script>
</body>

</html>