package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.ImportDetail;
import model.Inventory;
import model.Item;

public class DBItem {

	public int addITEM(Item e) throws SQLException {
		int status = 0;

		Connection c = connectionDB.connect();
		Item item = getItemByID(e.getId());
		if (item == null) {
			String sql = "INSERT INTO ITEMS (CATEGORY_ID, ITEM_NAME, PRICE, DISCOUNT, DISCRIPTION, IMAGES, HIDDEN) "
					+ "VALUES (?, ?, ?, ?, ?, ?, 1);";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getCategory().getId());
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
				String sql = "UPDATE ITEMS SET CATEGORY_ID = ?, ITEM_NAME = ?, PRICE = ?, DISCOUNT = ?, DISCRIPTION = ?, IMAGES = ?, HIDDEN = ? WHERE ITEM_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setInt(1, e.getCategory().getId());
				ps.setString(2, e.getName());
				ps.setDouble(3, e.getPrice());
				ps.setDouble(4, e.getDiscount());
				ps.setString(5, e.getDiscription());
				ps.setString(6, e.getImageName());
				ps.setInt(7, e.getHidden());
				ps.setInt(8, a.getId());
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
				int HIDDEN = rs.getInt("HIDDEN");

				item.setId(ID);
				item.setCategory(new Category(CATEGORY_ID));
				;
				item.setName(ITEM_NAME);
				item.setPrice(PRICE);
				item.setDiscount(DISCOUNT);
				item.setDiscription(DISCRIPTION);
				item.setImageName(IMAGES);
				item.setHidden(HIDDEN);
//				Item item = new Item(ID, ITEM_NAME, UNITPRICE, DISCOUNT, TYPE, IMAGES);
				if (item.getHidden() == 1) {
					b.add(item);
				}
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
				int HIDDEN = rs.getInt("HIDDEN");

				Item item = new Item();
				item.setId(ID);
				item.setName(ITEM_NAME);
				item.setPrice(PRICE);
				item.setImageName(IMAGES);
				item.setDiscount(DISCOUNT);
				item.setCategory(new Category(CATEGORY_ID));
				item.setDiscription(DISCRIPTION);
				item.setHidden(HIDDEN);
				if (item.getHidden() == 1) {
					b.add(item);
				}
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
				item.setId(ID);
				item.setName(ITEM_NAME);
				item.setPrice(PRICE);
				item.setImageName(IMAGES);
				item.setDiscount(DISCOUNT);
				item.setCategory(new Category(CATEGORY_ID));
				;
				item.setDiscription(DISCRIPTION);
//				item.setHidden(HIDDEN);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return item;
	}

