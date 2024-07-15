package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.internet.InternetAddress;

import database.DBCart;
import database.DBUser;
import email.ServerSendMail;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	DBUser l = new DBUser();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String USER_NAME = req.getParameter("tentaikhoan");
		String PASSWORD = req.getParameter("matkhau");
		String NAME = req.getParameter("hoten");
		String PHONE = req.getParameter("sodienthoai");
		String GENDER = req.getParameter("gioitinh");
		String EMAIL = req.getParameter("email");

		System.out.println(GENDER);
		
		DBCart cart = new DBCart();
//   		System.out.println(a.getUserName());
		if (checkUser(USER_NAME) == null && checkPass(PASSWORD) == null && checkPhone(PHONE) == null
				&& checkEmail(EMAIL) == null) {
			if (l.checkUSERByEmail(EMAIL) == null) {
				ServerSendMail m = new ServerSendMail();
				String ver = m.createVerification();
				m.setTo(EMAIL);
				m.setMessage(ver);
				if (m.sendEmail()) {
					HttpSession session = req.getSession();
					session.setAttribute("USER_NAME", USER_NAME);
					session.setAttribute("PASSWORD", PASSWORD);
					if (NAME == null) {
						session.setAttribute("NAME", NAME);
					}else {
						session.setAttribute("NAME", USER_NAME);
					}
					session.setAttribute("PHONE", PHONE);
					session.setAttribute("GENDER", GENDER);
					session.setAttribute("EMAIL", EMAIL);
					session.setAttribute("ver", ver);

					RequestDispatcher dispatcher = req.getRequestDispatcher("verificationRegis.jsp");
					dispatcher.forward(req, resp);
				}
//   			RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
//			dispatcher.forward(req, resp);

			} else {

				String erro = "Email này đã được sử dụng để đăng ký trước đó";
				req.setAttribute("erro", erro);
				req.getRequestDispatcher("register.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("er", true);
			if (checkUser(USER_NAME) != null) {
				req.setAttribute("us", checkUser(USER_NAME));
//				System.out.println("mmmmmmmmmmmmmmmmmmm" + checkUser(USER_NAME));
			}
			if (checkPass(PASSWORD) != null) {
				req.setAttribute("pas", checkPass(PASSWORD));
			}
			if (checkPhone(PHONE) != null) {
				req.setAttribute("phone", checkPhone(PHONE));
			}
			if (checkEmail(EMAIL) != null) {
				req.setAttribute("mail", checkEmail(EMAIL));
			}
			req.setAttribute("username", USER_NAME);
			req.setAttribute("PASSWORD", PASSWORD);
			req.setAttribute("PHONE", PHONE);
			req.setAttribute("EMAIL", EMAIL);
			req.setAttribute("NAME", NAME);
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		}
	}

	public String checkUser(String usn) {
		String status = null;
		if (usn == null) {
			status = "không được để trống tên đăng nhập";
		} else
			try {
				if(l.getUserByUserName(usn) != null) {
					
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
