package servlet.Admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
@WebServlet("/edit")
public class Admin_Edit_Item extends HttpServlet {
	String mess;
	DBItem dbItem;
	Item item;
	Item item2;
	int id = 0;
	String name;
	double unitPrice;// gia tren web
	int quantityAvailable;// sl ton kho
	String type;
	String imageName;
	String status = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login").forward(req, resp);
		} else {
			dbItem = new DBItem();

			item = null;
			try {
				id = Integer.parseInt(req.getParameter("id"));
				item = dbItem.getItemByID(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			req.setAttribute("item", item);
			req.getRequestDispatcher("Admin_Edit_Item.jsp").forward(req, resp);

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		try {
//			id = item.getId();
//			name = req.getParameter("ITEM_NAME");
//			type = req.getParameter("TYPE");
//			Part part = req.getPart("IMAGES");
//			String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
//			part.write("D:\\Test\\test4\\test4\\src\\main\\webapp\\images\\" + fileName);
//
//			imageName = "images/" + fileName;
//			System.out.println(imageName);
//			unitPrice = Double.parseDouble(req.getParameter("UNITPRICE"));
//			quantityAvailable = Integer.parseInt(req.getParameter("QUANTITY_AVAILABLE"));
//			item2 = new Item(id, name, unitPrice, quantityAvailable, type, imageName);
//			System.out.println(item);
//			dbItem.update(item2);
//			status = "EDIT COMPLETED";
//		} catch (Exception e) {
//			status = "EDIT FAILED";
//		}
//		req.setAttribute("gr", "item");
//		req.setAttribute("status", status);
//		req.getRequestDispatcher("admin").forward(req, resp);
	}
}
