package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Category;
import model.ImportDetail;
import model.Item;
import model.User;

public class DBItem {

	public int addITEM(Item e) throws SQLException {
		int status = 0;

		Connection c = connectionDB.connect();
		Item item = getItemByID(e.getId());
		if (item == null) {
			String sql = "INSERT INTO ITEMS (CATEGORY_ID, ITEM_NAME, PRICE, DISCOUNT, DISCRIPTION, IMAGES) VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = c.prepareStatement(sql);
<<<<<<< HEAD
			ps.setInt(1, e.getCategory_id());
=======
			ps.setInt(1, e.getCategory().getId());
>>>>>>> origin/code
			ps.setString(2, e.getName());
			ps.setDouble(3, e.getPrice());
			ps.setDouble(4, e.getDiscount());
			ps.setString(5, e.getDiscription());
			ps.setString(6, e.getImageName());

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
				String sql = "UPDATE ITEMS SET CATEGORY_ID = ? ITEM_NAME = ?, PRICE = ?, DISCOUNT = ?, DISCRIPTION = ?, IMAGES = ? WHERE ITEM_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
<<<<<<< HEAD
				ps.setInt(1, e.getCategory_id());
=======
				ps.setInt(1, e.getCategory().getId());
>>>>>>> origin/code
				ps.setString(2, e.getName());
				ps.setDouble(3, e.getPrice());
				ps.setDouble(4, e.getDiscount());
				ps.setString(5, e.getDiscription());
				ps.setString(6, e.getImageName());
				ps.setInt(7, a.getId());
				status = ps.executeUpdate();
			} else {
				System.out.println("khong tim thay san pham");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	// lam menu cho All( hidden =1)
	public List<Item> getAllItem() {
		List<Item> b = new ArrayList<Item>();
		Connection c = connectionDB.connect();
		String sql = "SELECT * FROM ITEMS";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				int ID = rs.getInt("ITEM_ID");
				int CATEGORY_ID = rs.getInt("CATEGORY_ID");
				String ITEM_NAME = rs.getString("ITEM_NAME");
				double PRICE = rs.getDouble("PRICE");
				Double DISCOUNT = rs.getDouble("DISCOUNT");
				String DISCRIPTION = rs.getString("DISCRIPTION");
				String IMAGES = rs.getString("IMAGES");
<<<<<<< HEAD
				item.setId(ID);
				item.setCategory_id(CATEGORY_ID);
=======
				int HIDDEN=rs.getInt("HIDDEN");
				
				item.setId(ID);
				item.setCategory(new Category(CATEGORY_ID));;
>>>>>>> origin/code
				item.setName(ITEM_NAME);
				item.setPrice(PRICE);
				item.setDiscount(DISCOUNT);
				item.setDiscription(DISCRIPTION);
				item.setImageName(IMAGES);
<<<<<<< HEAD
//				Item item = new Item(ID, ITEM_NAME, UNITPRICE, DISCOUNT, TYPE, IMAGES);
				b.add(item);
=======
				item.setHidden(HIDDEN);
//				Item item = new Item(ID, ITEM_NAME, UNITPRICE, DISCOUNT, TYPE, IMAGES);
				if(item.getHidden()==1) {
					b.add(item);
				}
				
>>>>>>> origin/code
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;

	}

	// load lam menu tung loai
	public List<Item> getItemByType(int id) throws SQLException {
		List<Item> b = new ArrayList<Item>();

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT * FROM ITEMS  WHERE CATEGORY_ID = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int ID = rs.getInt("ITEM_ID");
				int CATEGORY_ID = rs.getInt("CATEGORY_ID");
				String ITEM_NAME = rs.getString("ITEM_NAME");
				double PRICE = rs.getDouble("PRICE");
				Double DISCOUNT = rs.getDouble("DISCOUNT");
				String DISCRIPTION = rs.getString("DISCRIPTION");
				String IMAGES = rs.getString("IMAGES");
				int HIDDEN=rs.getInt("HIDDEN");

<<<<<<< HEAD
				Item item = new Item(ID, ITEM_NAME, PRICE, IMAGES, DISCOUNT, CATEGORY_ID, DISCRIPTION);
				b.add(item);
=======
				Item item = new Item();
				item.setId(ID);
				item.setName(ITEM_NAME);
				item.setPrice(PRICE);
				item.setImageName(IMAGES);
				item.setDiscount(DISCOUNT);
				item.setCategory(new Category(CATEGORY_ID));;
				item.setDiscription(DISCRIPTION);
				item.setHidden(HIDDEN);
				if(item.getHidden()==1) {
					b.add(item);
				}
				
>>>>>>> origin/code
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	public Item getItemByName(String n) throws SQLException {
		Item item = null;

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT * FROM ITEMS  WHERE ITEM_NAME = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setString(1, n);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int ID = rs.getInt("ITEM_ID");
				int CATEGORY_ID = rs.getInt("CATEGORY_ID");
				String ITEM_NAME = rs.getString("ITEM_NAME");
				double PRICE = rs.getDouble("PRICE");
				Double DISCOUNT = rs.getDouble("DISCOUNT");
				String DISCRIPTION = rs.getString("DISCRIPTION");
				String IMAGES = rs.getString("IMAGES");
//				int HIDDEN=rs.getInt("HIDDEN");

<<<<<<< HEAD
				b = new Item(ID, ITEM_NAME, PRICE, IMAGES, DISCOUNT, CATEGORY_ID, DISCRIPTION);
=======

				item.setId(ID);
				item.setName(ITEM_NAME);
				item.setPrice(PRICE);
				item.setImageName(IMAGES);
				item.setDiscount(DISCOUNT);
				item.setCategory(new Category(CATEGORY_ID));;
				item.setDiscription(DISCRIPTION);
//				item.setHidden(HIDDEN);
				
>>>>>>> origin/code
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
		return item;
	}

	public Item getItemByID(int id) throws SQLException {
		Item item = null;

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT * FROM ITEMS  WHERE ITEM_ID = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int ID = rs.getInt("ITEM_ID");
				int CATEGORY_ID = rs.getInt("CATEGORY_ID");
				String ITEM_NAME = rs.getString("ITEM_NAME");
				double PRICE = rs.getDouble("PRICE");
				Double DISCOUNT = rs.getDouble("DISCOUNT");
				String DISCRIPTION = rs.getString("DISCRIPTION");
				String IMAGES = rs.getString("IMAGES");
<<<<<<< HEAD

				b = new Item(ID, ITEM_NAME, PRICE, IMAGES, DISCOUNT, CATEGORY_ID, DISCRIPTION);;
=======
				
				item.setId(ID);
				item.setName(ITEM_NAME);
				item.setPrice(PRICE);
				item.setImageName(IMAGES);
				item.setDiscount(DISCOUNT);
				item.setCategory(new Category(CATEGORY_ID));;
				item.setDiscription(DISCRIPTION);
>>>>>>> origin/code
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return item;
	}

	public List<Item> getItemByPage(List<Item> list, int start, int end) {
		List<Item> items = new ArrayList<Item>();
		for (int i = start; i < end; i++) {
			items.add(list.get(i));
		}
		return items;
	}

	// bài của Tân chưa sửa
	
<<<<<<< HEAD
//	public List<Item> getItemForAdmin() {
//		List<Item> b = new ArrayList<Item>();
//		Connection c = connectionDB.connect();
//		String sql = "select i.ITEM_ID, c.CATEGORY_NAME as 'type', i.ITEM_NAME, i.PRICE as 'sale_price', import_detail.PRICE as 'import_price', (i.PRICE-import_detail.PRICE) as 'difference', i.DISCOUNT, import_detail.QUANTITY from items i join category c on i.CATEGORY_ID=c.CATEGORY_ID join import_detail on i.ITEM_ID=import_detail.ITEM_ID;";
//		try {
//			PreparedStatement ps = c.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				Item item = new Item();
//				int id = rs.getInt("ITEM_ID");
//				String type = rs.getString("type");
//				String itemName = rs.getString("ITEM_NAME");
//				Double salePrice = rs.getDouble("sale_price");
//				Double importPrice = rs.getDouble("import_price");
//				Double difference = rs.getDouble("difference");
//				double DISCOUNT = rs.getDouble("DISCOUNT");
//				int quantity = rs.getInt("QUANTITY");
//				item.setId(id);
//				item.setType(type);
//				item.setName(itemName);
//				item.setUnitPrice(salePrice);
//				item.setImportPrice(importPrice);
//				item.setDifference(difference);
//				item.setDiscount(DISCOUNT);
//				item.setQuantity(quantity);
//				b.add(item);
//				
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return b;
//	}

	public static void main(String[] args) throws SQLException {
		DBItem l = new DBItem();
//		User a = l.checkUSER("PERSON1", "123");
//		Item b = new Item(1, "BURGER-BO", 20000,  "images/burger/bo.png", 0, 1, "");
//		Item b1 = new Item(2, "BURGER-GA", 23000, "images/burger/ga.png", 0, 1, "");
//		Item b2 = new Item(3, "BURGER-TOM", 23000, "images/burger/tom.jpg", 0, 1, "");
//		Item b3 = new Item(4, "CHICKENJOY-2M", 20000, "images/chickenjoy/2m.png", 0, 1, "");
//		Item b4 = new Item(5, "CHICKENJOY-4M", 35000, "images/chickenjoy/4m.png", 0, 1, "");
//		Item b5 = new Item(6, "CHICKENJOY-6M", 50000, "images/chickenjoy/6m.png", 0, 1, "");
//		Item b6 = new Item(7, "7-UP", 15000, "images/drink/7up.png", 0, 1, "");
//		Item b7 = new Item(8, "MIRINDA", 17000, "images/drink/mirinda.png", 0, 1, "");
//		Item b8 = new Item(9, "PEPSI", 16000, "images/drink/pepsi.png", 0, 1, "");
//		Item b9 = new Item(10, "NOODLES-BO", 50000, "images/spicynoodle/bo.png", 0, 1, "");
//		Item b10 = new Item(11, "NOODLES-HAISAN", 52000, "images/spicynoodle/haisan.png", 0, 1, "");
//		Item b11 = new Item(12, "NOODLES-XUCXICH", 51000, "images/spicynoodle/xucxich.png", 0, 1, "");

		// User b = new User(2, "PERSON2", "456", "Thao", "02", "nu", "");
		// System.out.println(l.update(a));
//		System.out.println(l.deleteUSER("PERSON"));
//		System.out.println(l.getUserByUserName("PERSON").getId());
//		System.out.println(a.getUserName());
//		l.addITEM(b);
//		l.addITEM(b1);
		// l.addITEM(b2);
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
=======
	public List<Item> getItemForAdmin() {
		List<Item> b = new ArrayList<Item>();
		Connection c = connectionDB.connect();
		String sql = "select i.ITEM_ID AS 'id', c.CATEGORY_NAME as 'category', i.ITEM_NAME AS 'item_name', i.PRICE as 'sale_price', \r\n"
				+ "	import_detail.PRICE as 'import_price', (i.PRICE-import_detail.PRICE) as 'difference', \r\n"
				+ "	i.DISCOUNT, import_detail.QUANTITY \r\n"
				+ "from items i \r\n"
				+ "join category c on i.CATEGORY_ID=c.CATEGORY_ID \r\n"
				+ "join import_detail on i.ITEM_ID=import_detail.ITEM_ID;";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				Category category = new Category();
				ImportDetail importDetail = new ImportDetail();
				item.setCategory(category);
				item.setImportDetail(importDetail);
				int id = rs.getInt("id");
				String categoryName = rs.getString("category");
				String itemName = rs.getString("item_name");
				Double salePrice = rs.getDouble("sale_price");
				Double importPrice = rs.getDouble("import_price");
				Double difference = rs.getDouble("difference");
				double DISCOUNT = rs.getDouble("DISCOUNT");
				int quantity = rs.getInt("QUANTITY");
				item.setId(id);
				item.getCategory().setCategoryName(categoryName);
				item.setName(itemName);
				item.setPrice(salePrice);
				item.getImportDetail().setPrice(importPrice);
				item.setDifference(difference);
				item.setDiscount(DISCOUNT);
				item.getImportDetail().setQuantity(quantity);
				b.add(item);
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	public static void main(String[] args) throws SQLException {
		DBItem l = new DBItem();
		List<Item> items = l.getAllItem();
		System.out.println(items);

>>>>>>> origin/code
	}

}
