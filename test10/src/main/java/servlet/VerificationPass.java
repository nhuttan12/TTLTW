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
import model.Logging;
import model.User;
import utils.Encryption;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

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
		HttpSession httpSession = req.getSession();
		Logging logging =null;
		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} else {
			String verification = req.getParameter("verification");

			int uid = user.getId();
			System.out.println("id user " + uid);

			String newpas = (String) httpSession.getAttribute("newpas");
			String ver = (String) httpSession.getAttribute("ver");
			String oldpass=(String)httpSession.getAttribute("oldpass");
			System.out.println("pass moi " + req.getAttribute("newpas"));
			System.out.println("ver moi " + ver);

			if (verification.equals(ver)) {
				DBUser db = new DBUser();
				try{
					db.updatePas(uid, Encryption.mahoaPass(newpas));
					System.out.println("da update pas");
					logging=new Logging(LevelLog.INFO.name(),InforMessage.THAY_DOI_MAT_KHAU_THANH_CONG.name(),oldpass,newpas);
					req.setAttribute("erro", "Thay đổi mật khẩu thành công");
					RequestDispatcher dispatcher = req.getRequestDispatcher("user");
					dispatcher.forward(req, resp);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				System.out.println("ma khong dung");
				req.setAttribute("erro", "Mã không đúng");
				logging=new Logging(LevelLog.ERROR.name(),ErrorMessage.THAY_DOI_MAT_KHAU_THAT_BAI_MA_XAC_THUC_KHONG_DUNG.name(),oldpass,newpas);
				RequestDispatcher dispatcher = req.getRequestDispatcher("verificationPass.jsp");
				dispatcher.forward(req, resp);
			}
		}
		MyLog.insertLog(logging, req);
	}
}
