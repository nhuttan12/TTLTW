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
<title>Contact</title>
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
<script type="text/javascript">
function doCancel(item) {
	window.location="admin?gr="+item;
	
}
</script>
</head>

<body class="sub_page">
	<fmt:setLocale value="${sessionScope.lang}" />
	<fmt:setBundle basename="languages.lang" />
	<c:set var="user" value="${sessionScope.user}" />


	<!-- book section -->
	<section class="book_section layout_padding">
		<c:set var="erro" value="${requestScope.erro}" />

		<div class="loginn">

			<form action="addItem?gr=item" enctype="multipart/form-data"  method="post">
				<center>
					<h4 style="color: red"&"front-size:10px">${erro}</h4>
					<table class="login-table">
						<tr class="login-head">
							<th colspan="3"><center>
									<h1>
										<fmt:message>addItem</fmt:message>
									</h1>
								</center></th>
						</tr>
						<tr class="login-body">
							<td>ID:</td>
							<td><input type="number" name="ITEM_ID" value="${requestScope.ITEM_ID}"></td>
						</tr>
						<tr class="login-body">
							<td><fmt:message>nameItem</fmt:message> :</td>
							<td><input type="text" name="ITEM_NAME" value="${ requestScope.ITEM_NAME}"></td>
						</tr>
						<tr class="login-body">
							<td><fmt:message>price</fmt:message> :</td>
							<td><input type="number" name="UNITPRICE" value="${ requestScope.UNITPRICE}"></td>
						</tr>
						<tr class="login-body">
							<td><fmt:message>QUANTITYAVAILABLE</fmt:message> :</td>
							<td><input type="number" name="QUANTITY_AVAILABLE" value="${ requestScope.QUANTITY_AVAILABLE}"></td>
						</tr>

						<tr lass="login-body">
							<td><fmt:message>TYPE</fmt:message> :</td>
							<td><select name="TYPE">
									<option value="Chickenjoy">Chickenjoy</option>
									<option value="Burger">Burger</option>
									<option value="Drinks">Drinks</option>
									<option value="Noodles">Noodles</option>
							</select></td>
						</tr>
						<tr class="login-body">
							<td><fmt:message>IMAGES</fmt:message> :</td>
							<td><input type="file" name="IMAGES"></td>
						</tr>
						<tr class="login-foot">
							<td class="foot-item"><input class="button" type="submit"
								name="submit" value=" <fmt:message>ok</fmt:message> "></td>
							<td><input class="button" type="button" onclick="doCancel('item')" value="<fmt:message>cancel</fmt:message>"> </td>
						</tr>
					</table>
				</center>
			</form>
		</div>
	</section>
	<!-- end book section -->

	<!-- footer section -->

</body>
</html>