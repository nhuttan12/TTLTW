package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.ImportDetail;
import model.ImportItem;
import model.Item;
import model.User;

public class DBImport {
	public List<ImportItem> getHistoryImportLists() {
		List<ImportItem> b = new ArrayList<ImportItem>();
		Connection c = connectionDB.connect();
		String sql = "SELECT ii.IMPORT_ITEM_ID, u.FULL_NAME,\r\n" + "SUM(id.QUANTITY) AS 'total_quantity_import',\r\n"
				+ "COUNT(id.IMPORT_DETAIL_ID) AS 'total_item_import',\r\n"
				+ "SUM(id.PRICE*id.QUANTITY) AS 'total_price', ii.IMPORT_DATE\r\n" + "FROM import_item ii\r\n"
				+ "JOIN users u ON u.USER_ID=ii.USER_ID\r\n"
				+ "JOIN import_detail id ON id.ITEM_IMPORT_ID=ii.IMPORT_ITEM_ID\r\n"
				+ "GROUP BY ii.IMPORT_ITEM_ID;";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ImportItem importItem = new ImportItem();
				User user = new User();
				importItem.setUser(user);

				int id = rs.getInt("IMPORT_ITEM_ID");
				String importer = rs.getString("FULL_NAME");
				int quantityImport = rs.getInt("total_quantity_import");
				int itemImport = rs.getInt("total_item_import");
				int totalPrice = rs.getInt("total_price");
				String importDate = rs.getString("IMPORT_DATE");

				importItem.setId(id);
				importItem.getUser().setName(importer);
				importItem.setTotalQuantity(quantityImport);
				importItem.setTotalItem(itemImport);
				importItem.setTotalPrice(totalPrice);
				importItem.setImportDate(importDate);

				b.add(importItem);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	public List<ImportDetail> getImportsDetailById(int importId) {
		List<ImportDetail> b = new ArrayList<ImportDetail>();
		Connection c = connectionDB.connect();
		String sql = "SELECT id.IMPORT_DETAIL_ID, i.ITEM_NAME, \r\n"
				+ "id.QUANTITY, id.PRICE, (id.QUANTITY*id.PRICE) AS 'total_price'\r\n" + "FROM import_detail id\r\n"
				+ "JOIN import_item ii ON ii.IMPORT_ITEM_ID=id.ITEM_IMPORT_ID\r\n"
				+ "JOIN items i ON id.ITEM_ID=i.ITEM_ID  \r\n" + "WHERE ii.IMPORT_ITEM_ID=?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, importId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ImportDetail importDetail = new ImportDetail();
				Item item = new Item();
				importDetail.setItem(item);

				int id = rs.getInt("IMPORT_DETAIL_ID");
				String name = rs.getString("ITEM_NAME");
				int quantity = rs.getInt("QUANTITY");
				int price = rs.getInt("PRICE");
				int totalPrice = rs.getInt("total_price");

				importDetail.setId(id);
				importDetail.getItem().setName(name);
				importDetail.setQuantity(quantity);
				importDetail.getItem().setPrice(price);
				importDetail.setPrice(totalPrice);

				b.add(importDetail);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	public ImportItem getImportItemById(int id) {
		ImportItem importItem = new ImportItem();
		User user = new User();
		importItem.setUser(user);

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT * FROM import_item ii WHERE ii.IMPORT_ITEM_ID=?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int ID = rs.getInt("USER_ID");
				String IMPORT_DATE = rs.getString("IMPORT_DATE");

				importItem.getUser().setId(ID);
				importItem.setImportDate(IMPORT_DATE);

			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return importItem;
	}

	public int addImportItem(ImportItem importI) throws SQLException {
		int status = 0;

		Connection c = connectionDB.connect();
		String sql = "INSERT INTO import_item (USER_ID, IMPORT_DATE) VALUES (?, CURDATE());";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, importI.getUser().getId());

		status = ps.executeUpdate();
		return status;
	}

	public ImportDetail getImportDetailById(int id) {
		ImportDetail importDetail = new ImportDetail();
		ImportItem importItem = new ImportItem();
		Item item = new Item();
		importDetail.setImpoItem(importItem);
		importDetail.setItem(item);

		Connection c = connectionDB.connect();
		String sql = "SELECT * FROM import_detail id WHERE id.IMPORT_DETAIL_ID=?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int IMPORT_DETAIL_ID = rs.getInt("IMPORT_DETAIL_ID");
				int ITEM_IMPORT_ID = rs.getInt("ITEM_IMPORT_ID");
				int ITEM_ID = rs.getInt("ITEM_ID");
				int QUANTITY = rs.getInt("QUANTITY");
				int PRICE = rs.getInt("PRICE");

				importDetail.setId(IMPORT_DETAIL_ID);
				importDetail.getImpoItem().setId(ITEM_IMPORT_ID);
				importDetail.getItem().setId(ITEM_ID);
				importDetail.setQuantity(QUANTITY);
				importDetail.setPrice(PRICE);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return importDetail;
	}

	public int addToImportDetail(ImportDetail od) throws SQLException {
		int status = 0;

		Connection c = connectionDB.connect();

		// Step 1: Retrieve the latest price for the given ITEM_ID
		String priceQuery = "SELECT PRICE FROM import_detail WHERE ITEM_ID = ? ORDER BY ITEM_IMPORT_ID DESC LIMIT 1";
		PreparedStatement priceStatement = c.prepareStatement(priceQuery);
		priceStatement.setInt(1, od.getItem().getId());
		ResultSet rs = priceStatement.executeQuery();

		double latestPrice = 0.0;
		if (rs.next()) {
			latestPrice = rs.getDouble("PRICE");
		}

		// If no price is found, use the provided price in od
		if (latestPrice == 0.0) {
			latestPrice = od.getPrice();
		}

		rs.close();
		priceStatement.close();

		// Step 2: Insert the new record into import_detail
		String sql = "INSERT INTO import_detail (ITEM_IMPORT_ID, ITEM_ID, QUANTITY, PRICE) "
				+ "VALUES ((SELECT MAX(ii.IMPORT_ITEM_ID) FROM import_item ii), ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, od.getItem().getId());
		ps.setInt(2, od.getQuantity());
		ps.setDouble(3, latestPrice);

		status = ps.executeUpdate();
		ps.close();
		c.close();

		return status;
	}
	public static void main(String[] args) throws SQLException {
		DBImport dbImport=new DBImport();
		ImportDetail importDetail=new ImportDetail();
		Item item=new Item();
		ImportItem importItem=new ImportItem();
		User user=new User();
		importItem.setUser(user);
		importItem.getUser().setId(20);
		System.out.println(dbImport.addImportItem(importItem));
	}
}
