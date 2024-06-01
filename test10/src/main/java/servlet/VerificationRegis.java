package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ShoppingCart;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

import database.DBShoppingCart;
import database.DBUser;

/**
 * Servlet implementation class VerificationRegis
 */
@WebServlet("/verificationRegis")
public class VerificationRegis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificationRegis() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.sendRedirect("verificationRegis.jsp");
    	
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession httpSession =req.getSession();
		String verification = null;

    	try {
    		verification = req.getParameter("verification");

		} catch (Exception e) {
			resp.sendRedirect("verificationRegis.jsp");
		}

		String USER_NAME = (String) httpSession.getAttribute("USER_NAME");
		String PASSWORD = (String) httpSession.getAttribute("PASSWORD");
		String NAME = (String) httpSession.getAttribute("NAME");
		String PHONE = (String) httpSession.getAttribute("PHONE");
		String GENDER = (String) httpSession.getAttribute("GENDER");
		String EMAIL = (String) httpSession.getAttribute("EMAIL");
		String ver = (String) httpSession.getAttribute("ver");
//		System.out.println("pass moi " +req.getAttribute("newpas"));
//		System.out.println("ver moi " +ver);

		
		if(verification.equals(ver)) {
			DBUser l = new DBUser();
	   		DBShoppingCart cart = new DBShoppingCart();
			try {
   				User a = new User(USER_NAME, PASSWORD, NAME, PHONE, GENDER,EMAIL);
				l.addUSER(a);
				a.setId(l.getUserId(a));
				a.setRole(1);
				a.setStatus(1);
				System.out.println(l.getUserId(a));
				ShoppingCart s = new ShoppingCart(a);
				cart.addShoppingCart(s);
				s.setId(cart.getCartId(a.getId()));
				l.updateCart(a, s.getId());
	   			httpSession.setAttribute("user", a);
	   			httpSession.setAttribute("cart", s);
	   			RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
	   			dispatcher.forward(req, resp);
	   			//req.getRequestDispatcher("login.jsp").forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}else {
			 System.out.println("ma khong dung");
			 RequestDispatcher dispatcher = req.getRequestDispatcher("verification.jsp");
				dispatcher.forward(req, resp);
		}
    }

}
