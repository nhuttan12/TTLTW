package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.Order;
import model.OrderDetail;
import model.User;

public class DBOderDetail {
	public int addDetail(int oid, Item i) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {

			String sql = "INSERT INTO ODER_DETAIL (ODER_ID, ITEM_ID, QUANTITY, PRICE) "
					+ "VALUES (?, ?, ?, ?);";
			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setInt(1, oid);
			ps.setInt(2, i.getId());
			ps.setInt(3, i.getQuantity());
			ps.setDouble(4, i.getUnitPrice()*i.getQuantity());
			status = ps.executeUpdate();

			c.close();
		} catch (Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
			System.exit(0);
		}

		return status;
	}
	
	public List<Item> getOderDetailByOderID(int id) throws SQLException {
		List<Item> list = new ArrayList<Item>();
		Connection connection = connectionDB.connect();
		String sql = "select i.ITEM_NAME, i.UNITPRICE, o.PRICE, o.QUANTITY, i.IMAGES from ODER_DETAIL o JOIN ITEMS i ON o.ITEM_ID = i.ITEM_ID WHERE o.ODER_ID=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			int QUANTITY = rs.getInt("QUANTITY");
			double UNITPRICE = rs.getDouble("UNITPRICE");
			double PRICE = rs.getDouble("PRICE");
			String NAME = rs.getString("ITEM_NAME");
			String IMAGES = rs.getString("IMAGES");
			
			Item order = new Item(NAME, UNITPRICE, PRICE, QUANTITY, IMAGES);
			list.add(order);
		}

		return list;

	}
	public Item getItemByItemID(int id) throws SQLException {
		Item item = new Item();
		Connection connection = connectionDB.connect();
		String sql = "select i.* from ODER_DETAIL o JOIN ITEMS i ON o.ITEM_ID = i.ITEM_ID";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			String NAME = rs.getString("ITEM_NAME");
			double UNIT_PRICE = rs.getDouble("UNIT_PRICE");
			int QUANTITY_AVAILABLE = rs.getInt("QUANTITY_AVAILABLE");
			String TYPE = rs.getString("TYPE");
			String IMAGES = rs.getString("IMAGES");

			item = new Item(NAME, UNIT_PRICE, QUANTITY_AVAILABLE, TYPE, IMAGES);

		}

		return item;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
