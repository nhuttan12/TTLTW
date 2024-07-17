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
            <form action="addItem" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="product-name">Mã người dùng</label> 
                    <input type="text" name="id" class="form-control" id="product-name" value="1" readonly>
                </div>
                <div class="form-group">
                    <label for="product-price">Tên khách hàng</label> 
                    <input name="customer" type="text" class="form-control" id="customer" value="Khách hàng" readonly>
                </div>
                <div class="form-group">
                    <label for="product-discount">Email</label> 
                    <input type="email" name="email" value="0" class="form-control" id="total-price" readonly>
                </div>
                <div class="form-group">
                    <label for="product-discription">Tài khoản</label> 
                    <input name="username" type="text" class="form-control" id="order-date" readonly>
                </div>
                <label for="category">Chức vụ</label> 
                <select name="category" class="form-control" id="category">
					<option value=1>1</option>
				</select>
                <label for="category">Trạng thái</label> 
                <select name="category" class="form-control" id="category">
					<option value=1>1</option>
				</select>
                <button type="submit" class="btn btn-primary" style="margin-top: 1rem;">Xác nhận</button>
            </form>
        </div>
    </div>
</body>

</html>