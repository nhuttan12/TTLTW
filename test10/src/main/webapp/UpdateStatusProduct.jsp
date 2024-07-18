<%@page import="model.Order"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Chỉnh sửa thông tin hoá đơn</title>
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
<link rel="shortcut icon" href="images/loo6.png" />
</head>
<style>
.main {
	width: 50%;
	padding: 2rem 4rem 2rem 4rem;
	border: 1px solid #9c9c9c;
	border-radius: 10px;
	box-shadow: 0px 5px 10px #9c9c9c;
	width: 50%;
}

.container {
	padding-top: 2rem;
}
</style>

<body>
	<div class="container">
		<div class="main m-auto">
			<h1 class="text-center">Chỉnh sửa thông tin hoá đơn</h1>
			<%
			Order order = (Order) request.getAttribute("order");
			%>
			<form action="editOrder" method="post">
				<div class="form-group">
					<label for="product-name">Mã hoá đơn</label> <input type="text"
						name="order-id" class="form-control" id="product-name"
						value="<%=order.getOrderId()%>" readonly>
				</div>
				<div class="form-group">
					<label for="product-price">Tên khách hàng</label> <input
						name="customer" type="text" class="form-control" id="customer"
						value="<%=order.getName()%>" readonly>
				</div>
				<div class="form-group">
					<label for="product-discount">Tổng giá hoá đơn</label> <input
						type="number" name="totalPrice"
						value="<%=order.getTotalPrice()%>" class="form-control"
						id="total-price" readonly>
				</div>
				<div class="form-group">
					<label for="product-discription">Ngày đặt</label> <input
						name="order-date" type="text" class="form-control" id="order-date"
						value="<%=order.getOrderDate()%>" readonly>
				</div>
				<div class="form-group">
					<label for="product-discription">Ngày giao</label> <input
						value="<%=order.getDeliveriDate()%>" name="deliver-date" type="date"
						class="form-control" id="deliver-date">
				</div>
				<label for="category">Loại sản phẩm</label> <select name="status"
					class="form-control" id="category" >
					<option value=1 <%=(order.getStatusOrder().getId())==1?"selected":""%>>đang xử lý</option>
					<option value=2 <%=(order.getStatusOrder().getId())==2?"selected":""%>>xác nhận và đóng gói</option>
					<option value=3 <%=(order.getStatusOrder().getId())==3?"selected":""%>>vận chuyển</option>
					<option value=4 <%=(order.getStatusOrder().getId())==4?"selected":""%>>giao hàng thành công</option>
					<option value=5 <%=(order.getStatusOrder().getId())==5?"selected":""%>>hủy</option>
				</select>
				<button type="submit" class="btn btn-primary"
					style="margin-top: 1rem;">Xác nhận</button>
			</form>
		</div>
	</div>
</body>

</html>