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

			String sql = "INSERT INTO orders (USER_ID, FULL_NAME, PHONE, ADDRESS, ORDER_DATE, STATUS_ORDER_ID) " + "VALUES (?, ?, ?, ?, ?, ?);";
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

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
//		DBOrder c = new DBOrder();
//		DBUser dbUser=new DBUser();
//		for(User u:dbUser.getUserByRole(1)) {
//			for(Order order:c.getOderByShoppingCartID(u.getShoppingCartId())) {
//				System.out.println(order);
////				for(Item item:c.getListItemOrderByOrderID(order.getOrderId())) {
////					System.out.println(item);
////				}
//			}
//		}
		
//		List<Order> o = c.getOderByCartId(7);
//		for (Order order : o) {
//			System.out.println(order.getOrderId());
////			List<Item> item = c.getListItemOrderByOrderID(136);
////			for (Item it : item) {
////				System.out.println(it.getName());
////			}
//		}
		
//		List<Item> item = c.getListItemOrderByOrderID(136);
//		for (Item item2 : item) {
//			System.out.println(item2.getName());
//		}
		
//		List<Item> item = c.getListItemOrderByOrderID(115);
//		for (Item it : item) {
//			System.out.println(it.getName());
//		}

//		Order o = c.getOderByOderId(136);
//		System.out.println(o.getShoppingCartId());
//		System.out.println(o.getOrderPrice());
//		System.out.println(o.getDate());
//		System.out.println(o.getOrderPrice());
//		System.out.println(o.getStatus());
		
//		for(User u:dbUser.getUserByRole(1)) {
//			for(Order order : 
//				c.getOderByShoppingCartID(u.getShoppingCartId())) {
//				System.out.println(order);
//				for(Item item:c.getListItemOrderByOrderID(order.getOrderId())) {
//					System.out.println(item);
//				}
//			}
//		}
		
//		List<Order> os = c.getOderByCartIdStatus(7);
////		req.setAttribute("listOder", os);
//		
//		Map<Order, List<Item>> orderProductMap = new HashMap<>();
//		for (Order order : os) {
//			List<Item> item = c.getListItemOrderByOrderID(order.getOrderId());
//			System.out.println(order.getOrderId()+" "+order.getOrderPrice());
//			for (Item it : item) {
//				System.out.println(it);
//			}
//			
//			orderProductMap.put(order, item);
//		}
	}
	

}
