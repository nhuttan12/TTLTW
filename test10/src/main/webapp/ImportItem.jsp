<!DOCTYPE html>
<%@page import="model.User"%>
<%@page import="model.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
    <meta charset="UTF-8">
    <title>Nhập thêm số lượng</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="shortcut icon" href="images/loo6.png" />
</head>
<style>
    .main {
        padding: 2rem 4rem 2rem 4rem;
        border: 1px solid #9c9c9c;
        border-radius: 10px;
        box-shadow: 0px 5px 10px #9c9c9c;
        width: 70%;
        overflow-y: auto;
    }

    .container {
        padding-top: 2rem;
        padding-bottom: 2rem;
    }

    form div div {
        display: flex;
        align-items: center;
        gap: 1rem;
        padding-top: 1rem
    }

    #import-quantity {
        width: 30%;
    }
    
    form div label{
        width: 30%;
    }
</style>

<body>
    <div class="container">
        <div class="main m-auto">
            <h1 class="text-center">Nhập thêm số lượng</h1>
            <form action="importItem" method="post">
                <div class="form-group">
	                <%
	                List<Item>items=(List<Item>)request.getAttribute("items");
	                for(int i=1; i<6;i++){
	                %>
	                <div>
	                    <select name="product-<%=i%>" class="form-control" id="product">
			                <%
			                if(items!=null){
			                	for(Item item : items){
			                %>
	                        <option value="<%=item.getId()%>"><%=item.getName() %></option>
	                        <%
			                	}
			                }
	                        %>
	                    </select>
	                    <input name="quantity-<%=i%>" value="0" type="number" class="form-control">
                    </div>
                    <%
                	}
                    %>
                </div>
                <div class="form-group">
                    <label for="importer">Người nhập: </label>
                    <select name="importer" class="form-control" id="importer">
		                <%
		                List<User>users=(List<User>)request.getAttribute("users");
		                if(users != null){
			                for(User u : users){
		                %>
                        <option value="<%=u.getId() %>"><%=u.getName() %></option>
                        <%
		                	}
		                }
                        %>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Thêm số lượng</button>
            </form>
        </div>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
    crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
    crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
    crossorigin="anonymous"></script>

</html>