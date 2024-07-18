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
				String sql = "UPDATE ITEMS SET CATEGORY_ID = ? ITEM_NAME = ?, PRICE = ?, DISCOUNT = ?, DISCRIPTION = ?, IMAGES = ? WHERE ITEM_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setInt(1, e.getCategory().getId());
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
		String sql = "SELECT items.ITEM_ID AS 'id', category.CATEGORY_NAME AS 'category' , items.ITEM_NAME AS 'name', \r\n"
				+ "items.PRICE AS 'sale_price', import_detail.PRICE AS 'import_price', \r\n"
				+ "(items.PRICE-import_detail.PRICE) AS 'difference',items.DISCOUNT AS 'discount', \r\n"
				+ "inventory.quantity\r\n"
				+ "FROM items JOIN category ON items.CATEGORY_ID=category.CATEGORY_ID\r\n"
				+ "JOIN import_detail ON import_detail.ITEM_ID=items.ITEM_ID\r\n"
				+ "JOIN inventory ON items.ITEM_ID=inventory.item_id;";
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
				String itemName = rs.getString("name");
				Double salePrice = rs.getDouble("sale_price");
				Double importPrice = rs.getDouble("import_price");
				Double difference = rs.getDouble("difference");
				double DISCOUNT = rs.getDouble("discount");
				int quantity = rs.getInt("quantity");
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
		List<Item> items = l.getItemForAdmin();
		System.out.println(items);
	}

}
