<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa thông tin người dùng</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="images/loo6.png" />
</head>
<style>
    .main {
        padding: 2rem 4rem 2rem 4rem;
        border: 1px solid #9c9c9c;
        border-radius: 10px;
        box-shadow: 0px 5px 10px #9c9c9c;
        width: 70%;
    }

    .container {
        padding-top: 2rem;
    }
</style>

<body>
    <div class="container">
        <div class="main m-auto">
            <h1 class="text-center">Chỉnh sửa thông tin người dùng</h1>
            <%
            	User user=(User) request.getAttribute("u");
            %>
            <form action="updateStatusUser" method="post">
                <div class="form-group">
                    <label for="product-name">Mã người dùng</label> 
                    <input type="text" name="id" class="form-control" id="product-name" value="<%=user.getId() %>" readonly>
                </div>
                <div class="form-group">
                    <label for="product-price">Tên người dùng</label> 
                    <input name="customer" type="text" class="form-control" id="customer" value="<%=user.getName() %>" readonly>
                </div>
                <div class="form-group">
                    <label for="product-discount">Email</label> 
                    <input type="email" name="email" value="<%=user.getEmail() %>" class="form-control" id="total-price" readonly>
                </div>
                <div class="form-group">
                    <label for="product-discription">Tài khoản</label> 
                    <input name="username" type="text" class="form-control" value="<%=user.getUserName() %>" id="order-date" readonly>
                </div>
                <label for="role">Chức vụ</label> 
                <select name="role" class="form-control" id="role">
                	<option value="1" <%=(user.getRole())==1?"selected":""%>>Người dùng</option>
                	<option value="2" <%=(user.getRole())==2?"selected":""%>>Quản lý</option>
				</select>
                <label for="status">Trạng thái</label> 
                <select name="status" class="form-control" id="status">
					<option value="1" <%=(user.getStatus())==1?"selected":""%>>Không khoá</option>
                	<option value="2" <%=(user.getStatus())==2?"selected":""%>>Khoá</option>
				</select>
                <button type="submit" class="btn btn-primary" style="margin-top: 1rem;">Xác nhận</button>
            </form>
        </div>
    </div>
</body>

</html>