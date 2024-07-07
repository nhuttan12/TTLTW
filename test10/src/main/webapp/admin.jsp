<%@page import="model.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Admin</title>
<style type="text/css">
a {
	display: inline;
}

img {
	display: inline;
}
#product-table_filter label input{
	color: #e4e7ea;
}
#product-table_length {
	color: #e4e7ea;
}
#product-table_length label select {
	color: #e4e7ea;
}
#product-table tbody tr{
	background-color: transparent;
	color: #e4e7ea;
}
#product-table_previous, #product-table_next {
	color: #e4e7ea !important;
}
#product-table_paginate span .paginate_button.current, 
#product-table_paginate span .paginate_button {
	background-color: white;
}
</style>

<!-- Jquery -->
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>

<!-- Css & Js -->
<link rel="shortcut icon" href="images/logo2.png" />
<link rel="stylesheet" href="css/admin/ad.css">

<!-- boot strap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

<!-- data table -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css" />
<script
	src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		new DataTable('#product-table', {
		    info: false,
		    ordering: true,
		    paging: true,
		    lengthMenu: [10, 25, 50, 75, 100],
		    language: {
			    paginate: {
		            first: "Trang đầu",
		            previous: "Trang trước",
		            next: "Trang sau",
		            last: "Trang cuối"
		        },
		    },
		});
	});
</script>
<script type="text/javascript">
	function doDelete(id, aa) {
		if (confirm("Are you delete Item with id=" + id)) {
			window.location = "delete?id=" + id + "&gr=" + aa;
		}
	}

	function doDelete2(orderId, aa) {
		if (confirm("Are you delete Oder with id=" + orderId)) {
			window.location = "delete?orderId=" + orderId + "&gr=" + aa;
		}
	}
	function doDeleteUser(id, user) {
		if (confirm("Are you delete User with id=" + id)) {
			window.location = "delete?userId=" + id + "&gr=" + user;
		}
	}
	function doUpdateStatusUser(id, status, user) {
		if (status == 1) {
			if (confirm("Are you block User with id=" + id)) {
				window.location = "updateStatusUser?userId=" + id + "&gr="
						+ user;
			}
		} else if (status == 2) {
			if (confirm("Are you unblock User with id=" + id)) {
				window.location = "updateStatusUser?userId=" + id + "&gr="
						+ user;
			}
		}

	}
	function doLogout() {
		if (confirm("Are you Logout?")) {
			window.location = "logout";
		}
	}
	function doAddItem() {
		window.location = "addItem";
	}
