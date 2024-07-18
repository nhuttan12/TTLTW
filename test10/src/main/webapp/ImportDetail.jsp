<%@page import="model.ImportDetail"%>
<%@page import="model.OrderDetail"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết đơn hàng</title>
    <!-- Jquery -->
    <script src="https://code.jquery.com/jquery-3.7.1.js"
        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

    <!-- boot strap -->
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

    <!-- data table -->
    <link rel="stylesheet" href="https://cdn.datatables.net/2.0.8/css/dataTables.bootstrap5.min.css" />
    <script src="https://cdn.datatables.net/2.0.8/js/dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/2.0.8/js/dataTables.bootstrap5.min.js"></script>


	<!-- favicon -->
	<link rel="shortcut icon" href="images/loo6.png" />

    <!-- font awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    
    <script type="text/javascript">
        $(document).ready(function () {
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
                    lengthMenu: 'Hiển thị _MENU_ sản phẩm mỗi trang',
                },
            })
        });
    </script>
    <style>
        .main {
            width: 100%;
        }

        .container {
            padding-top: 2rem;
            width: 70%;
            margin: auto;
        }

        .table {
        	overflow: auto;
            padding: 1rem;
            padding-left: 2rem;
            padding-right: 2rem;
            box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
            border-radius: 10px;
            max-height: 600px;
        }
        #order-table_wrapper{
        }
    </style>
</head>
<div class="main">
    <div class="container">
        <div class="table">
            <table id="order-table" style="width: 100%;">
                <thead>
                    <tr>
                        <th>
                            Stt
                        </th>
                        <th>
                            Tên sản phẩm
                        </th>
                        <th>
                            Số lượng
                        </th>
                        <th>
                            Giá tiền
                        </th>
                        <th>
                            Tổng giá
                        </th>
                    </tr>
                </thead>
                <tbody>
	                <%
	                List<ImportDetail> importDetails = (List<ImportDetail>) request.getAttribute("importDetails");
	                if (importDetails != null) {
						for (ImportDetail id : importDetails) {
					%>
                    <tr>
                        <td><%=id.getId() %></td>
                        <td><%=id.getItem().getName() %></td>
                        <td><%=id.getQuantity() %></td>
                        <td><%=id.getItem().getPrice() %> vnđ</td>
                        <td><%=id.getPrice() %> vnđ</td>
                    </tr>
	                <%
						}
	                }
	                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<body>
</body>

</html>