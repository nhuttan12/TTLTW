package servlet.Admin;

import model.Logging;
import model.Order;
import model.OrderDetail;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import database.DBItem;
import database.DBOderDetail;
import database.DBOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import log.InforMessage;
import log.LevelLog;
import log.MyLog;

@WebServlet("/editOrder")
public class EditOrderStatus extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			request.setAttribute("erro", "bạn phải đăng nhập !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			int orderId = Integer.parseInt(request.getParameter("id"));
			DBOrder db = new DBOrder();
			Order order = db.getOrderById(orderId);
			request.setAttribute("order", order);

			request.getRequestDispatcher("UpdateStatusProduct.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			request.setAttribute("erro", "bạn phải đăng nhập !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			String date = request.getParameter("deliver-date");
			int statusId = Integer.parseInt(request.getParameter("status"));
			int orderId = Integer.parseInt(request.getParameter("order-id"));

			DBOrder db = new DBOrder();
			Order order=db.getOrderById(orderId);
			int update = db.updateOrderStatus(orderId, statusId, date);
			
			Logging logging=new Logging(LevelLog.INFO.name(), InforMessage.CAP_NHAT_THONG_TIN_THANH_CONG.name(), order, db.getOrderById(orderId));
			MyLog myLog=new MyLog();

			System.out.println("thay đổi trạng thái đơn hàng " + update);
			myLog.insertLog(logging, request);
			
			response.sendRedirect(request.getContextPath() + "/admin?gr=spcart");
		}
	}

}
