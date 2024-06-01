package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Item;
@WebServlet("/menu")
public class Menu extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		Item it = new Item();
		DBItem dbItem = new DBItem();
		List<Item> items = new ArrayList<Item>();
		if(type==null || type.equals("all")) {
			items=dbItem.getAllItem();
		}else {
			try {
				items=dbItem.getItemByType(type);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int itemPerPage=6;
		int page;//stttrang muon  hien thi
		String xpage=req.getParameter("page");
		int size=items.size();//so luong item cua he thong
		int numberOfPage=(size%itemPerPage==0)?(size/itemPerPage):((size/itemPerPage)+1);
		if(xpage==null) {
			page=1;
		}else {
			page=Integer.parseInt(xpage);
		}
		int start=(page-1)*itemPerPage;
		int end=Math.min(page*itemPerPage, size);
		List<Item> list = new ArrayList<Item>();
		list=dbItem.getItemByPage(items, start, end);
		
		
		
		
		
		
		req.setAttribute("type2", type);
		req.setAttribute("Chickenjoy", "Chickenjoy");
		req.setAttribute("Burger", "Burger");
		req.setAttribute("Noodles", "Noodles");
		req.setAttribute("Drinks", "Drinks");
		
		req.setAttribute("listItem", list);
		req.setAttribute("page", page);
		req.setAttribute("number", numberOfPage);
		req.getRequestDispatcher("menu.jsp").forward(req, resp);
	
	}
}
