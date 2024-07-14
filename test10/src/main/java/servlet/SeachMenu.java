package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import database.DBItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Item;

@WebServlet("/searchmenu")
public class SeachMenu extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		DBItem dbItem = new DBItem();
		String search = req.getParameter("search");
		String user_id=req.getParameter("userid");
		String id="";
		if(user_id==null) {
			id="login.jsp";
		}else {
			id="cart?userId="+user_id;
		}
		try {
			
			List<Item> items = dbItem.getItemByNameSearch(search);
			PrintWriter printWriter = resp.getWriter();
			for (Item item : items) {
				printWriter.println("<div class=\"col-sm-6 col-lg-4 all pizza\">\r\n"
						+ "							<div class=\"box\">\r\n"
						+ "								<div>\r\n"
						+ "									<div class=\"img-box\">\r\n"
						+ "										<img src=\""+item.getImageName()+"\" alt=\"\">\r\n"
						+ "									</div>\r\n"
						+ "									<div class=\"detail-box\">\r\n"
						+ "										<h5>"+item.getName()+"</h5>\r\n"
						+ "										<div class=\"options\">\r\n"
						+ "											<h6>"+item.getPrice()+"VND</h6>\r\n"
						+ "											<a\r\n"
						+ "												href=\""+id+"&itemId="+item.getId()+"\">\r\n"
						+ "												+ </a>\r\n"
						+ "										</div>\r\n"
						+ "									</div>\r\n"
						+ "								</div>\r\n"
						+ "							</div>\r\n"
						+ "						</div>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
