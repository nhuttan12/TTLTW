package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Item;
import model.Order;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import database.DBCartItems;
import database.DBOrder;

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet("/shopping")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User a = (User) session.getAttribute("user");
		if (a == null) {
			req.setAttribute("erro", "bạn phải đăng nhập!");

			RequestDispatcher dispatcher = req.getRequestDispatcher("login");
			dispatcher.forward(req, resp);
		} else {
			String xcartId = req.getParameter("shoppingCartId");
			int cartId = 0;
			String xitemId = req.getParameter("itemId");

			DBCartItems dbCartItem = new DBCartItems();
			int status;
			try {
				int itemId = Integer.parseInt(xitemId);
				cartId = Integer.parseInt(xcartId);
				status = dbCartItem.addITEM(cartId, itemId, 1);


			} catch (SQLException m) {
				// TODO Auto-generated catch block
				m.printStackTrace();
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher("menu");
			dispatcher.forward(req, resp);

		}
	}

}
