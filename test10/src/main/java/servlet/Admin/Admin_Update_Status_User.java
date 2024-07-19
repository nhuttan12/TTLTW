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
import log.InforMessage;
import log.LevelLog;
import log.MyLog;
import model.Logging;
import model.User;

@WebServlet("/updateStatusUser")
public class Admin_Update_Status_User extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} else {
			int userId = Integer.parseInt(req.getParameter("id"));
			DBUser dbUser = new DBUser();
			User u=new User();
			try {
				 u=dbUser.getUserByID(userId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			req.setAttribute("u", u);
			req.getRequestDispatcher("UpdateUser.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		int update=0;
		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} else {
			int userId = Integer.parseInt(req.getParameter("id"));
			int statusId = Integer.parseInt(req.getParameter("status"));
			int roleId = Integer.parseInt(req.getParameter("role"));
			
			DBUser dbUser = new DBUser();
			User u;
			try {
				u = dbUser.getUserByID(userId);
				update=dbUser.updateUser(userId, statusId, roleId);

				Logging logging=new Logging(LevelLog.INFO.name(), InforMessage.CAP_NHAT_THONG_TIN_THANH_CONG.name(), u, dbUser.getUserByID(userId));
				MyLog myLog=new MyLog();
				
				System.out.println("thay đổi trạng thái người dùng"+update);
				myLog.insertLog(logging, req);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("thay đổi trạng thái người dùng " + update);
		resp.sendRedirect(req.getContextPath() + "/admin?gr=user");
	}
}
