package servlet;

import java.io.IOException;
import java.sql.SQLException;

import database.DBUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/edituser")
public class EditUser extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} else {
		req.getRequestDispatcher("user.jsp").forward(req, resp);
			
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
