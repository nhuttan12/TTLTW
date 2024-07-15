package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import utils.Encryption;

import java.io.IOException;
import java.sql.SQLException;

import database.DBUser;
import email.ServerSendMail;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/changePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
HttpSession httpSession=req.getSession();
		
		User user=(User) httpSession.getAttribute("user");
		if(user==null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);		}
		else { 
		String oldpas=req.getParameter("oldPassword");
		String newpas =req.getParameter("newPassword");
		String pre_newpas=req.getParameter("prenewPassword");

		if(user.getPassword().equals(Encryption.mahoaPass(oldpas)) && newpas != null&&newpas.equals(pre_newpas) ) {
			ServerSendMail m = new ServerSendMail();
			String email = user.getEmail();
			String ver = m.createVerification();
			m.setTo(email);
			m.setMessage(ver);
			if(m.sendEmail()) {
				HttpSession session = req.getSession();
				session.setAttribute("newpas", newpas);
				session.setAttribute("ver", ver);
				session.setAttribute("oldpass", oldpas);
				RequestDispatcher dispatcher = req.getRequestDispatcher("verificationPass.jsp");
				dispatcher.forward(req, resp);
			}else {
				System.out.println("ma khong dung");
			}
			
		}else {
			 System.out.println("kiem tra lai thong tin");
			 req.setAttribute("erro", "Thông tin chưa đúng");
			 RequestDispatcher dispatcher = req.getRequestDispatcher("changePassword.jsp");
			dispatcher.forward(req, resp);
		}
		
	}
		
		
	}

}
