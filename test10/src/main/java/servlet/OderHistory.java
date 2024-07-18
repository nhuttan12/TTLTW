package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Item;
import model.Order;
import model.OrderDetail;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.DBOderDetail;
import database.DBOrder;

/**
 * Servlet implementation class OderHistory
 */
@WebServlet("/oderHistory")
public class OderHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OderHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} else {
			DBOrder order = new DBOrder();
			DBOderDetail detail = new DBOderDetail();
			List<Order> listOrder;
			try {
				listOrder = order.getOderByUserID(user.getId());
				Map<Order, List<Cart>> orderCartMap = new HashMap<>();
				Map<Order, List<Item>> orderProductMap = new HashMap<>();
				
				for (Order or : listOrder) {
					List<Cart> listCart = detail.getCartByOderID(or.getOrderId());
					orderCartMap.put(or, listCart);
					List<Item> listItem = detail.getItemByOderID(or.getOrderId());
					orderProductMap.put(or, listItem);
				}
				req.setAttribute("listCartOder", orderCartMap);
				req.setAttribute("listItemOder", orderProductMap);
//				req.setAttribute("listOder", listOrder);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		req.setAttribute("listOder", os);

			RequestDispatcher dispatcher = req.getRequestDispatcher("oderHistory.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
