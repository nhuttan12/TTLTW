package servlet.Admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import database.DBCategory;
import database.DBItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import log.InforMessage;
import log.LevelLog;
import log.MyLog;
import model.Category;
import model.Item;
import model.Logging;
import model.User;

@MultipartConfig()
@WebServlet("/edit")
public class Admin_Edit_Item extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login").forward(req, resp);
		} else {
			int itemId = Integer.parseInt(req.getParameter("id"));
			DBItem dbItem = new DBItem();
			DBCategory dbCategory = new DBCategory();
			List<Category> categories = dbCategory.getCategoryList();
			Item item;
			try {
				item = dbItem.getItemByID(itemId);
				req.setAttribute("item", item);
				req.setAttribute("category", categories);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.getRequestDispatcher("Admin_Edit_Item.jsp").forward(req, resp);

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login").forward(req, resp);
		} else {
			int itemId = Integer.parseInt(req.getParameter("id"));
			int categoryId = Integer.parseInt(req.getParameter("category"));
			String name = req.getParameter("name");
			double price = Double.parseDouble(req.getParameter("price"));
			double discount = Double.parseDouble(req.getParameter("discount"));
			String description = req.getParameter("description");
			int hidden = Integer.parseInt(req.getParameter("hidden"));

			Part image = req.getPart("image");
			String image_name = image.getSubmittedFileName();
			System.out.println("cloudiary");

			// Khởi tạo một mảng byte để lưu dữ liệu từ phần tải lên
			byte[] data_image = new byte[(int) image.getSize()];

			// đưa dữ lieu anh vao mảng nhi phan
			image.getInputStream().read(data_image);
			System.out.println("declare");

			// Khởi tạo Cloudinary object
			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "dr5petyho", "api_key",
					"178384366789557", "api_secret", "ZZVpyN3dGPL-rNHIaD4qTBoklyc"));
			System.out.println("object");
			// Upload ảnh lên Cloudinary
			Map upload_image = cloudinary.uploader().upload(data_image, ObjectUtils.asMap("public_id", image_name));
			String url = (String) upload_image.get("url");

			DBItem dbItem = new DBItem();
			Item oldItem = new Item();

			Item item = new Item();
			Category category = new Category();
			item.setCategory(category);

			item.setId(itemId);
			item.getCategory().setId(categoryId);
			item.setName(name);
			item.setPrice(price);
			item.setDiscount(discount);
			item.setDiscription(description);
			item.setImageName(url);
			item.setHidden(hidden);

			try {
				oldItem = dbItem.getItemByID(itemId);
				dbItem.update(item);

				Logging logging = new Logging(LevelLog.INFO.name(), InforMessage.CAP_NHAT_THONG_TIN_THANH_CONG.name(),
						oldItem, item);
				MyLog myLog = new MyLog();

				myLog.insertLog(logging, req);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		resp.sendRedirect(req.getContextPath() + "/admin?gr=item");
	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
//	}
}
