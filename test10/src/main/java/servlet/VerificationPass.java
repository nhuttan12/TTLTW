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

import database.DBUser;
import email.ServerSendMail;

/**
 * Servlet implementation class Verification
 */
@WebServlet("/verificationPass")
public class VerificationPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificationPass() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
HttpSession httpSession=req.getSession();
		
		User user=(User) httpSession.getAttribute("user");
		if(user==null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);		}else {     	
		String verification = req.getParameter("verification");

		int uid = user.getId();
		System.out.println("id user " +uid);
		
		
		String newpas = (String) httpSession.getAttribute("newpas");
		String ver = (String) httpSession.getAttribute("ver");

		System.out.println("pass moi " +req.getAttribute("newpas"));
		System.out.println("ver moi " +ver);

		if(verification.equals(ver)) {
			DBUser db = new DBUser();
			try {
				db.updatePas(uid, newpas);
				System.out.println("da update pas");
				RequestDispatcher dispatcher = req.getRequestDispatcher("index");
				dispatcher.forward(req, resp);
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
}
