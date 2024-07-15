package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
@WebServlet("/index")
public class Index extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session =req.getSession();
		//User user =(User) session.getAttribute("user");	
		String lang=(String)session.getAttribute("lang_local");
		session.setAttribute("lang_local", lang);
		RequestDispatcher re = req.getRequestDispatcher("index.jsp");
		re.forward(req, resp);

	}

}
