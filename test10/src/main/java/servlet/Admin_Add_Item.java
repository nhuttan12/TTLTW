package servlet;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

import database.DBItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Item;
import model.User;
@MultipartConfig()
@WebServlet("/addItem")
public class Admin_Add_Item extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
HttpSession httpSession=req.getSession();
		
		User user=(User) httpSession.getAttribute("user");
		if(user==null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login").forward(req, resp);
		}else { 
			req.getRequestDispatcher("add.jsp").forward(req, resp);
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBItem dbItem = new DBItem();
		int id;
		String name_Item;
		double unitPrice;
		int quantityavai;
		String type;
		String image;
		
		

		try {
			id=Integer.parseInt(req.getParameter("ITEM_ID"));
			name_Item=req.getParameter("ITEM_NAME");
			unitPrice=Double.parseDouble(req.getParameter("UNITPRICE"));
			quantityavai=Integer.parseInt(req.getParameter("QUANTITY_AVAILABLE"));
			type=req.getParameter("TYPE");
			Part part =req.getPart("IMAGES");
			String fileName=Path.of(part.getSubmittedFileName()).getFileName().toString();

			part.write("D:\\Test\\test4\\test4\\src\\main\\webapp\\images\\"+fileName);

			image="images/"+fileName;

			Item item = new Item(id, name_Item, unitPrice, quantityavai, type, image);
			dbItem.addITEM(item);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resp.sendRedirect("admin");
		
	}
public static void main(String[] args) {
	
}
}
