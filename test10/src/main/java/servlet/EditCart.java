package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.JsonObject;

import database.DBCart;

//import database.DBCartItems;

/**
 * Servlet implementation class EditCart
 */
@WebServlet("/editcart")
public class EditCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBCart dbCart = new DBCart();
		HttpSession httpSession = req.getSession();
		// cho javax
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		JsonObject jsonResponse = new JsonObject();

		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			jsonResponse.addProperty("success", false);
			jsonResponse.addProperty("message", "Bạn phải đăng nhập !");
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} else {

			// đã đăng nhập thành công
			String action = req.getParameter("action");
			int cartID = Integer.parseInt(req.getParameter("cartID"));
			int itemId = Integer.parseInt(req.getParameter("itemId"));

			try {
				if (action.equals("increase")) {
					dbCart.addITEM(user.getId(), itemId, 1);
				} else if (action.equals("decrease")) {
					dbCart.subITEM(user.getId(), itemId, 1);
				} else if (action.equals("remove")) {
					dbCart.deleteCartByCartID(cartID);
				}
				List<Cart> listCart = dbCart.getListCartByUserIDForCart(user.getId());
				// Cập nhật tổng số lượng và tổng giá của tổng order
				double tongtien_giohang = 0;
				int total_quantity = 0;
				for (Cart cart : listCart) {
					tongtien_giohang += cart.getTotalPrice();
					total_quantity += cart.getQuantity();

				}
				// Cập nhật tổng số lượng và tổng giá mỗi các sản phẩm
				int newQuantity = 0;
				double newTotalPrice = 0;
				for (Cart cart : listCart) {
					if (cart.getId() == cartID) {
						newQuantity = cart.getQuantity();
						newTotalPrice = cart.getTotalPrice();
						break;
					}
				}
				jsonResponse.addProperty("tongtienGiohang", tongtien_giohang);
				jsonResponse.addProperty("totalQuantity", total_quantity);
				if ("remove".equals(action)) {
					jsonResponse.addProperty("success", true);
					
				} else {
					jsonResponse.addProperty("success", true);
					jsonResponse.addProperty("newQuantity", newQuantity);
					jsonResponse.addProperty("newTotalPrice", newTotalPrice);
					jsonResponse.addProperty("message", "");
				}
				
			} catch (SQLException e) {
				jsonResponse.addProperty("success", false);
				jsonResponse.addProperty("message", "Đã xảy ra lỗi khi xử lý yêu cầu.");
				e.printStackTrace();
			}

//			RequestDispatcher dispatcher = req.getRequestDispatcher("shoppingcart");
//			dispatcher.forward(req, resp);
		}
		out.write(jsonResponse.toString());
	}

}
