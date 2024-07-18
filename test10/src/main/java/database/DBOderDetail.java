package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.Category;
import model.Item;
import model.Order;
import model.OrderDetail;
import model.User;

public class DBOderDetail {
	public OrderDetail addDetail(int oid, int cid) throws SQLException {
		OrderDetail orderDetail = new OrderDetail();

		try (Connection c = connectionDB.connect()) {

			String sql = "INSERT INTO order_detail (ORDER_ID, CART_ID) " + "VALUES (?, ?);";
			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setInt(1, oid);
			ps.setInt(2, cid);
			ps.executeUpdate();

			// lấy ra order trên cùng
			String sql2 = "SELECT * FROM order_detail WHERE ORDER_DETAIL_ID = (SELECT MAX(ORDER_DETAIL_ID) FROM order_detail);";
			PreparedStatement ps2 = c.prepareStatement(sql2);
			ResultSet rs = ps2.executeQuery();
			while (rs.next()) {

				int ODER_DETAIL_ID = rs.getInt("ORDER_DETAIL_ID");
				int ORDER_ID = rs.getInt("ORDER_ID");
				int CART_ID = rs.getInt("CART_ID");
				
				orderDetail = new OrderDetail(ODER_DETAIL_ID, ORDER_ID, CART_ID);
			}
			rs.close();
			c.close();
		} catch (Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
			System.exit(0);
		}

		return orderDetail;
	}
	
	public List<Cart> getCartByOderID(int oid) throws SQLException {
		List<Cart> list = new ArrayList<Cart>();
		Connection connection = connectionDB.connect();
		String sql = "select c.* from order_detail o JOIN cart c ON o.CART_ID = c.CART_ID WHERE o.ORDER_ID=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, oid);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			int CART_ID = rs.getInt("CART_ID");
			int USER_ID = rs.getInt("USER_ID");
			int ITEM_ID = rs.getInt("ITEM_ID");
			int QUANTITY = rs.getInt("QUANTITY");
			double TOTAL_PRICE = rs.getDouble("TOTAL_PRICE");
			
			Cart i = new Cart(CART_ID, USER_ID, ITEM_ID, QUANTITY, TOTAL_PRICE) ;
			list.add(i);
		}

		return list;

	}
	public List<Item> getItemByOderID(int oid) throws SQLException {
		List<Item> list = new ArrayList<Item>();
		Connection connection = connectionDB.connect();
		String sql = "select i.* from order_detail o JOIN cart c ON o.CART_ID = c.CART_ID JOIN items i ON c.ITEM_ID = i.ITEM_ID WHERE o.ORDER_ID=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, oid);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			int ITEM_ID = rs.getInt("ITEM_ID");
			String ITEM_NAME = rs.getString("ITEM_NAME");
			double PRICE = rs.getDouble("PRICE");
			double DISCOUNT = rs.getDouble("DISCOUNT");
			String DISCRIPTION = rs.getString("DISCRIPTION");
			int CATEGORY_ID = rs.getInt("CATEGORY_ID");
			String IMAGES = rs.getString("IMAGES");
			Item i = new Item();
			i.setId(ITEM_ID);
			i.setName(ITEM_NAME);
			i.setPrice(PRICE);
			i.setDiscount(DISCOUNT);
			i.setDiscription(DISCRIPTION);
			Category ca = new Category(CATEGORY_ID);
			i.setCategory(ca);
			i.setImageName(IMAGES);
			list.add(i);
		}

		return list;

	}
//	public Item getItemByItemID(int id) throws SQLException {
//		Item item = new Item();
//		Connection connection = connectionDB.connect();
//		String sql = "select i.* from ODER_DETAIL o JOIN ITEMS i ON o.ITEM_ID = i.ITEM_ID";
//		PreparedStatement preparedStatement = connection.prepareStatement(sql);
//		preparedStatement.setInt(1, id);
//		ResultSet rs = preparedStatement.executeQuery();
//		while (rs.next()) {
//			String NAME = rs.getString("ITEM_NAME");
//			double UNIT_PRICE = rs.getDouble("UNIT_PRICE");
//			int QUANTITY_AVAILABLE = rs.getInt("QUANTITY_AVAILABLE");
//			String TYPE = rs.getString("TYPE");
//			String IMAGES = rs.getString("IMAGES");
//
//			item = new Item(NAME, UNIT_PRICE, QUANTITY_AVAILABLE, TYPE, IMAGES);
//
//		}
//
//		return item;
//
//	}
	public List<OrderDetail> getOrderDetails(int orderId){
		List<OrderDetail> b = new ArrayList<OrderDetail>();
		Connection c = connectionDB.connect();
		String sql = "SELECT od.ORDER_DETAIL_ID, i.ITEM_NAME, i.PRICE, c.QUANTITY, c.TOTAL_PRICE\r\n"
				+ "FROM items i\r\n"
				+ "JOIN cart c ON c.ITEM_ID=i.ITEM_ID\r\n"
				+ "JOIN order_detail od ON od.CART_ID=c.CART_ID\r\n"
				+ "JOIN orders o ON o.ORDER_ID=od.ORDER_ID\r\n"
				+ "WHERE o.ORDER_ID=?;";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, orderId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				int id=rs.getInt("ORDER_DETAIL_ID");
				String name=rs.getString("ITEM_NAME");
				double price=rs.getDouble("PRICE");
				int quantity=rs.getInt("QUANTITY");
				double totalPrice=rs.getDouble("TOTAL_PRICE");
				
				OrderDetail od=new OrderDetail();
				Item item=new Item();
				od.setItem(item);
				od.setId(id);
				od.getItem().setName(name);
				od.getItem().setPrice(price);
				od.setQuantity(quantity);
				od.setPrice(totalPrice);

				b.add(od);
			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
