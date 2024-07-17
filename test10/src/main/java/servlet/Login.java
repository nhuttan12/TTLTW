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
import log.WarnMessage;
import model.Cart;
import model.Logging;
import model.User;
import utils.Encryption;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBCart;
import database.DBUser;
import database.connectionDB;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String USER_NAME = req.getParameter("taikhoan");
		String PASSWORD = req.getParameter("matkhau");
		System.out.println(USER_NAME + PASSWORD);
		DBUser l = new DBUser();
		User a = new User();
		a = l.checkUSER(USER_NAME, Encryption.mahoaPass(PASSWORD));
		String erro;
		Logging logging = null;

		if (a == null) {
			erro = "Tài khoản hoặc mật khẩu sai!";
//			System.out.println(a.getId());
			logging=new Logging(LevelLog.WARN.name(), WarnMessage.DANG_NHAP_THAT_BAI__THONG_TIN_DANG_NHAP_SAI.name());
			req.setAttribute("erro", erro);
			req.getRequestDispatcher("login.jsp").forward(req, resp);

		} else {
			if (a.getStatus() == 2) {
				erro = "Tài khoản đã bị khóa";
				logging=new Logging(LevelLog.WARN.name(), WarnMessage.DANG_NHAP_THAT_BAI__TAI_KHOAN_BI_KHOA.name());
				req.setAttribute("erro", erro);
				req.getRequestDispatcher("login.jsp").forward(req, resp);

			} else {
//				System.out.println(a.getId());
				HttpSession session = req.getSession();
				session.setAttribute("user", a);
				session.setMaxInactiveInterval(20);
				System.out.println("login thanh cong");
				logging = new Logging(LevelLog.INFO.name(),InforMessage.DANG_NHAP_THANH_CONG.name());
				RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
				dispatcher.forward(req, resp);
			}

	}
		MyLog.insertLog(logging,req);
	}
}

//	public static void main(String[] args) throws SQLException {
//		DBUser l = new DBUser();
//		User a = l.checkUSER("PERSON11", "123");
//		System.out.println(a.getUserName());
//
//	}
