<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm sản phẩm</title>
<%-- Bootstrap --%>
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
	margin-top: 1rem;
	width: 50%;
	padding: 2rem 2rem 3rem 2rem;
	border: 1px solid #9c9c9c;
	border-radius: 10px;
	box-shadow: 0px 5px 10px #9c9c9c;
	width: 50%;
}
</style>
<body>
	<div class="main m-auto">
		<h1 class="text-center">Thêm sản phẩm</h1>
		<form action="edit" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="category">Loại sản phẩm</label> <select name="category"
					class="form-control" id="category">
					<%
					List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
					if (categoryList != null) {
						for (Category category : categoryList) {
					%>
					<option value=<%=category.getId()%>><%=category.getCategoryName()%></option>
					<%
					}
					}
					%>
				</select>
			</div>
			<div class="form-group">
				<label for="product-name">Tên sản phẩm</label> <input type="text"
					name="product-name" class="form-control" id="product-name"
					placeholder="Nhập tên sản phẩm ở đây" required=""
					oninvalid="this.setCustomValidity('Vui lòng điền tên sản phẩm')">
			</div>
			<div class="form-group">
				<label for="product-price">Giá bán sản phẩm</label> <input
					name="price" type="number" class="form-control" id="product-price"
					placeholder="Nhập giá bán sản phẩm ở đây" required=""
					oninvalid="this.setCustomValidity('Vui lòng điền giá bán sản phẩm')">
			</div>
			<div class="form-group">
				<label for="product-discount">Giảm giá</label> <input type="number"
					name="discount" value="0" class="form-control"
					id="product-discount" placeholder="Nhập giá thành sản phẩm ở đây">
			</div>
			<div class="form-group">
				<label for="product-discription">Mô tả sản phẩm</label> <input
					name="description" type="text" class="form-control"
					id="product-discription" placeholder="Nhập mô tả sản phẩm ở đây"
					required=""
					oninvalid="this.setCustomValidity('Vui lòng điền mô tả sản phẩm')">
			</div>
			<div class="form-group">
				<label for="product-image">Hình của sản phẩm</label> <input
					name="image" type="file" class="form-control-file"
					id="product-image" required="" accept="image/*"
					oninvalid="this.setCustomValidity('Vui lòng thêm hình ảnh cho sản phẩm')">
			</div>
			<button type="submit" class="btn btn-primary">Thêm sản phẩm</button>
		</form>
	</div>
</body>
</html>