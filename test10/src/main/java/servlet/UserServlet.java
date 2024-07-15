package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.internet.InternetAddress;

import com.google.gson.Gson;
import com.restfb.json.Json;

import database.DBCart;
import database.DBUser;
import email.ServerSendMail;
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

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	DBUser l = new DBUser();

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
		Logging logging = null;
		HttpSession httpSession = req.getSession();
		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} else {
		String NAME = req.getParameter("hoten");
		String PHONE = req.getParameter("sodienthoai");
		String GENDER = req.getParameter("gioitinh");
		
		if (checkPhone(PHONE) == null) {
			try {
				
				User user2 = l.getUserByID(user.getId());
//				httpSession.removeAttribute("user");
//				httpSession.setAttribute("user", user2);
				User old = new User(user.getId(), NAME, PHONE, Integer.parseInt(GENDER));
				System.out.println("o"+old.toString());
				User neww = new User(user2.getId(), user2.getName(), user2.getPhone(), user2.getGender());
				System.out.println(neww.toString());
				
				Gson gson = new Gson();
				if(user.getId()==user2.getId()&&NAME.equals(user2.getName())&&PHONE.equals(user2.getPhone())&&Integer.parseInt(GENDER)==user2.getGender()) {
					req.setAttribute("erro", "");
					System.out.println("giong ne");
					req.getRequestDispatcher("user.jsp").forward(req, resp);

				}else {
					l.updateInfor(user.getId(), NAME, PHONE, GENDER);
					User user3 = l.getUserByID(user.getId());
					httpSession.removeAttribute("user");
					httpSession.setAttribute("user", user3);
					logging = new Logging(LevelLog.INFO.name(), InforMessage.CAP_NHAT_THONG_TIN_THANH_CONG.name(),
							old, neww);
					
					req.setAttribute("erro", "Cập nhật thông tin thành công");
					System.out.println("khac ne");
					req.getRequestDispatcher("user.jsp").forward(req, resp);
					MyLog.insertLog(logging, req);
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			req.setAttribute("er", true);
			if (checkPhone(PHONE) != null) {
				req.setAttribute("phone", checkPhone(PHONE));
			}
			logging = new Logging(LevelLog.ERROR.name(), ErrorMessage.CAP_NHAT_THONG_TIN_THAT_BAI.name());
			req.getRequestDispatcher("user.jsp").forward(req, resp);
			MyLog.insertLog(logging, req);
			

		}
		
		}

	}

	public String checkUser(String usn) {
		String status = null;
		if (usn == null) {
			status = "không được để trống tên đăng nhập";
		} else
			try {
				if (l.getUserByUserName(usn) != null) {

					status = "username đã tồn tại";

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return status;
	}

	public String checkPass(String pass) {
		String status = null;
		if (pass == null) {
			status = "không được để trống mật khẩu";
		} else if (pass.toCharArray().length < 6) {
			status = "mật khẩu phải chứa từ 6 ký tự trở lên";
		}
		return status;

	}

	public String checkPhone(String phone) {

		if (phone.matches("^0\\d{9}$")) {
			return null;
		}
		return "số điện thoại phải gồm 10 số từ 0-9 và bắt đầu bằng số 0";

	}

	public String checkEmail(String email) {

		try {
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			return null;
		} catch (Exception e) {
			return "Địa chỉ email không hợp lệ.";
		}

	}
}
