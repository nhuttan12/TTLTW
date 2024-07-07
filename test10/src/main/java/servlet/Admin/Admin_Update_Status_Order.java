package servlet.Admin;

import java.io.IOException;

import database.DBOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/updateStatus")
public class Admin_Update_Status_Order extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		HttpSession httpSession = req.getSession();
//
//		User user = (User) httpSession.getAttribute("user");
//		if (user == null) {
//			req.setAttribute("erro", "bạn phải đăng nhập !");
//			req.getRequestDispatcher("login.jsp").forward(req, resp);
//		} else {
//			int orderId;
//			DBOrder dbOrder = new DBOrder();
//			try {
//				orderId = Integer.parseInt(req.getParameter("orderId"));
//				dbOrder.updateStatusByOderId(orderId);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			req.setAttribute("gr", "spcart");
//			req.getRequestDispatcher("admin").forward(req, resp);
//		}
	}
}
