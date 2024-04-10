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
</style>
<link rel="shortcut icon" href="images/logo2.png" />

<link rel="stylesheet" href="css/admin/ad.css">

<script type="text/javascript">
	function doDelete(id,aa) {
		if (confirm("Are you delete Item with id=" + id)) {
			window.location = "delete?id=" + id+"&gr="+aa;
		}
	}

	function doDelete2(orderId,aa) {
		if (confirm("Are you delete Oder with id=" + orderId)) {
			window.location = "delete?orderId=" + orderId+"&gr="+aa;
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
		function doAddItem() {
			window.location = "addItem";
		}
		
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
			<li class="nav-item"><a href="#"  class="nav-link"
				onclick="doLogout()"> <i
					class="fa fa-arrow-left"></i> <fmt:message>logout</fmt:message>
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

				<c:set var="listItem" value="${requestScope.listItem}" />
				<div class="table-content">
					<table style="width: 100%">
						<tr class="table-header">
							<th>ID</th>
							<th>TYPE</th>
							<th>NAME</th>
							<th>PRICE</th>
							<th>QUANITY</th>
							<th>ACTION</th>
						</tr>
						<c:forEach var="i" items="${listItem }">
							<tr>
								<td>${i.id}</td>
								<td>${i.type}</td>
								<td>${i.name}</td>
								<td>${i.unitPrice}</td>
								<td>${i.quantityAvailable}</td>
								<td><a class="s" href="edit?id=${i.id}&gr=spcart"> <img
										width="20px" alt="" src="images/edit2.png"></a> <a class="s"
									href="#" onclick="doDelete('${i.id}','item')"> <img width="20px"
										alt="" src="images/delete.png"></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				  <div class="table-footer">
				    
                <button onclick="doAddItem()">
                    <i class="fa fa-plus-square"></i>
                    Thêm sản phẩm
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
										onclick="doDelete2('${o.orderId}','spcart')"><img width="20px"
											alt="" src="images/delete2.png"></a></td>
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