package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import log.ErrorMessage;
import log.InforMessage;
import log.LevelLog;
import log.MyLog;
import model.Cart;
import model.Logging;
import model.User;
import utils.Encryption;

import java.io.IOException;
import java.sql.SQLException;

import database.DBCart;
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
    	Logging logging = null;
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
		String GENDER1 = (String) httpSession.getAttribute("GENDER");
		int GENDER = Integer.parseInt(GENDER1);
		String EMAIL = (String) httpSession.getAttribute("EMAIL");
		String ver = (String) httpSession.getAttribute("ver");
//		System.out.println("pass moi " +req.getAttribute("newpas"));
//		System.out.println("ver moi " +ver);

		
		if(verification.equals(ver)) {
			DBUser l = new DBUser();
	   		DBCart cart = new DBCart();
			try {
			
   				User a = new User(USER_NAME, Encryption.mahoaPass(PASSWORD), NAME, PHONE, GENDER,EMAIL);
   				System.out.println("aaaaaaaaaaaaaaaaaaaaaa" + a.getName());
				l.addUSER(a);
				a.setId(l.getUserId(a));
				a.setRole(1);
				a.setStatus(1);
				System.out.println(l.getUserId(a));
				req.setAttribute("erro", "Đăng Ký Thành Công");
				logging=new Logging(LevelLog.INFO.name(),InforMessage.DANG_KY_TAI_KHOAN_THANH_CONG.name());
	   			RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
	   			dispatcher.forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			 System.out.println("ma khong dung");
			 logging=new Logging(LevelLog.ERROR.name(),ErrorMessage.DANG_KY_TAI_KHOAN_THAT_BAI__MA_XAC_THUC_KHONG_DUNG.name());
			 RequestDispatcher dispatcher = req.getRequestDispatcher("verificationRegis.jsp");
			dispatcher.forward(req, resp);
		}
		MyLog.insertLog(logging, req);
    }

}
