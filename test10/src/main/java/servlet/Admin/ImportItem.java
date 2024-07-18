package servlet.Admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import database.DBImport;
import database.DBItem;
import database.DBUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import log.InforMessage;
import log.LevelLog;
import log.MyLog;
import model.ImportDetail;
import model.Inventory;
import model.Item;
import model.Logging;
import model.User;

@WebServlet("/importItem")
public class ImportItem extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			request.setAttribute("erro", "bạn phải đăng nhập !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			DBItem dbItem=new DBItem();
			DBUser dbUser=new DBUser();
			List<User>users=dbUser.getModAndAdmin();
			List<Item>items=dbItem.getAllItemName();
			request.setAttribute("items", items);
			request.setAttribute("users", users);
			request.getRequestDispatcher("ImportItem.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			request.setAttribute("erro", "bạn phải đăng nhập !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			DBItem dbItem=new DBItem();
			DBImport dbImport=new DBImport();
			
			//Lấy thông tin người chịu trách nhiệm khâu nhập hàng
			int importerId=Integer.parseInt(request.getParameter("importer"));
			
			//Lấy mã và số lượng của sản phẩm 1 
			int inventoryId1=Integer.parseInt(request.getParameter("product-1"));
			int quantity1=Integer.parseInt(request.getParameter("quantity-1"));
			
			//Lấy mã và số lượng của sản phẩm 2
			int inventoryId2=Integer.parseInt(request.getParameter("product-2"));
			int quantity2=Integer.parseInt(request.getParameter("quantity-2"));
			
			//Lấy mã và số lượng của sản phẩm 3
			int inventoryId3=Integer.parseInt(request.getParameter("product-3"));
			int quantity3=Integer.parseInt(request.getParameter("quantity-3"));
			
			//Lấy mã và số lượng của sản phẩm 4
			int inventoryId4=Integer.parseInt(request.getParameter("product-4"));
			int quantity4=Integer.parseInt(request.getParameter("quantity-4"));
			
			//Lấy mã và số lượng của sản phẩm 5
			int inventoryId5=Integer.parseInt(request.getParameter("product-5"));
			int quantity5=Integer.parseInt(request.getParameter("quantity-5"));
			
			//Lấy dữ liệu ban đầu
			Inventory inventory1 = dbItem.getInventoryByInvenID(inventoryId1);
			Inventory inventory2 = dbItem.getInventoryByInvenID(inventoryId2);
			Inventory inventory3 = dbItem.getInventoryByInvenID(inventoryId3);
			Inventory inventory4 = dbItem.getInventoryByInvenID(inventoryId4);
			Inventory inventory5 = dbItem.getInventoryByInvenID(inventoryId5);
			
			try {
				dbItem.updateInventory(inventoryId1, quantity1);
				dbItem.updateInventory(inventoryId2, quantity2);
				dbItem.updateInventory(inventoryId3, quantity3);
				dbItem.updateInventory(inventoryId4, quantity4);
				dbItem.updateInventory(inventoryId5, quantity5);
				
				model.ImportItem importItem=new model.ImportItem();
				User u = new User();
				importItem.setUser(u);
				importItem.getUser().setId(importerId);
				dbImport.addImportItem(importItem);
				
				ImportDetail importDetail = new ImportDetail();
				Item item=new Item();
				importDetail.setItem(item);
				importDetail.getItem().setId(inventoryId1);
				importDetail.setQuantity(quantity1);
				dbImport.addToImportDetail(importDetail);
				
				importDetail.getItem().setId(inventoryId2);
				importDetail.setQuantity(quantity2);
				dbImport.addToImportDetail(importDetail);

				importDetail.getItem().setId(inventoryId3);
				importDetail.setQuantity(quantity3);
				dbImport.addToImportDetail(importDetail);
				
				importDetail.getItem().setId(inventoryId4);
				importDetail.setQuantity(quantity4);
				dbImport.addToImportDetail(importDetail);
				
				importDetail.getItem().setId(inventoryId5);
				importDetail.setQuantity(quantity5);
				dbImport.addToImportDetail(importDetail);
				
				//Lấy dữ liệu sau khi đã chỉnh sửa
				
				MyLog myLog = new MyLog();
				Logging logging1=new Logging(LevelLog.INFO.name(), InforMessage.CAP_NHAT_THONG_TIN_THANH_CONG.name(), inventory1 , dbItem.getInventoryByInvenID(inventoryId1));
				Logging logging2=new Logging(LevelLog.INFO.name(), InforMessage.CAP_NHAT_THONG_TIN_THANH_CONG.name(), inventory2 , dbItem.getInventoryByInvenID(inventoryId2));
				Logging logging3=new Logging(LevelLog.INFO.name(), InforMessage.CAP_NHAT_THONG_TIN_THANH_CONG.name(), inventory3 , dbItem.getInventoryByInvenID(inventoryId3));
				Logging logging4=new Logging(LevelLog.INFO.name(), InforMessage.CAP_NHAT_THONG_TIN_THANH_CONG.name(), inventory4 , dbItem.getInventoryByInvenID(inventoryId4));
				Logging logging5=new Logging(LevelLog.INFO.name(), InforMessage.CAP_NHAT_THONG_TIN_THANH_CONG.name(), inventory5 , dbItem.getInventoryByInvenID(inventoryId5));
				
				myLog.insertLog(logging1, request);
				myLog.insertLog(logging2, request);
				myLog.insertLog(logging3, request);
				myLog.insertLog(logging4, request);
				myLog.insertLog(logging5, request);

				response.sendRedirect(request.getContextPath()+"/admin?gr=importHistory");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
