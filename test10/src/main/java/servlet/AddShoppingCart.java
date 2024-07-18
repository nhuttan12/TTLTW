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
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import database.DBCart;
import database.DBItem;
import database.DBOrder;

/**
 * Servlet implementation class AddShoppingCart
 */
@WebServlet("/cart")
public class AddShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		JsonObject jsonResponse = new JsonObject();

		User a = (User) session.getAttribute("user");
		if (a == null) {
			req.setAttribute("erro", "bạn phải đăng nhập!");
			RequestDispatcher dispatcher = req.getRequestDispatcher("login");
			dispatcher.forward(req, resp);
		} else {
			int itemId = 0;
			int userId = a.getId();

			DBCart dbCart = new DBCart();
			List<Cart> cart;
			try {
				itemId = Integer.parseInt(req.getParameter("itemId"));
				dbCart.addITEM(userId, itemId, 1);
				cart = dbCart.getListCartByUserIDForCart(userId);
				req.setAttribute("listAll", cart);
				jsonResponse.addProperty("success", true);
				jsonResponse.addProperty("message", "Sản phẩm đã được thêm vào giỏ hàng!");
			} catch (SQLException m) {
				// TODO Auto-generated catch block
				jsonResponse.addProperty("success", false);
				jsonResponse.addProperty("message", "Đã xảy ra lỗi khi thêm sản phẩm vào giỏ hàng.");
				m.printStackTrace();
			}
			out.write(jsonResponse.toString());
//			RequestDispatcher dispatcher = req.getRequestDispatcher("menu?type=0");
//			dispatcher.forward(req, resp);
		}

	}

	public static void main(String[] args) {
		DBItem dbItem = new DBItem();
		DBCart dbCart = new DBCart();
		try {
			dbCart.addITEM(3, 4, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("không thêm được");
			e.printStackTrace();
		}
		List<Item> items = dbItem.getAllItem();
		for (Item item : items) {
			System.out.println(item.getPrice());
		}

	}

}
