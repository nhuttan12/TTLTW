<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@page import="model.Item"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa sản phẩm</title>
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
        width: 50%;
    }

    .container {
        padding-top: 2rem;
        padding-bottom: 2rem;
    }
</style>

<body>
    <div class="container">
        <div class="main m-auto">
            <h1 class="text-center">Chỉnh sửa sản phẩm</h1>
            <form action="edit" method="post" enctype="multipart/form-data">
	            <%
	            	Item item = (Item) request.getAttribute("item");
	            %>
                <div class="form-group">
                    <label for="product-name">Mã sản phẩm</label>
                    <input type="text" name="id" class="form-control" id="product-name" readonly value="<%=item.getId()%>">
                </div>
                <div class="form-group">
                    <label for="category">Loại sản phẩm</label>
                    <select name="category" class="form-control" id="category">
	                    <%
	                    List<Category> categories= (List<Category>) request.getAttribute("category");
                    	if(categories!=null){
                    		for(Category c : categories){
	                    %>
                        <option value=<%=c.getId() %> <%=(c.getId()==item.getCategory().getId())?"selected":"" %>><%=c.getCategoryName() %></option>
                        <%
                    		}
                    	}
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="product-name">Tên sản phẩm</label>
                    <input type="text" name="name" class="form-control" id="product-name" value="<%=item.getName()%>">
                </div>
                <div class="form-group">
                    <label for="product-price">Giá bán sản phẩm</label>
                    <input name="price" type="number" class="form-control" id="product-price" value="<%=item.getPrice()%>">
                </div>
                <div class="form-group">
                    <label for="product-discount">Giảm giá</label>
                    <input type="number" name="discount" value="0" class="form-control" id="product-discount" value="<%=item.getDiscount()%>">
                </div>
                <div class="form-group">
                    <label for="product-discription">Mô tả sản phẩm</label>
                    <input name="description" type="text" class="form-control" id="product-discription" value="<%=item.getDiscription()%>">
                </div>
                <div class="form-group">
                    <label for="hidden">Hiển thị sản phẩm</label>
                    <select name="hidden" class="form-control" id="hidden">
                        <option value=1 <%=item.getHidden()==1?"checked":"" %>>Hiển thị</option>
                        <option value=2 <%=item.getHidden()==2?"checked":"" %>>Ẩn</option>
                    </select>
                </div>
                <div class="form-group">
					<label for="product-image">Hình của sản phẩm</label> 
					<input name="image" type="file" class="form-control-file" id="product-image" accept="image/*">
				</div>
                <button type="submit" class="btn btn-primary">Chỉnh sửa</button>
            </form>
        </div>
    </div>
</body>

</html>