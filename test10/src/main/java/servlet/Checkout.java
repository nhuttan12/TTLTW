package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Item;
import model.Order;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import database.DBCartItems;
import database.DBOderDetail;
import database.DBOrder;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
HttpSession httpSession=req.getSession();
		
		User user=(User) httpSession.getAttribute("user");
		if(user==null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);		}else { 
		int cartId  = Integer.parseInt(req.getParameter("cartId")) ;
		DBCartItems dbCartItem = new DBCartItems();
		DBOrder dbOrder = new DBOrder();

		int status;
		List<Item> items;
		try {
			Order oder1 = dbOrder.getTopOder();
			List<Item> it = dbCartItem.getListItemByCartID(cartId);
			for (Item item : it) {
				DBOderDetail detail = new DBOderDetail();
				detail.addDetail(oder1.getOrderId(), item);
			}
			dbOrder.updateStatus(oder1.getOrderId(),2 );
			status = dbCartItem.deleteAllITEM(cartId);
			items = dbCartItem.getListItemByCartID(cartId);
			req.setAttribute("listAll", items);
		} catch (SQLException m) {
			// TODO Auto-generated catch block
			m.printStackTrace();
		}
		
		Order oder;
		try {
			oder = dbOrder.getOderEmpty();
			System.out.println(oder.getOrderPrice() +"dayyyyy");
			req.setAttribute("checkout", oder);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			List<Item> oders = dbOrder.getListItemByShopCartID(cartId);
			int slg = oders.size();
			System.out.println(slg +"dayyyyy222");
			req.setAttribute("slg", slg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
//
//		
//		
		req.getRequestDispatcher("menu").forward(req, resp);
	}

	

}
