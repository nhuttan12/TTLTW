package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Item;
import model.Order;
import model.ShoppingCart;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBCartItems;
import database.DBItem;
import database.DBOrder;

/**
 * Servlet implementation class AddShoppingCart
 */
@WebServlet("/cart")
public class AddShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User a = (User) session.getAttribute("user");
		if(a==null ) {
			req.setAttribute("erro", "bạn phải đăng nhập!");
			RequestDispatcher dispatcher = req.getRequestDispatcher("login");
			dispatcher.forward(req, resp);
		}else {
		int cartId = 0  ;
		
	
		DBCartItems dbCartItem = new DBCartItems();
		List<Item> items;
		try {
			cartId  = Integer.parseInt(req.getParameter("shoppingCartId")) ;
			items = dbCartItem.getListItemByCartID(cartId);
//			session.setAttribute("it", items);
			req.setAttribute("listAll", items);
			DBOrder dbOrder = new DBOrder();
			Order oder = dbOrder.addOrder(cartId);
//			session.setAttribute("od", oder);
			System.out.println(oder.getOrderPrice() +"dayyyyy");
			req.setAttribute("checkout", oder);
			List<Item> oders = dbOrder.getListItemByShopCartID(cartId);
			int slg = oders.size();
			System.out.println(slg +"dayyyyy222");
			req.setAttribute("slg", slg);
		} catch (SQLException m) {
			// TODO Auto-generated catch block
			m.printStackTrace();
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("shoppingcart.jsp");
		dispatcher.forward(req, resp);
		}
		
		

		
		

		
	}
	
	public static void main(String[] args) {
//		DBItem dbItem = new DBItem();
//		List<Item> items = dbItem.getAllItem();
//		for (Item item : items) {
//			System.out.println(item.getPrice());
//		}
		
	}

}
