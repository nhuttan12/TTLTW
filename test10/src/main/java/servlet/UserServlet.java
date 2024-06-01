package servlet;

import java.io.IOException;

import database.DBUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
@WebServlet("/user")
public class UserServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		User a = (User) session.getAttribute("user");
		
		if(a==null) {
			req.setAttribute("erro", "bạn phải đăng nhập!");

			RequestDispatcher dispatcher = req.getRequestDispatcher("login");
			dispatcher.forward(req, resp);
		}else {
			int role =a.getRole();
	
		if(role==1) {
			req.getRequestDispatcher("user.jsp").forward(req, resp);
		}else if(role==2) {
			req.getRequestDispatcher("admin").forward(req, resp);
		}
		}
	}
}

