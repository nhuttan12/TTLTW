package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Item;
import model.Order;
import model.Cart;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//import database.DBCartItems;
import database.DBContact;
import database.DBOrder;
//import database.DBShoppingCart;
import database.DBUser;
import email.ClientSendEmail;

/**
 * Servlet implementation class SendMail
 */
@WebServlet("/mailcontact")
public class Contact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Contact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String message = req.getParameter("message");
		model.Contact con = new model.Contact(name, email, message);
		DBContact db = new DBContact();
		try {
			db.addContact(con);
			String tb = "da gui!!!!!";
//			System.out.println(a.getId());
			req.setAttribute("tb", tb);
			System.out.println("gui dc");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getRequestDispatcher("contact.jsp").forward(req, resp);
			
		
	}

	

}
