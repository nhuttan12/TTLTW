	package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

import database.DBCartItems;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
HttpSession httpSession=req.getSession();
		
		User user=(User) httpSession.getAttribute("user");
		if(user==null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);		}else { 
		int cartId  = Integer.parseInt(req.getParameter("cartId")) ;
		int itemId = Integer.parseInt(req.getParameter("itemId")) ;
		DBCartItems dbCartItem = new DBCartItems();
		int status;
		try {
			status = dbCartItem.deleteITEM(cartId,itemId);
			System.out.println("da xóa");
			System.out.println(itemId);

		} catch (SQLException m) {
			// TODO Auto-generated catch block
			m.printStackTrace();
		}
		String url = "http://localhost:8080/test10/cart?shoppingCartId=" + cartId;
		resp.sendRedirect(url);
//		req.getRequestDispatcher("cart").forward(req, resp);
	}
	}


}
