package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBCategory;
import database.DBItem;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Item;
@WebServlet("/menu")
public class Menu extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		int id_type=Integer.parseInt(type);
		Item it = new Item();
		DBItem dbItem = new DBItem();
		DBCategory dbCategory = new DBCategory();
		List<Item> items = new ArrayList<Item>();
		if( id_type==0) {
			items=dbItem.getAllItem();
		}else {
			try {
		
			
				items=dbItem.getItemByType(id_type);
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
		
		req.setAttribute("type2", id_type);
		req.setAttribute("Chickenjoy", "Chickenjoy");
		req.setAttribute("Burger", "Burger");
		req.setAttribute("Noodles", "Noodles");
		req.setAttribute("Drinks", "Drinks");
		
		req.setAttribute("listItem", list);
		req.setAttribute("page", page);
		req.setAttribute("number", numberOfPage);
		RequestDispatcher dispatcher = req.getRequestDispatcher("menu.jsp");
		System.out.println("aaaaa"+req.getRemoteAddr());
		dispatcher.forward(req, resp);
//	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