</script>
</head>
<body>
	<fmt:setLocale value="${sessionScope.lang}" />
	<fmt:setBundle basename="languages.lang" />

	<header>
		<h2>T Fast Food - Admin</h2>
		<div style="margin-right: 30px; float: right;" class="language">
			<a style="color: white;" href="?lang_local=vi_VN" class="lang">VN
			</a> <a style="color: white;" href="?lang_local=en_US" class="lang">
				EN </a>
		</div>
	</header>

	<!-- Menu -->

	<aside class="sidebar">
		<ul class="nav">
			<li class="nav-title">MENU</li>
			<li class="nav-item"><a
				class="nav-link ${gr2 eq home?'active':''}" href="admin?gr=home"><i
					class="fa fa-home"></i> Trang Chủ</a></li>
			<li class="nav-item"><a
				class="nav-link ${gr2 eq item?'active':''}" href="admin?gr=item"><i
					class="fa fa-th-large"></i> Sản Phẩm</a></li>
			<li class="nav-item"><a
				class="nav-link ${gr2 eq spcart?'active':''}" href="admin?gr=spcart"><i
					class="fa fa-file-text-o"></i> Đơn Hàng</a></li>
			<li class="nav-item"><a
				class="nav-link ${gr2 eq user?'active':''}" href="admin?gr=user"><i
					class="fa fa-address-book-o"></i> Khách Hàng</a></li>
			<li class="nav-item">
				<hr>
			</li>
			<li class="nav-item"><a href="#" class="nav-link"
				onclick="doLogout()"> <i class="fa fa-arrow-left"></i> <fmt:message>logout</fmt:message>
			</a></li>
		</ul>
	</aside>
	<div class="main">
		<c:set var="gr2" value="${requestScope.gr2}" />
		<c:if test="${gr2 eq home}">
			<!-- Khung hiển thị chính -->
		</c:if>
		<c:if test="${empty gr2}">
		</c:if>
		<!-- Sản Phẩm -->
		<c:if test="${gr2 eq item}">
			<div class="sanpham">

				<div class="table-content">
					<table style="width: 100%" id="product-table">	
						<thead>
							<tr class="table-header">
								<th>Mã số</th>
								<th>Loại</th>
								<th>Tên</th>
								<th>Giá bán</th>
								<th>Giá nhập</th>
								<th>Chênh lệch</th>
								<th>Giảm giá</th>
								<th>Số lượng</th>
								<th>Thao tác</th>
							</tr>
						</thead>
						<tbody>
							<%
							List<Item> itemList = (List<Item>) request.getAttribute("listItem");
							if (itemList != null) {
								for (Item i : itemList) {
							%>
							<tr>
								<td><%=i.getId()%></td>
								<td><%=i.getCategory().getCategoryName()%></td>
								<td><%=i.getName()%></td>
								<td><%=i.getPrice()%></td>
								<td><%=i.getImportDetail().getPrice()%></td>
								<td><%=i.getDifference()%></td>
								<td><%=i.getDiscount()%></td>
								<td><%=i.getImportDetail().getQuantity()%></td>
								<td><a class="s" href="edit?id=<%=i.getId()%>&gr=spcart">
										<img width="20px" alt="" src="images/edit2.png">
								</a> <a class="s" href="#"
									onclick="doDelete('<%=i.getId()%>','item')"> <img
										width="20px" alt="" src="images/delete.png">
								</a></td>
							</tr>
							<%
							}
							}
							%>
						</tbody>
					</table>
				</div>
				<div class="table-footer">

					<button onclick="doAddItem()">
						<i class="fa fa-plus-square"></i> Thêm sản phẩm
					</button>
				</div>
			</div>

		</c:if>
		<!-- // sanpham -->
		<c:if test="${gr2 eq spcart}">
			<div class="donhang">
				<c:set var="users" value="${requestScope.users}" />
				<c:set var="itemList" value="${requestScope.itemList}" />
				<c:set var="orderList" value="${requestScope.orderList }" />
				<c:set var="numUser" value="${requestScope.numUser }" />
				<c:set var="numOrder" value="${requestScope.numOrder }" />
				<!-- Đơn Hàng -->
				<div class="table-content">
					<table style="width: 100%">
						<tr class="table-header">
							<th>ID</th>
							<th>Khach</th>
							<th>San pham</th>
							<th>Tong tien</th>
							<th>Ngay</th>
							<th>Trang thai</th>
							<th>Hanh dong</th>
						</tr>
						<c:forEach var="i" begin="0" end="${fn:length(users)}" step="1">
							<c:set var="u" value="${users[i] }" />
							<c:set var="ordersOfI" value="${orderList[i]}" />
							<c:set var="numberOrdersOfI" value="${fn:length(ordersOfI)}" />
							<c:set var="itemsOfI" value="${itemList[i]}" />
							<c:forEach var="ii" begin="0" step="1" end="${numberOrdersOfI}">
								<c:set var="o" value="${ordersOfI[ii]}" />
								<c:set var="items" value="${itemsOfI[ii]}" />

								<tr>
									<td>${o.orderId}</td>
									<td>${u.userName}</td>
									<td><c:forEach var="iz" items="${items}">
						${iz.name}
						</c:forEach></td>
									<td>${o.orderPrice}</td>
									<td>${o.date}</td>
									<td>${o.status==3?"đã giao hàng":"chưa giao hàng"}</td>
									<td><a href="updateStatus?orderId=${o.orderId }&gr=spcart"><img
											width="20px" alt="" src="images/ok2.png"></a> <a href="#"
										onclick="doDelete2('${o.orderId}','spcart')"><img
											width="20px" alt="" src="images/delete2.png"></a></td>
								</tr>
							</c:forEach>
						</c:forEach>
					</table>
				</div>
			</div>
			<!-- // don hang -->
		</c:if>
		<c:if test="${gr2 eq user}">
			<div class="khachhang">

				<!-- Khách hàng -->
				<c:set var="listuser" value="${requestScope.listUser }" />
				<div class="table-content">
					<table style="width: 100%">
						<tr class="table-header">
							<th>ID</th>
							<th>HO TEN</th>
							<th>EMAIL</th>
							<th>TAI KHOAN</th>
							<th>MAT KHAU</th>
							<th>HANH DONG</th>
						</tr>
						<c:forEach var="i" items="${listuser }">
							<tr>
								<td>${i.id}</td>
								<td>${i.name}</td>
								<td>${i.email}</td>
								<td>${i.userName}</td>
								<td>${i.password}</td>
								<td>
									<div class="switch">
										<input class="slider"
											onclick="doUpdateStatusUser('${i.id}','${i.status }','user')"
											type="checkbox" id="${i.id}"
											${i.status == 1 ? 'checked' : ''} hidden> <label
											for="${i.id }"></label>
									</div> <a href="#" onclick="doDeleteUser('${i.id}','user')"> <img
										width="20px" alt="" src="images/delete2.png"></a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<!-- // khach hang -->
		</c:if>
		<!-- // main -->

	</div>

</body>
</html>