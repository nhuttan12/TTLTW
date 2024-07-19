<%@page import="model.ImportItem"%>
<%@page import="model.User"%>
<%@page import="model.Order"%>
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
</style>

<!-- Jquery -->
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<!-- Ajax -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!-- Css & Js -->
<link rel="shortcut icon" href="images/loo6.png" />
<link rel="stylesheet" href="css/admin/ii.css">

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
	href="https://cdn.datatables.net/2.0.8/css/dataTables.bootstrap5.min.css" />
<script src="https://cdn.datatables.net/2.0.8/js/dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/2.0.8/js/dataTables.bootstrap5.min.js"></script>

<!-- font awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						new DataTable('#product-table', {
							info : false,
							ordering : true,
							paging : true,
							lengthMenu : [ 10, 25, 50, 75, 100 ],
							language: {
			                    paginate: {
			                        first: "Trang đầu",
			                        previous: "Trang trước",
			                        next: "Trang sau",
			                        last: "Trang cuối"
			                    },
			                    processing: "Đang tải dữ liệu",
			                    infoEmpty: "Không có dữ liệu",
			                    zeroRecords: "Không tìm thấy",
			                    emptyTable: "Không có dữ liệu",
			                    search: "Tìm kiếm",
			                    lengthMenu: 'Hiển thị _MENU_ sản phẩm mỗi trang',
			                },
						});
						var inventory_table = $('#revenue-table').DataTable({
							info : false,
							ordering : true,
							paging : true,
							lengthMenu : [ 10, 25, 50, 75, 100 ],
							language: {
			                    paginate: {
			                        first: "Trang đầu",
			                        previous: "Trang trước",
			                        next: "Trang sau",
			                        last: "Trang cuối"
			                    },
			                    processing: "Đang tải dữ liệu",
			                    infoEmpty: "Không có dữ liệu",
			                    zeroRecords: "Không tìm thấy",
			                    emptyTable: "Không có dữ liệu",
			                    search: "Tìm kiếm",
			                    lengthMenu: 'Hiển thị _MENU_ sản phẩm mỗi trang',
			                },
						});
						var inventory_table = $('#not-use-in-6-month-table').DataTable({
							info : false,
							ordering : true,
							paging : true,
							lengthMenu : [ 10, 25, 50, 75, 100 ],
							language: {
			                    paginate: {
			                        first: "Trang đầu",
			                        previous: "Trang trước",
			                        next: "Trang sau",
			                        last: "Trang cuối"
			                    },
			                    processing: "Đang tải dữ liệu",
			                    infoEmpty: "Không có dữ liệu",
			                    zeroRecords: "Không tìm thấy",
			                    emptyTable: "Không có dữ liệu",
			                    search: "Tìm kiếm",
			                    lengthMenu: 'Hiển thị _MENU_ sản phẩm mỗi trang',
			                },
						});
						var inventory_table = $('#inventory-table').DataTable({
							info : false,
							ordering : true,
							paging : true,
							lengthMenu : [ 10, 25, 50, 75, 100 ],
							language: {
			                    paginate: {
			                        first: "Trang đầu",
			                        previous: "Trang trước",
			                        next: "Trang sau",
			                        last: "Trang cuối"
			                    },
			                    processing: "Đang tải dữ liệu",
			                    infoEmpty: "Không có dữ liệu",
			                    zeroRecords: "Không tìm thấy",
			                    emptyTable: "Không có dữ liệu",
			                    search: "Tìm kiếm",
			                    lengthMenu: 'Hiển thị _MENU_ sản phẩm mỗi trang',
			                },
						});
						var import_table = $('#import-table').DataTable({
							info : false,
							ordering : true,
							paging : true,
							lengthMenu : [ 10, 25, 50, 75, 100 ],
							language: {
			                    paginate: {
			                        first: "Trang đầu",
			                        previous: "Trang trước",
			                        next: "Trang sau",
			                        last: "Trang cuối"
			                    },
			                    processing: "Đang tải dữ liệu",
			                    infoEmpty: "Không có dữ liệu",
			                    zeroRecords: "Không tìm thấy",
			                    emptyTable: "Không có dữ liệu",
			                    search: "Tìm kiếm",
			                    lengthMenu: 'Hiển thị _MENU_ sản phẩm mỗi trang',
			                },
						});
						var order_table = $('#order-table').DataTable({
							info : false,
							ordering : true,
							paging : true,
							lengthMenu : [ 10, 25, 50, 75, 100 ],
							language: {
			                    paginate: {
			                        first: "Trang đầu",
			                        previous: "Trang trước",
			                        next: "Trang sau",
			                        last: "Trang cuối"
			                    },
			                    processing: "Đang tải dữ liệu",
			                    infoEmpty: "Không có dữ liệu",
			                    zeroRecords: "Không tìm thấy",
			                    emptyTable: "Không có dữ liệu",
			                    search: "Tìm kiếm",
			                    lengthMenu: 'Hiển thị _MENU_ hoá đơn mỗi trang',
			                },
						});
						var user_table = $('#user-table').DataTable({
							info : false,
							ordering : true,
							paging : true,
							lengthMenu : [ 10, 25, 50, 75, 100 ],
							language: {
			                    paginate: {
			                        first: "Trang đầu",
			                        previous: "Trang trước",
			                        next: "Trang sau",
			                        last: "Trang cuối"
			                    },
			                    processing: "Đang tải dữ liệu",
			                    infoEmpty: "Không có dữ liệu",
			                    zeroRecords: "Không tìm thấy",
			                    emptyTable: "Không có dữ liệu",
			                    search: "Tìm kiếm",
			                    lengthMenu: 'Hiển thị _MENU_ người dùng mỗi trang',
			                },
						});
						var log_table = $('#log_table').DataTable({

							createdRow : function(row, data, stt_row) {
								var level_ = data[3];//cột message
								switch (level_) {
								case "INFO":
									$(row).css('background-color', '#20c997');
									break;
								case "WARN":
									$(row).css('background-color', '#ffc107');
									break;
								case "ERROR":
									$(row).css('background-color', '#c96d2e');
									break;
								case "FATAL":
									$(row).css('background-color', '#ff0000');
									break;

								}
							},
							paging : true,
							searching : true,
							info : true,
							lengthChange : true,
							ordering : true,
							pageLength : 5,
							lengthMenu : [ 5, 10, 20, 75, 100 ]
						});
						$('#level_select').on('change', function() {
							var selectedValue = $(this).val();
							log_table.column(3).search(selectedValue).draw();

						});
						function changeTime() {
							var to_time = $('#to_time').val();
							var from_time = $('#from_time').val();
							if (!from_time || !to_time) {//không chọn 1 trong 2
								log_table.rows().every(function() {
									$(this.node()).show();
								});
								log_table.draw();
								return;
							}
							var to_time1 = Date.parse(to_time);
							var from_time2 = Date.parse(from_time);
							console.log("From Time:", from_time2);
							console.log("To Time:", to_time1);
							log_table
									.rows()
									.every(
											function() {
												var data = this.data();
												var a = data[1];
												console.log(" Date:", a);
												var formattedDate = a
														.replace(
																/(\d{2})-(\d{2})-(\d{4}) (\d{2}):(\d{2}):(\d{2})/,
																'$3-$2-$1T$4:$5:$6');
												var dateObject = new Date(
														formattedDate);
												var date = Date
														.parse(dateObject);
												console.log("Row Date:", date);
												if (date >= from_time2
														&& date <= to_time1) {
													console.log("Show ");
													$(this.node()).show();
												} else {
													console.log("ẩn nè");
													$(this.node()).hide();
												}
											});

							log_table.draw();
						}

						$('#from_time, #to_time').on('change', changeTime);
						function convertDateFormat(dateStr) {
							// Tách ngày và thời gian từ chuỗi
							var parts = dateStr.split(' ');
							var datePart = parts[0];
							var timePart = parts[1];

							// Tách ngày thành các thành phần ngày, tháng, năm
							var dateParts = datePart.split('/');
							var day = dateParts[0];
							var month = dateParts[1];
							var year = dateParts[2];

							// Định dạng lại chuỗi ngày tháng
							var formattedDate = year + '-' + month + '-' + day
									+ ' ' + timePart;

							return formattedDate;
						}
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
	function importItem(){
		window.location = "importItem";
	}
