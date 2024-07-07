package servlet.Admin;

import java.io.IOException;
import java.sql.SQLException;

import database.DBUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
@WebServlet("/updateStatusUser")
public class Admin_Update_Status_User extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession httpSession=req.getSession();
	
	User user=(User) httpSession.getAttribute("user");
	if(user==null) {
		req.setAttribute("erro", "bạn phải đăng nhập !");
		req.getRequestDispatcher("login.jsp").forward(req, resp);	}else { 
	String gr=req.getParameter("gr");
	int userId=Integer.parseInt(req.getParameter("userId"));
	DBUser dbUser =new DBUser();
	try {
		dbUser.updateByStatus(userId);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	req.setAttribute("gr", gr);
	req.getRequestDispatcher("admin").forward(req, resp);
}
}
}
