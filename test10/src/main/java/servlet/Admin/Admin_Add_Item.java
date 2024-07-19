package servlet.Admin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;

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
@WebServlet("/addItem")
public class Admin_Add_Item extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login").forward(req, resp);
		} else {
			DBCategory dao = new DBCategory();
			List<Category> categories = dao.getCategoryList();
			req.setAttribute("categoryList", categories);
			req.getRequestDispatcher("/add.jsp").forward(req, resp);

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
			String name = req.getParameter("product-name");
			String category = req.getParameter("category");
			int categoryId=Integer.parseInt(category);
			
			String price = req.getParameter("price");
			double salePrice=Double.parseDouble(price);
			
			String discount = req.getParameter("discount");
			double discountItem=Double.parseDouble(discount);
			
			String discription = req.getParameter("description");
			
			Part image = req.getPart("image");
			String image_name = image.getSubmittedFileName();
			System.out.println("cloudiary");
			
			// Khởi tạo một mảng byte để lưu dữ liệu từ phần tải lên
			byte[] data_image=new byte[(int) image.getSize()];
			
			//đưa dữ lieu anh vao mảng nhi phan
			image.getInputStream().read(data_image);
			System.out.println("declare");
			
			// Khởi tạo Cloudinary object
			Cloudinary cloudinary=new Cloudinary(ObjectUtils.asMap(
					"cloud_name", "dr5petyho",
                    "api_key", "178384366789557",
                    "api_secret", "ZZVpyN3dGPL-rNHIaD4qTBoklyc"));
			System.out.println("object");
			// Upload ảnh lên Cloudinary
			Map upload_image = cloudinary.uploader().upload(data_image, ObjectUtils.asMap("public_id", image_name));
			String url = (String) upload_image.get("url");
			
			DBItem dbItem = new DBItem();
			Item item=new Item();
			Category categoryItem=new Category();
			item.setCategory(categoryItem);
			item.getCategory().setId(categoryId);
			item.setName(name);
			item.setPrice(salePrice);
			item.setDiscount(discountItem);
			item.setDiscription(discription);
			item.setImageName(url);
			
			Logging logging=new Logging(LevelLog.INFO.name(), InforMessage.THEM_SAN_PHAM_THANH_CONG.name(), null, item);
			MyLog myLog = new MyLog();
			try {
				dbItem.addITEM(item);
				dbItem.addInventoryFirst(item);
				myLog.insertLog(logging, req);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			resp.sendRedirect(req.getContextPath()+"/admin?gr=item");
			
		}
	}

	public static void main(String[] args) {

	}
}
