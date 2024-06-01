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
public class EditUser extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
HttpSession httpSession=req.getSession();
		
		User user=(User) httpSession.getAttribute("user");
		if(user==null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);		}else { 
		String name=req.getParameter("fullname");
		String phone=req.getParameter("phone");
		String gender=req.getParameter("gender");
		String email=req.getParameter("email");
		int id=user.getId();
		User a;
		DBUser dbUser =new DBUser();
		try {
			dbUser.update1(id, name, phone,gender,email);
			httpSession.removeAttribute("user");
			a=dbUser.getUserByID(id);
			httpSession.setAttribute("user", a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("user");
		dispatcher.forward(req, resp);
	}
	}

}
