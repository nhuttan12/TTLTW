package servlet.Admin;

import java.io.IOException;

import database.DBItem;
import database.DBOrder;
import database.DBUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Item;
import model.User;

@WebServlet("/delete")
public class Admin_Delete extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		HttpSession httpSession = req.getSession();
//
//		User user = (User) httpSession.getAttribute("user");
//		if (user == null) {
//			req.setAttribute("erro", "bạn phải đăng nhập !");
//			req.getRequestDispatcher("login").forward(req, resp);
//		} else {
//			String gr = req.getParameter("gr");
//			int orderId;
//			int id;
//			int userId;
//			DBItem dbItem = new DBItem();
//			DBOrder dbOrder = new DBOrder();
//			DBUser dbUser = new DBUser();
//			try {
//				id = Integer.parseInt(req.getParameter("id"));
//				// item=dbItem.getItemByID(id);
//				dbItem.deleteITEM(id);
//				System.out.println("xoaitem");
//			} catch (Exception e) {
//				try {
//					orderId = Integer.parseInt(req.getParameter("orderId"));
//					dbOrder.deleteOrder(orderId);
//					System.out.println("xoaoder");
//				} catch (Exception e2) {
//					try {
//						userId = Integer.parseInt(req.getParameter("userId"));
//						dbUser.deleteUSERByID(userId);
//					} catch (Exception e3) {
//						// TODO: handle exception
//					}
//				}
//			}
//			req.setAttribute("gr", gr);
//			req.getRequestDispatcher("admin").forward(req, resp);
//		}
	}
}
