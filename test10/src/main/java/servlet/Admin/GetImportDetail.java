package servlet.Admin;

import java.io.IOException;
import java.util.List;

import database.DBImport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ImportDetail;
import model.User;

@WebServlet("/getImportDetails")
public class GetImportDetail extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			request.setAttribute("erro", "bạn phải đăng nhập !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			int importId = Integer.parseInt(request.getParameter("id"));
			DBImport db = new DBImport();
			List<ImportDetail> importDetails = db.getImportsDetailById(importId);
			request.setAttribute("importDetails", importDetails);
			request.getRequestDispatcher("ImportDetail.jsp").forward(request, response);
		}
	}
}
