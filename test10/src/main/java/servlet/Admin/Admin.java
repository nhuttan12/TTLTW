package servlet.Admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import database.DBItem;
import database.DBLog;
import database.DBOrder;
import database.DBUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Item;
import model.Logging;
import model.Order;
import model.User;

@WebServlet("/admin")
public class Admin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} else {
			String gr = req.getParameter("gr");
			if(gr==null) {
				gr="home";
			}
			System.out.println(gr);
			DBItem dbItem = new DBItem();
			DBUser dbUser = new DBUser();
			DBOrder dbOrder = new DBOrder();
			DBLog dbLog = new DBLog();

			List<User> users = new ArrayList<User>();
			List<Item> items = new ArrayList<Item>();
			List<Order> oders = new ArrayList<Order>();
			List<List<Item>> itemss = new ArrayList<List<Item>>();
			List<List<List<Item>>> itemList = new ArrayList<List<List<Item>>>();
			List<List<Order>> orderList = new ArrayList<List<Order>>();
			
			if (gr == null || gr.equals("home")) {
				req.setAttribute("home", "home");

			} else if (gr.equals("item")) {// quan ly san pham
				items = dbItem.getItemForAdmin();
				req.setAttribute("listItem", items);
				req.setAttribute("item", "item");

				System.out.println(items);
			} else if (gr.equals("spcart")) {// quan ly don hang
				try {
					users = dbUser.getUserByRole(1);
					req.setAttribute("users", users);
					req.setAttribute("spcart", "spcart");

//					for (User u : users) {
//						oders = dbOrder.getOderByShoppingCartID(u.getShoppingCartId());
//						for (Order order : oders) {
//							items = dbOrder.getListItemOrderByOrderID(order.getOrderId());
//							System.out.println(items);
//							itemss.add(items);
//						}
//						orderList.add(oders);
//						itemList.add(itemss);
//
//					}
					req.setAttribute("itemList", itemList);
					req.setAttribute("orderList", orderList);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (gr.equals("user")) {
				try {
					users = dbUser.getUserByRole(1);
					req.setAttribute("listUser", users);
					req.setAttribute("user", "user");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(gr.equals("log")) {
				List<Logging> listLog =dbLog.getAll();
				req.setAttribute("listLog", listLog);
			}

			req.setAttribute("gr2",gr);
			req.setAttribute(gr, gr);
			req.getRequestDispatcher("admin.jsp").forward(req, resp);

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} else {
			String gr = req.getParameter("gr");
			System.out.println(gr);
			DBItem dbItem = new DBItem();
			DBUser dbUser = new DBUser();
			DBOrder dbOrder = new DBOrder();

			List<User> users = new ArrayList<User>();
			List<Item> items = new ArrayList<Item>();
			List<Order> oders = new ArrayList<Order>();
			List<List<Item>> itemss = new ArrayList<List<Item>>();
			List<List<List<Item>>> itemList = new ArrayList<List<List<Item>>>();
			List<List<Order>> orderList = new ArrayList<List<Order>>();
			if (gr == null || gr.equals("home")) {
				req.setAttribute("home", "home");

			} else if (gr.equals("item")) {// quan ly san pham
				items = dbItem.getItemForAdmin();
				System.out.println("item: " + items);
				req.setAttribute("listItem", items);
				req.setAttribute("item", "item");

				System.out.println(items);
			} else if (gr.equals("spcart")) {// quan ly don hang
				try {
					users = dbUser.getUserByRole(1);
					req.setAttribute("users", users);
					req.setAttribute("spcart", "spcart");

					for (User u : users) {
//						oders = dbOrder.getOderByShoppingCartID(u.getShoppingCartId());
						for (Order order : oders) {
//							items = dbOrder.getListItemOrderByOrderID(order.getOrderId());
							System.out.println(items);
							itemss.add(items);
						}
						orderList.add(oders);
						itemList.add(itemss);

					}
					req.setAttribute("itemList", itemList);
					req.setAttribute("orderList", orderList);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (gr.equals("user")) {
				try {
					users = dbUser.getUserByRole(1);
					req.setAttribute("listUser", users);
					req.setAttribute("user", "user");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(gr.equals("log")) {
				req.setAttribute("log", "log");
			}

			req.setAttribute("gr2", gr);
			req.getRequestDispatcher("admin.jsp").forward(req, resp);
		}

	}
}