</script>
<style>
table.dataTable th.dt-type-numeric, table.dataTable th.dt-type-date,
	table.dataTable td.dt-type-numeric, table.dataTable td.dt-type-date {
	text-align: center;
}

table.dataTable thead th, table.dataTable thead td, table.dataTable tfoot th,
	table.dataTable tfoot td {
	text-align: center;
}
.table-footer{
    position: fixed;
    bottom: 0px;
    width: 100%;
}
</style>
<script type="text/javascript" src="js/table2excel.js"></script>
</head>
<body>


	<header>
		<h2>T Fast Food - Admin</h2>

	</header>

	<!-- Menu -->

	<aside class="sidebar">
		<ul class="nav" style="display: block;">
			<li class="nav-title">MENU</li>
			<li class="nav-item">
				<a class="nav-link ${gr2 eq home?'active':''}" href="admin?gr=home">
					<i class="fa fa-home"></i> Trang Chủ
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${gr2 eq item?'active':''}" href="admin?gr=item">
					<i class="fa fa-th-large"></i>Quản lý sản phẩm
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${gr2 eq inventory?'active':''}" href="admin?gr=inventory">
					<i class="fa-solid fa-warehouse"></i></i>Quản lý tồn kho
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${gr2 eq importHistory?'active':''}" href="admin?gr=importHistory">
					<i class="fa-solid fa-warehouse"></i></i>Lịch sử nhập hàng
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${gr2 eq spcart?'active':''}" href="admin?gr=spcart">
					<i class="fa-solid fa-newspaper"></i>Quản lý đơn hàng
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${gr2 eq user?'active':''}" href="admin?gr=user">
					<i class="fa-regular fa-user"></i>Quản lý User
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${gr2 eq log?'active':''}" href="admin?gr=log">
					<i class="fa-regular fa-note-sticky"></i>Quản lý Log
				</a>
			</li>
			<li class="nav-item">
				<a href="index" class="nav-link"> 
					<i class="fa fa-arrow-left"></i>Quay về Home
				</a>
			</li>
		</ul>
	</aside>
	<div class="main">
		<c:set var="gr2" value="${requestScope.gr2}" />
		<c:if test="${gr2 eq home}">
			<!-- Khung hiển thị chính -->
			<div class="home">
				<h1 style="color: white; text-align: center;">
					Những người không dùng dịch vụ hệ thống trong vòng 6 tháng
				</h1> 
				<div class="table-content" style="padding-top: 2rem;">
					<table style="width: 100%" id="not-use-in-6-month-table">
						<thead>
							<tr class="table-header">
								<th>Mã số</th>
								<th>Tên người dùng</th>
								<th>Ngày sử dụng gần nhất</th>
								<th>Số ngày cụ thể chưa đăng nhập</th>
							</tr>
						</thead>
						<tbody>
							<%
							List<Order> orders = (List<Order>) request.getAttribute("orders");
							if (orders != null) {
								for (Order o : orders) {
							%>
							<tr>
								<td><%=o.getUserId()%></td>
								<td><%=o.getName()%></td>
								<td><%=o.getOrderDate()%></td>
								<td><%=o.getDateDifference()%></td>
							</tr>
							<%
								}
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
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
								<th>Giá tiền</th>
								<th>Giảm giá</th>
								<th>Mô tả</th>
								<th>Hiển thị</th>
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
								<td><%=i.getPrice()%> vnđ</td>
								<td><%=i.getDiscount()%></td>
								<td><%=i.getDiscription()%></td>
								<td><%=(i.getHidden()==1)?"Hiển thị":"Ẩn"%></td>
								<td>
									<a class="s" href="edit?id=<%=i.getId()%>">
										<img width="20px" alt="" src="images/edit2.png">
									</a> 
								</td>
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
					<button id="export-to-excel">
						<i class="fa-regular fa-note-sticky"></i> Xuất ra file excel 
					</button>
				</div>
			</div>

		</c:if>
		<!-- // sanpham -->
		<!-- Tồn kho -->
		<c:if test="${gr2 eq inventory}">
			<div class="tonkho">
				<div class="table-content">
					<table style="width: 100%" id="inventory-table">
						<thead>
							<tr class="table-header">
								<th>ID</th>
								<th>Tên sản phẩm</th>
								<th>Loại</th>
								<th>Số lượng tồn kho</th>
								<th>Giá bán</th>
								<th>Giá nhập</th>
								<th>Chênh lệch</th>
							</tr>
						</thead>
						<tbody>
							<%
							List<Item> inventories = (List<Item>) request.getAttribute("listInventory");
							if (inventories != null) {
								for (Item inv : inventories) {
							%>
							<tr>
								<td><%=inv.getId() %></td>
								<td><%=inv.getName() %></td>
								<td><%=inv.getCategory().getCategoryName() %></td>
								<td><%=inv.getImportDetail().getQuantity() %></td>
								<td><%=inv.getPrice() %> vnđ</td>
								<td><%=inv.getImportDetail().getPrice() %> vnđ</td>
								<td><%=inv.getDifference() %> vnđ</td>
							</tr>
							<%
								}
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
		<!-- // tonkho -->
		<!-- Lịch sử nhập hàng -->
		<c:if test="${gr2 eq importHistory}">
			<div class="nhaphang">
				<div class="table-content">
					<table style="width: 100%" id="import-table">
						<thead>
							<tr class="table-header">
								<th>ID</th>
								<th>Người nhập</th>
								<th>Tổng số lượng nhập</th>
								<th>Tổng các loại sản phẩm nhập</th>
								<th>Tổng giá</th>
								<th>Ngày nhập</th>
								<th>Thao tác</th>
							</tr>
						</thead>
						<tbody>
							<%
							List<ImportItem> importItems = (List<ImportItem>) request.getAttribute("importList");
							if (importItems != null) {
								for (ImportItem item : importItems) {
							%>
							<tr>
								<td><%=item.getId() %></td>
								<td><%=item.getUser().getName() %></td>
								<td><%=item.getTotalQuantity() %></td>
								<td><%=item.getTotalItem() %></td>
								<td><%=item.getTotalPrice() %> vnđ</td>
								<td><%=item.getImportDate() %></td>
								<td>
									<a href="getImportDetails?id=<%=item.getId()%>" style="color: white;">
										Xem chi tiết
									</a>
								</td>
							</tr>
							<%
								}
							}
							%>
						</tbody>
					</table>
				</div>
				<div class="table-footer">
					<button onclick="importItem()">
						<i class="fa fa-plus-square"></i> Nhập thêm số lượng hàng hoá
					</button>
				</div>
			</div>
		</c:if>
		<!-- //lichsunhaphang -->
		<c:if test="${gr2 eq spcart}">
			<div class="donhang">
				<!-- Đơn Hàng -->
				<div class="table-content">
					<table style="width: 100%" id="order-table">
						<thead>
							<tr class="table-header">
								<th>ID</th>
								<th>Khách hàng</th>
								<th>Tổng giá hoá đơn</th>
								<th>Địa chỉ</th>
								<th>Ngày tạo hoá đơn</th>
								<th>Ngày giao</th>
								<th>Trang thai</th>
								<th>Thay đổi</th>
							</tr>
						</thead>
						<tbody>
							<%
							List<Order> orders = (List<Order>) request.getAttribute("listOrder");
							if (orders != null) {
								for (Order o : orders) {
							%>
							<tr>
								<td><%=o.getOrderId()%></td>
								<td><%=o.getName()%></td>
								<td><%=o.getTotalPrice()%> vnđ</td>
								<td><%=o.getAddress()%></td>
								<td><%=o.getOrderDate()%></td>
								<td><%=o.getDeliveriDate()%></td>
								<td><%=o.getStatusOrder().getName()%></td>
								<td style="display: flex; flex-direction: row;">
									<a href="<%=request.getContextPath()%>/editOrder?id=<%=o.getOrderId()%>">
										<i class="fa-solid fa-pencil"></i>
									</a> 
									<a href="<%=request.getContextPath()%>/getOrderDetail?id=<%=o.getOrderId()%>">
										<i class="fa-solid fa-arrow-right"></i>
									</a>
								</td>
							</tr>
							<%
								}
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
			<!-- // don hang -->
		</c:if>
		<c:if test="${gr2 eq user}">
			<div class="khachhang">
				<!-- Khách hàng -->
				<div class="table-content">
					<table style="width: 100%" id="user-table">
						<thead>
							<tr class="table-header">
								<th>ID</th>
								<th>Họ tên</th>
								<th>Email</th>
								<th>Tài khoản</th>
								<th>Chức vụ</th>
								<th>Trạng thái</th>
								<th>Hành động</th>
							</tr>
						</thead>
						<tbody>
							<%
							List<User> users = (List<User>) request.getAttribute("listUser");
							if (users != null) {
								for (User u : users) {
							%>
							<tr>
								<td><%=u.getId() %></td>
								<td><%=u.getName() %></td>
								<td><%=u.getEmail() %></td>
								<td><%=u.getUserName() %></td>
								<td><%=u.getRolee().getName() %></td>
								<td><%=u.getStatusUser().getName() %></td>
								<td>
									<a href="<%=request.getContextPath()%>/updateStatusUser?id=<%=u.getId()%>">
										<i class="fa-solid fa-pencil"></i>
									</a> 
								</td>
							</tr>
							<%
								}
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
			<!-- // ket thuc khach hang -->
		</c:if>
		<!-- // log -->
		<c:if test="${gr2 eq log}">
		<%
			String error=(String)request.getAttribute("error");
			if(error != "authorization"){
		%>
				<c:set var="listLog" value="${requestScope.listLog}" />
				<div class="table-content">
					<div class="search">
						<b>From</b> <input type="datetime-local" id="from_time"> <b>To</b>
						<input type="datetime-local" id="to_time">
					</div>
	
					<table style="width: 100%; border: solid white;" id="log_table"
						border="1px white;">
						<thead>
							<tr class="table-header">
								<th>ID</th>
								<th>Thời gian</th>
								<th>IP</th>
								<th>Level</th>
								<th>Nội dung</th>
								<th>Giá trị trước</th>
								<th>Giá trị hiện tại</th>
	
							</tr>
						</thead>
						<tbody>
							<c:forEach var="log" items="${listLog}">
								<tr>
									<td>${log.id}</td>
									<td>${log.time}</td>
									<td>${log.IP}</td>
									<td>${log.level}</td>
									<td>${log.message}</td>
									<td>${log.pre_value}</td>
									<td>${log.current_value}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<select id="level_select">
						<option value="">Level</option>
						<option value="INFO">INFOR</option>
						<option value="WARN">WARN</option>
						<option value="ERRO">ERRO</option>
						<option value="FATAL">FATAL</option>
					</select>
				</div>
		<%
			}else{
		%>
			<h1 style="
			    padding-top: 3rem;
			    text-align: center;
			    color: white;
			">Lỗi: Bạn không đủ quyền hạn để truy cập vào mục này</h1>
		<%
			}
		%>
		</c:if>
		<!-- // ket thuc log -->
		<!-- // main -->

	</div>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							var log_table = $('#log_table')
							function changeTime() {
								var to_time = $('#to_time').val();
								var from_time = $('#from_time').val();
								var to_time1 = new Date(to_time);
								var from_time2 = new Date(from_time);
								log_table
										.columns(1)
										.search(
												function(data, index, rowData) {
													var rowDataTime = new Date(
															data); // Chuyển đổi dữ liệu từ cột thời gian sang đối tượng Date
													// Kiểm tra xem thời gian rowDataTime có nằm trong khoảng từ from_time đến to_time không
													return (rowDataTime >= from_time2 && rowDataTime <= to_time1);
												}).draw();
							}
							$('#export-to-excel').click(function() {
							    var table2excel = new Table2Excel();
							    table2excel.export($('#product-table'));
							});
						});
	</script>
</body>
</html>