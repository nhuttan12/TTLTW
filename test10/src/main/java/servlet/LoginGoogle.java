package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import login.GooglePojo;
import login.GoogleUtils;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

import database.DBUser;

/**
 * Servlet implementation class LoginGoogle
 */
@WebServlet("/loginGoogle")
public class LoginGoogle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginGoogle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
	    if (code == null || code.isEmpty()) {
	      RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
	      dis.forward(request, response);
	    } else {
	      String accessToken = GoogleUtils.getToken(code);
	      
	      GooglePojo acc = GoogleUtils.getUserInfo(accessToken);
	      
//	      System.out.println(googlePojo.getGiven_name()+"zzzzzzzzzzzzzzzzzzzzzzzzzzzz");
	      request.setAttribute("id", acc.getId());
	      request.setAttribute("name", acc.getName());
	      request.setAttribute("email", acc.getEmail());
	      
	      String erro;
			if (acc != null) {
				DBUser l = new DBUser();
				User a = new User();
				a = l.checkUSERByEmail(acc.getEmail());

				if (a == null) {
					try {
						l.addUSERForFB(acc.getName(), acc.getEmail());
						a = l.checkUSERByEmail(acc.getEmail());
						HttpSession session = request.getSession();
						session.setAttribute("user", a);
						System.out.println("login thanh cong");
						RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
						dispatcher.forward(request, response);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					if (a.getStatus() == 2) {
						erro = "Tài khoản đã bị khóa";
						request.setAttribute("erro", erro);
						request.getRequestDispatcher("login.jsp").forward(request, response);

					} else {
//						System.out.println(a.getId());
						HttpSession session = request.getSession();
						session.setAttribute("user", a);
						System.out.println("login thanh cong");
						RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
						dispatcher.forward(request, response);
					}
				}
			} else {
				erro = "Đăng nhập không thành công! vui lòng đăng nhập lại!";
//				System.out.println(a.getId());
				request.setAttribute("erro", erro);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
