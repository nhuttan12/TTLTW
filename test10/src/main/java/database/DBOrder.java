package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import model.Item;
import model.Order;
import model.StatusOrder;
import model.Cart;
import model.Category;
import model.User;

public class DBOrder {

//	public List<Item> getListByShopCartID(int cart_id) throws SQLException {
//		List<Item> list = new ArrayList<Item>();
//		try (Connection c = connectionDB.connect()) {
//
//			String sql = "SELECT * FROM  CART_ITEMS ci JOIN ITEMS i on i.ITEM_ID = ci.ITEM_ID	JOIN SHOPPINGCARTS s on s.SHOPPINGCART_ID = ci.SHOPPINGCART_ID  WHERE s.SHOPPINGCART_ID = ?;";
//
//			PreparedStatement ps = c.prepareStatement(sql);
//			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
//
//			ps.setInt(1, cart_id);
//
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {
//
//				int ITEM_ID = rs.getInt("ITEM_ID");
//				String ITEM_NAME = rs.getString("ITEM_NAME");
//				double PRICE = rs.getDouble("PRICE");
//				double DISCOUNT = rs.getDouble("DISCOUNT");
//				String DISCRIPTION = rs.getString("DISCRIPTION");
//				int CATEGORY_ID = rs.getInt("CATEGORY_ID");
//				String IMAGES = rs.getString("IMAGES");
//				
//
//				Item i = new Item();
//				i.setId(ITEM_ID);
//				i.setName(ITEM_NAME);
//				i.setPrice(PRICE);
//				i.setDiscount(DISCOUNT);
//				i.setDiscription(DISCRIPTION);
//				// phần này chưa đúng nghe, nên coi lại
//				Category ca = new Category(CATEGORY_ID);
//				i.setCategory(ca);
//				i.setImageName(IMAGES);
//				list.add(i);
//			}
//			rs.close();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return list;
//	}
//
// status = 1: chua thanh toan 2: thanh toan roi cho duyet 3: thanh toan roi duyet roi 4: xoa khoi lich su thanh toan
	public Order addOrder(int uId, String name, String phone, String address) {
		Order order = new Order();
		LocalDate curDate = java.time.LocalDate.now();
		String date = curDate.toString();
		try (Connection c = connectionDB.connect()) {

			String sql = "INSERT INTO orders (USER_ID, FULL_NAME, PHONE, ADDRESS, ORDER_DATE, STATUS_ORDER_ID) "
					+ "VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setInt(1, uId);
			ps.setString(2, name);
			ps.setString(3, phone);
			ps.setString(4, address);
			ps.setString(5, date);
			ps.setInt(6, 1);
//			System.out.println(orderPrice);
			ps.executeUpdate();
			System.out.println("da them");

			// lấy ra order trên cùng
			String sql2 = "SELECT * FROM orders WHERE ORDER_ID = (SELECT MAX(ORDER_ID) FROM orders);";
			PreparedStatement ps2 = c.prepareStatement(sql2);
			ResultSet rs = ps2.executeQuery();
			while (rs.next()) {

				int ORDER_ID = rs.getInt("ORDER_ID");
				int USER_ID = rs.getInt("USER_ID");
				String NAME = rs.getString("FULL_NAME");
				String PHONE = rs.getString("PHONE");
				String ADDRESS = rs.getString("ADDRESS");
				String NOTE = rs.getString("NOTE");
				String ORDER_DATE = rs.getString("ORDER_DATE");
				int STATUS = rs.getInt("STATUS_ORDER_ID");

				order = new Order(ORDER_ID, USER_ID, NAME, PHONE, ADDRESS, NOTE, ORDER_DATE, STATUS);
			}
			rs.close();
			c.close();

		} catch (

		Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
			System.exit(0);
		}

		return order;

	}

//
//	public int updateStatus(int oid, int status) throws SQLException {
//		int s = 0;
//
//		try (Connection c = connectionDB.connect()) {
//			String sql = "UPDATE ODER SET STATUS = ? WHERE ODER_ID = ?;";
//			PreparedStatement ps = c.prepareStatement(sql);
//			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
//			ps.setInt(1, status);
//			ps.setInt(2, oid);
//			System.out.println("update thanh cong");
//			s = ps.executeUpdate();
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		return s;
//	}
//
//	public void name() {
//
//	}
//
//	public List<Item> getListItemOrderByOrderID(int OrderID) {
//		List<Item> list = new ArrayList<Item>();
//
//		try (Connection c = connectionDB.connect()) {
//
//			String sql = "SELECT * FROM  CART_ITEMS ci JOIN ITEMS i on i.ITEM_ID = ci.ITEM_ID	JOIN SHOPPINGCARTS s on s.SHOPPINGCART_ID = ci.SHOPPINGCART_ID"
//					+ " JOIN ODER o on s.SHOPPINGCART_ID = o.SHOP_CART_ID  WHERE o.ODER_ID = ?;";
//			PreparedStatement ps = c.prepareStatement(sql);
//			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
//
//			ps.setInt(1, OrderID);
//
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {
////				System.out.println("pkkkkkkkkkkkkkk");
//
//				int ITEM_ID = rs.getInt("ITEM_ID");
//				String ITEM_NAME = rs.getString("ITEM_NAME");
//				double UNITPRICE = rs.getDouble("UNITPRICE");
//				double PRICE = rs.getDouble("TOTAL_PRICE");
//				int QUANTITY_AVAILABLE = rs.getInt("QUANTITY_AVAILABLE");
//				int QUANTITY_ITEM = rs.getInt("QUANTITY_ITEM");
//				String TYPE = rs.getString("TYPE");
//				String IMAGES = rs.getString("IMAGES");
//
//				Item i = new Item(ITEM_ID, ITEM_NAME, UNITPRICE, PRICE, QUANTITY_AVAILABLE, QUANTITY_ITEM, TYPE,
//						IMAGES);
//				list.add(i);
//			}
//			rs.close();
//		} catch (
//
//		Exception ex) {
//
//			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
//			System.exit(0);
//		}
//
//		return list;
//
//	}
//
//	public Order getTopOder() throws SQLException {
//
//		Order order = new Order();
//		try (Connection c = connectionDB.connect()) {
//
//			String sql = "SELECT * FROM ODER WHERE ODER_ID = (SELECT MAX(ODER_ID) FROM ODER);";
//			PreparedStatement ps = c.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//
//				int ODER_ID = rs.getInt("ODER_ID");
//				int SHOP_CART_ID = rs.getInt("SHOP_CART_ID");
//				String DATE = rs.getString("DATE");
//				double ODER_PRICE = rs.getDouble("ODER_PRICE");
//				int STATUS = rs.getInt("STATUS");
//
//				order = new Order(ODER_ID, SHOP_CART_ID, DATE, ODER_PRICE, STATUS);
//			}
//			rs.close();
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		return order;
//	}
//
//	public Order getOderEmpty() throws SQLException {
//
//		Order order = new Order();
//		try (Connection c = connectionDB.connect()) {
//
//			String sql = "SELECT * FROM ODER  WHERE ODER_ID = 1;";
//			PreparedStatement ps = c.prepareStatement(sql);
//
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {
//
//				int ODER_ID = rs.getInt("ODER_ID");
//				int SHOP_CART_ID = rs.getInt("SHOP_CART_ID");
//				String DATE = rs.getString("DATE");
//				double ODER_PRICE = rs.getDouble("ODER_PRICE");
//				int STATUS = rs.getInt("STATUS");
//
//				order = new Order(ODER_ID, SHOP_CART_ID, DATE, ODER_PRICE, STATUS);
//			}
//			rs.close();
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		return order;
//	}
//
	public List<Order> getOderByUserID(int uid) throws SQLException {
		List<Order> list = new ArrayList<Order>();
		Connection connection = connectionDB.connect();
		String sql = "select * from orders where USER_ID=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, uid);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			int ORDER_ID = rs.getInt("ORDER_ID");
			int USER_ID = rs.getInt("USER_ID");
			String NAME = rs.getString("FULL_NAME");
			String PHONE = rs.getString("PHONE");
			String ADDRESS = rs.getString("ADDRESS");
			String NOTE = rs.getString("NOTE");
			String ORDER_DATE = rs.getString("ORDER_DATE");
			int STATUS = rs.getInt("STATUS_ORDER_ID");

			Order order = new Order(ORDER_ID, USER_ID, NAME, PHONE, ADDRESS, NOTE, ORDER_DATE, STATUS);
			list.add(order);
		}

		return list;

	}

//
//	public Order getOderByOderId(int id) throws SQLException {
//
//		Order order = new Order();
//		try (Connection c = connectionDB.connect()) {
//
//			String sql = "SELECT * FROM ODER  WHERE ODER_ID = ?;";
//			PreparedStatement ps = c.prepareStatement(sql);
//			ps.setInt(1, id);
//
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {
//
//				int ODER_ID = rs.getInt("ODER_ID");
//				int SHOP_CART_ID = rs.getInt("SHOP_CART_ID");
//				String DATE = rs.getString("DATE");
//				double ODER_PRICE = rs.getDouble("ODER_PRICE");
//				int STATUS = rs.getInt("STATUS");
//
//				order = new Order(ODER_ID, SHOP_CART_ID, DATE, ODER_PRICE, STATUS);
//			}
//			rs.close();
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		return order;
//	}
//
//	public int deleteOrder(int id) {
//		int status = 0;
//
//		try (Connection c = connectionDB.connect()) {
//			Order a = getOderByOderId(id);
//			if (a != null) {
//				String sql = "DELETE FROM ODER  WHERE ODER_ID = ?;";
//
//				PreparedStatement ps = c.prepareStatement(sql);
//				ps.setInt(1, id);
//
//				status = ps.executeUpdate();
//				System.out.println("oke");
//				c.close();
//			} else {
//				System.out.println("don hang khoong ton tai");
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		return status;
//
//	}
//
//	public int updateStatusByOderId(int OrderId) throws SQLException {
//		int result = 0;
//		Connection connection = connectionDB.connect();
//		Order order = getOderByOderId(OrderId);
//		if (order != null) {
//			String sql = "UPDATE ODER SET STATUS = ? WHERE ODER_ID = ?;";
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(1, 3);
//			preparedStatement.setInt(2, OrderId);
//			result = preparedStatement.executeUpdate();
//		}
//
//		return result;
//	}
//
//	public List<Order> getAllOder() {
//		List<Order> b = new ArrayList<Order>();
//		Connection c = connectionDB.connect();
//		String sql = "SELECT * FROM ODER";
//		try {
//			PreparedStatement ps = c.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				int ODER_ID = rs.getInt("ODER_ID");
//				int SHOP_CART_ID = rs.getInt("SHOP_CART_ID");
//				String DATE = rs.getString("DATE");
//				double ODER_PRICE = rs.getDouble("ODER_PRICE");
//				int STATUS = rs.getInt("STATUS");
//
//				Order oder = new Order(ODER_ID, SHOP_CART_ID, DATE, ODER_PRICE, STATUS);
//				b.add(oder);
//
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return b;
//
//	}
//
//	public List<Order> getOderByCartId(int cartId) {
//		List<Order> b = new ArrayList<Order>();
//		Connection c = connectionDB.connect();
//		String sql = "SELECT * FROM ODER WHERE SHOP_CART_ID = ?";
//		try {
//			PreparedStatement ps = c.prepareStatement(sql);
//			ps.setInt(1, cartId);
//
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {
//
//				int ODER_ID = rs.getInt("ODER_ID");
//				int SHOP_CART_ID = rs.getInt("SHOP_CART_ID");
//				String DATE = rs.getString("DATE");
//				double ODER_PRICE = rs.getDouble("ODER_PRICE");
//				int STATUS = rs.getInt("STATUS");
//
//				Order oder = new Order(ODER_ID, SHOP_CART_ID, DATE, ODER_PRICE, STATUS);
//				b.add(oder);
//			}
//			rs.close();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return b;
//
//	}
//
//	public List<Order> getOderByCartIdStatus(int cartId) {
//		List<Order> b = new ArrayList<Order>();
//		Connection c = connectionDB.connect();
//		String sql = "SELECT * FROM ODER WHERE SHOP_CART_ID = ? AND STATUS = 2 OR STATUS = 3";
//		try {
//			PreparedStatement ps = c.prepareStatement(sql);
//			ps.setInt(1, cartId);
//
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {
//
//				int ODER_ID = rs.getInt("ODER_ID");
//				int SHOP_CART_ID = rs.getInt("SHOP_CART_ID");
//				String DATE = rs.getString("DATE");
//				double ODER_PRICE = rs.getDouble("ODER_PRICE");
//				int STATUS = rs.getInt("STATUS");
//
//				Order oder = new Order(ODER_ID, SHOP_CART_ID, DATE, ODER_PRICE, STATUS);
//				b.add(oder);
//			}
//			rs.close();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return b;
//
//	}
	public List<Order> getOrdersForAdmin() {
		List<Order> b = new ArrayList<Order>();
		Connection c = connectionDB.connect();
		String sql = "SELECT o.ORDER_ID, u.FULL_NAME, sum(c.TOTAL_PRICE) AS 'TOTAL_PRICE', \r\n"
				+ "o.ORDER_DATE, o.DELIVERY_DATE, \r\n"
				+ "so.STATUS_ORDER_NAME, o.ADDRESS\r\n"
				+ "FROM orders o\r\n"
				+ "JOIN users u ON o.USER_ID=u.USER_ID\r\n"
				+ "JOIN order_detail od ON od.ORDER_ID=o.ORDER_ID\r\n"
				+ "JOIN cart c ON od.CART_ID=c.CART_ID\r\n"
				+ "JOIN items i ON c.ITEM_ID=i.ITEM_ID\r\n"
				+ "JOIN status_oder so ON so.STATUS_ORDER_ID=o.STATUS_ORDER_ID \r\n"
				+ "GROUP BY o.ORDER_ID";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int orderId = rs.getInt("ORDER_ID");
				String name = rs.getString("FULL_NAME");
				int total_price = rs.getInt("TOTAL_PRICE");	
				String orderDate = rs.getString("ORDER_DATE");
				String deliverDate = rs.getString("DELIVERY_DATE");
				String status = rs.getString("STATUS_ORDER_NAME");
				String address = rs.getString("ADDRESS");
				
				Order order = new Order();
				StatusOrder statusOrder=new StatusOrder();
				order.setStatusOrder(statusOrder);
				
				order.setOrderId(orderId);
				order.setName(name);
				order.setTotalPrice(total_price);
				order.setOrderDate(orderDate);
				order.setDeliveriDate(deliverDate);
				order.getStatusOrder().setName(status);
				order.setAddress(address);
				
				b.add(order);
			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	public Order getOrderById(int id) {
		Connection c = connectionDB.connect();
		Order order = new Order();
		StatusOrder statusOrder =new StatusOrder();
		order.setStatusOrder(statusOrder);
		String sql = "SELECT o.ORDER_ID, u.FULL_NAME,  sum(c.TOTAL_PRICE) AS 'TOTAL_PRICE', \r\n"
				+ "o.ORDER_DATE, o.DELIVERY_DATE, \r\n"
				+ "o.STATUS_ORDER_ID AS 'status', o.ADDRESS\r\n"
				+ "FROM orders o\r\n"
				+ "JOIN users u ON o.USER_ID=u.USER_ID\r\n"
				+ "JOIN order_detail od ON od.ORDER_ID=o.ORDER_ID\r\n"
				+ "JOIN cart c ON od.CART_ID=c.CART_ID\r\n"
				+ "JOIN items i ON c.ITEM_ID=i.ITEM_ID\r\n"
				+ "WHERE o.ORDER_ID=?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				int orderId = rs.getInt("ORDER_ID");
				String name = rs.getString("FULL_NAME");
				double total_price = rs.getInt("TOTAL_PRICE");
				String orderDate = rs.getString("ORDER_DATE");
				String deliverDate = rs.getString("DELIVERY_DATE");
				int statusId = rs.getInt("status");
				order.setOrderId(orderId);
				order.setName(name);
				order.setTotalPrice(total_price);
				order.setOrderDate(orderDate);
				order.setDeliveriDate(deliverDate);
				order.getStatusOrder().setId(statusId);

			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	
	public int updateOrderStatus(int orderId,int statusId, String deliverDate) {
		int status = 0;
		
		Connection c = connectionDB.connect();
		Order order = new Order();
		StatusOrder statusOrder =new StatusOrder();
		order.setStatusOrder(statusOrder);
		String sql="UPDATE orders o\r\n"
				+ "SET o.DELIVERY_DATE = ?,\r\n"
				+ "		o.STATUS_ORDER_ID=?\r\n"
				+ "WHERE o.ORDER_ID=?;";
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(sql);
			ps.setString(1, deliverDate);
			ps.setInt(2, statusId);
			ps.setInt(3, orderId);
			
			status = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public List<Order> getCusomerNotBuyOver6Month(){
		List<Order> b = new ArrayList<Order>();
		Connection c = connectionDB.connect();
		String sql = "SELECT \r\n"
				+ "    u.USER_ID, \r\n"
				+ "    u.FULL_NAME, \r\n"
				+ "    o.ORDER_DATE,\r\n"
				+ "    DATEDIFF(CURDATE(), o.ORDER_DATE) AS DAYS_DIFFERENCE\r\n"
				+ "FROM users u\r\n"
				+ "JOIN orders o ON o.USER_ID = u.USER_ID\r\n"
				+ "WHERE DATEDIFF(CURDATE(), o.ORDER_DATE) > 6 * 30 -- Giả sử một tháng có 30 ngày\r\n"
				+ "GROUP BY u.USER_ID, u.FULL_NAME, o.ORDER_DATE;\r\n";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int userId = rs.getInt("USER_ID");
				String name = rs.getString("FULL_NAME");
				String orderDate = rs.getString("ORDER_DATE");
				int dayDifference = rs.getInt("DAYS_DIFFERENCE");	
				
				Order order = new Order();
				
				order.setUserId(userId);
				order.setName(name);
				order.setOrderDate(orderDate);
				order.setDateDifference(dayDifference);
				
				b.add(order);
			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	public List<Order> topCustomer(){
		List<Order> b = new ArrayList<Order>();
		Connection c = connectionDB.connect();
		String sql = "SELECT \r\n"
				+ "    u.USER_ID, \r\n"
				+ "    u.FULL_NAME, \r\n"
				+ "    COUNT(o.ORDER_ID) AS TOTAL_ORDERS\r\n"
				+ "FROM users u\r\n"
				+ "JOIN orders o ON o.USER_ID = u.USER_ID\r\n"
				+ "WHERE MONTH(o.ORDER_DATE) = MONTH(CURDATE()) \r\n"
				+ "AND YEAR(o.ORDER_DATE) = YEAR(CURDATE())\r\n"
				+ "GROUP BY u.USER_ID, u.FULL_NAME;";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int userId = rs.getInt("USER_ID");
				String name = rs.getString("FULL_NAME");
				String orderDate = rs.getString("TOTAL_ORDERS");
				
				Order order = new Order();
				
				order.setUserId(userId);
				order.setName(name);
				order.setOrderDate(orderDate);
				
				b.add(order);
			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
}