	public List<Item> getItemByNameSearch(String n) throws SQLException {
		List<Item> result = new ArrayList<Item>();

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT * FROM ITEMS WHERE ITEM_NAME LIKE ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setString(1, "%" + n + "%");

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
				int HIDDEN = rs.getInt("HIDDEN");
				if (HIDDEN == 1) {
					item.setHidden(HIDDEN);
					item.setId(ID);
					item.setName(ITEM_NAME);
					item.setPrice(PRICE);
					item.setImageName(IMAGES);
					item.setDiscount(DISCOUNT);
					item.setCategory(new Category(CATEGORY_ID));
					;
					item.setDiscription(DISCRIPTION);
					result.add(item);
				}
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
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
				item = new Item();
				item.setId(ID);
				item.setName(ITEM_NAME);
				item.setPrice(PRICE);
				item.setImageName(IMAGES);
				item.setDiscount(DISCOUNT);
				item.setCategory(new Category(CATEGORY_ID));
				;
				item.setDiscription(DISCRIPTION);
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

	public List<Item> getItemForAdmin() {
		List<Item> b = new ArrayList<Item>();
		Connection c = connectionDB.connect();
		String sql = "SELECT i.ITEM_ID, c.CATEGORY_NAME, i.ITEM_NAME, \r\n"
				+ "i.PRICE, i.DISCOUNT, i.DISCRIPTION, \r\n"
				+ "i.IMAGES, i.HIDDEN\r\n"
				+ "FROM items i\r\n"
				+ "JOIN category c ON c.CATEGORY_ID=i.CATEGORY_ID\r\n"
				+ "ORDER BY i.ITEM_ID ASC";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				Category category = new Category();
				item.setCategory(category);
				int id = rs.getInt("ITEM_ID");
				String categoryName = rs.getString("CATEGORY_NAME");
				String itemName = rs.getString("ITEM_NAME");
				int price=rs.getInt("PRICE");
				double discount=rs.getDouble("DISCOUNT");
				String description = rs.getString("DISCRIPTION");
				String image = rs.getString("IMAGES");
				int hidden = rs.getInt("HIDDEN");

				item.setId(id);
				item.getCategory().setCategoryName(categoryName);
				item.setName(itemName);
				item.setPrice(price);
				item.setDiscount(discount);
				item.setDiscription(description);
				item.setImageName(image);
				item.setHidden(hidden);

				b.add(item);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	public List<Item> getInventoryForAdmin() {
		List<Item> b = new ArrayList<Item>();
		Connection c = connectionDB.connect();
		String sql = "SELECT DISTINCT i.ITEM_ID, i.ITEM_NAME, c.CATEGORY_NAME, \r\n"
				+ "iv.quantity, i.PRICE AS 'sale_price', id.PRICE AS 'import_price',\r\n"
				+ "(i.PRICE-id.PRICE) AS 'difference'\r\n" + "FROM inventory iv\r\n"
				+ "JOIN items i ON iv.item_id=i.ITEM_ID\r\n" + "JOIN category c ON c.CATEGORY_ID=i.CATEGORY_ID\r\n"
				+ "JOIN import_detail id ON id.ITEM_ID=i.ITEM_ID";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				Category category = new Category();
				ImportDetail importDetail = new ImportDetail();
				item.setCategory(category);
				item.setImportDetail(importDetail);

				int id = rs.getInt("ITEM_ID");
				String itemName = rs.getString("ITEM_NAME");
				String categoryName = rs.getString("CATEGORY_NAME");
				int quantity = rs.getInt("quantity");
				int salePrice = rs.getInt("sale_price");
				int importPrice = rs.getInt("import_price");
				int difference = rs.getInt("difference");

				item.setId(id);
				item.setName(itemName);
				item.getCategory().setCategoryName(categoryName);
				item.getImportDetail().setQuantity(quantity);
				item.setPrice(salePrice);
				item.getImportDetail().setPrice(importPrice);
				item.setDifference(difference);

				b.add(item);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	public List<Item> getAllItemName() {
		List<Item> b = new ArrayList<Item>();
		Connection c = connectionDB.connect();
		String sql = "SELECT i.ITEM_ID, i.ITEM_NAME\r\n" + "FROM items i";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item();

				int id = rs.getInt("ITEM_ID");
				String itemName = rs.getString("ITEM_NAME");

				item.setId(id);
				item.setName(itemName);

				b.add(item);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	public int addInventoryFirst(Item e) throws SQLException {
		int status = 0;

		Connection c = connectionDB.connect();
		Item item = getItemByID(e.getId());
		if (item != null) {
			String sql = "INSERT INTO inventory (item_id, quantity) VALUES (?,0)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getId());

			status = ps.executeUpdate();

		}
		return status;
	}
	
	public int updateInventory(int invenID, int quantity) throws SQLException {
		int status = 0;

		Connection c = connectionDB.connect();
		String sql = "UPDATE inventory \r\n" + "SET quantity=quantity+?\r\n" + "WHERE id=?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, quantity);
		ps.setInt(2, invenID);

		status = ps.executeUpdate();
		return status;
	}

	public Inventory getInventoryByInvenID(int invenID) {
		Inventory inventory = new Inventory();
		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT i.id, i.item_id, i.quantity\r\n" + "FROM inventory i\r\n" + "WHERE i.id=?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, invenID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int itemId = rs.getInt("item_id");
				int quantity = rs.getInt("quantity");

				inventory.setId(id);
				inventory.setItemId(itemId);
				inventory.setQuantity(quantity);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return inventory;
	}

	public static void main(String[] args) throws SQLException {
		DBItem l = new DBItem();
		System.out.println(l.getInventoryByInvenID(14));
	}
}
