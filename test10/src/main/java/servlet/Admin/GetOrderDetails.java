package servlet.Admin;

import java.io.IOException;
import java.util.List;

import database.DBOderDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderDetail;
import model.User;

@WebServlet("/getOrderDetail")
public class GetOrderDetails extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			request.setAttribute("erro", "bạn phải đăng nhập !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			int orderId = Integer.parseInt(request.getParameter("id"));
			DBOderDetail db = new DBOderDetail();
			List<OrderDetail> od = db.getOrderDetails(orderId);
			request.setAttribute("order_details", od);
			request.getRequestDispatcher("order_detail.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
