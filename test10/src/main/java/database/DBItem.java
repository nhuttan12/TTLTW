package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Item;
import model.User;

public class DBItem {

	public int addITEM(Item e) throws SQLException {
		int status = 0;

		Connection c = connectionDB.connect();
		Item item = getItemByID(e.getId());
		if (item == null) {
			String sql = "INSERT INTO ITEMS (ITEM_NAME, UNITPRICE, QUANTITY_AVAILABLE, TYPE, IMAGES) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getUnitPrice());
			ps.setInt(3, e.getQuantityAvailable());
			ps.setString(4, e.getType());
			ps.setString(5, e.getImageName());

			status = ps.executeUpdate();

		}
		return status;
	}

	public int deleteITEM(int id) {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
			Item a = getItemByID(id);
			if (a != null) {
				String sql = "DELETE FROM ITEMS  WHERE ITEM_ID = ?;";

				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setInt(1, id);

				status = ps.executeUpdate();

				c.close();
			} else {
				System.out.println("san pham khoong ton tai");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;

	}

	public int update(Item e) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
			Item a = getItemByID(e.getId());
			if (a != null) {
				String sql = "UPDATE ITEMS SET ITEM_NAME = ?, UNITPRICE = ?, QUANTITY_AVAILABLE = ?, TYPE = ?, IMAGES = ? WHERE ITEM_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setString(1, e.getName());
				ps.setDouble(2, e.getUnitPrice());
				ps.setInt(3, e.getQuantityAvailable());
				ps.setString(4, e.getType());
				ps.setString(5, e.getImageName());
				ps.setInt(6, a.getId());
				status = ps.executeUpdate();
			} else {
				System.out.println("khong tim thay san pham");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	// lam menu cho All
	public List<Item> getAllItem() {
		List<Item> b = new ArrayList<Item>();
		Connection c = connectionDB.connect();
		String sql = "SELECT * FROM ITEMS";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt("ITEM_ID");
				String ITEM_NAME = rs.getString("ITEM_NAME");
				double UNITPRICE = rs.getDouble("UNITPRICE");
				int QUANTITY_AVAILABLE = rs.getInt("QUANTITY_AVAILABLE");
				String TYPE = rs.getString("TYPE");
				String IMAGES = rs.getString("IMAGES");

				Item item = new Item(ID, ITEM_NAME, UNITPRICE, QUANTITY_AVAILABLE, TYPE, IMAGES);
				b.add(item);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;

	}

	// load lam menu tung loai
	public List<Item> getItemByType(String type) throws SQLException {
		List<Item> b = new ArrayList<Item>();

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT * FROM ITEMS  WHERE TYPE = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setString(1, type);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int ID = rs.getInt("ITEM_ID");
				String ITEM_NAME = rs.getString("ITEM_NAME");
				double UNITPRICE = rs.getDouble("UNITPRICE");
				int QUANTITY_AVAILABLE = rs.getInt("QUANTITY_AVAILABLE");
				String TYPE = rs.getString("TYPE");
				String IMAGES = rs.getString("IMAGES");

				Item item = new Item(ID, ITEM_NAME, UNITPRICE, QUANTITY_AVAILABLE, TYPE, IMAGES);
				b.add(item);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	public Item getItemByName(String n) throws SQLException {
		Item b = null;

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT * FROM ITEMS  WHERE ITEM_NAME = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setString(1, n);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int ID = rs.getInt("ITEM_ID");
				String ITEM_NAME = rs.getString("ITEM_NAME");
				double UNITPRICE = rs.getDouble("UNITPRICE");
				int QUANTITY_AVAILABLE = rs.getInt("QUANTITY_AVAILABLE");
				String TYPE = rs.getString("TYPE");
				String IMAGES = rs.getString("IMAGES");

				b = new Item(ID, ITEM_NAME, UNITPRICE, QUANTITY_AVAILABLE, TYPE, IMAGES);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	public Item getItemByID(int id) throws SQLException {
		Item b = null;

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT * FROM ITEMS  WHERE ITEM_ID = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int ID = rs.getInt("ITEM_ID");
				String ITEM_NAME = rs.getString("ITEM_NAME");
				double UNITPRICE = rs.getDouble("UNITPRICE");
				int QUANTITY_AVAILABLE = rs.getInt("QUANTITY_AVAILABLE");
				String TYPE = rs.getString("TYPE");
				String IMAGES = rs.getString("IMAGES");

				b = new Item(ID, ITEM_NAME, UNITPRICE, QUANTITY_AVAILABLE, TYPE, IMAGES);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}
	public List<Item> getItemByPage(List<Item> list,int start,int end){
		List<Item>  items = new ArrayList<Item>();
		for(int i=start;i<end;i++) {
			items.add(list.get(i));
		}
		return items;
	}

	public static void main(String[] args) throws SQLException {
		DBItem l = new DBItem();
//		User a = l.checkUSER("PERSON1", "123");
		Item b = new Item(1, "BURGER-BO",20000, 10, "Burger", "images/burger/bo.png");
		Item b1 = new Item(2, "BURGER-GA",23000, 10, "Burger", "images/burger/ga.png");
		Item b2 = new Item(3, "BURGER-TOM",23000, 10, "Burger", "images/burger/tom.jpg");
		Item b3 = new Item(4, "CHICKENJOY-2M",20000, 10, "Chickenjoy", "images/chickenjoy/2m.png");
		Item b4 = new Item(5, "CHICKENJOY-4M",35000, 10, "Chickenjoy", "images/chickenjoy/4m.png");
		Item b5 = new Item(6, "CHICKENJOY-6M",50000, 10, "Chickenjoy", "images/chickenjoy/6m.png");
		Item b6 = new Item(7, "7-UP",15000, 10, "Drinks", "images/drink/7up.png");
		Item b7 = new Item(8, "MIRINDA",17000, 16, "Drinks", "images/drink/mirinda.png");
		Item b8 = new Item(9, "PEPSI",16000, 10, "Drinks", "images/drink/pepsi.png");
		Item b9 = new Item(10, "NOODLES-BO",50000, 10, "Noodles", "images/spicynoodle/bo.png");
		Item b10 = new Item(11, "NOODLES-HAISAN",52000, 10, "Noodles", "images/spicynoodle/haisan.png");
		Item b11 = new Item(12, "NOODLES-XUCXICH",51000, 10, "Noodles", "images/spicynoodle/xucxich.png");

		
		// User b = new User(2, "PERSON2", "456", "Thao", "02", "nu", "");
		// System.out.println(l.update(a));
//		System.out.println(l.deleteUSER("PERSON"));
//		System.out.println(l.getUserByUserName("PERSON").getId());
//		System.out.println(a.getUserName());
//		l.addITEM(b);
//		l.addITEM(b1);
		//l.addITEM(b2);
//		l.addITEM(b3);
//		l.addITEM(b4);
//		l.addITEM(b5);
//		l.addITEM(b6);
//		l.addITEM(b7);
//		l.addITEM(b8);
//		l.addITEM(b9);
//		l.addITEM(b10);
//		l.addITEM(b11);
//		
//		System.out.println(l.getAllItem());
//		for (Item z : l.getAllItem()) {
//			System.out.println(z);
//		}
//		DBItem d = new DBItem();
		List<Item> list = l.getAllItem();
		for (Item i : list) {
			
			System.out.println(i.getName());
		}
	}

}
