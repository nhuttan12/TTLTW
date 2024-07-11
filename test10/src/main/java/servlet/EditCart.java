package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

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
		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} else {
			
			// đã đăng nhập thành công
			String action = req.getParameter("action");
		    int cartID = Integer.parseInt(req.getParameter("cartID"));
		    int itemId = Integer.parseInt(req.getParameter("itemId"));
			
			if (action.equals("increase")) {
		        // Increase quantity logic
		        // Example: Assume listCart is a list of CartItem objects
				try {
					dbCart.addITEM(user.getId(), itemId, 1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    } else if (action.equals("decrease")) {
		        // Decrease quantity logic
		        // Example: Assume listCart is a list of CartItem objects
		    	try {
					dbCart.subITEM(user.getId(), itemId, 1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    } else if (action.equals("remove")) {
		        // Remove item logic
		        // Example: Assume listCart is a list of CartItem objects
		        try {
					dbCart.deleteCartByCartID(cartID);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }

//			try {
//				cartID=Integer.parseInt(cartid);
//				dbCart.deleteCartByCartID(cartID);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
			RequestDispatcher dispatcher = req.getRequestDispatcher("shoppingcart");
			dispatcher.forward(req, resp);
		}
	}

}
