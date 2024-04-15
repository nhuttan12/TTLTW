package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.ShoppingCart;
import model.User;

public class DBCartItems {

	public int addITEM(int sId, int iId, int slm) throws SQLException {
		int status = 0;
		try (Connection c = connectionDB.connect()) {
			List<Item> list = new ArrayList<Item>();
			list = getListItemByCartID(sId);
			
			boolean test = false;
			Item i = new Item();
			for (Item item : list) {
				if (item.getId()== iId) {
					test = true;
					i = item;
				} 
			}

			// neu trong list item do da co sp muon them thi chi update so luong mua
			if (test) {
				System.out.println(i.getName() +" "+ i.getQuantity() +" "+ i.getQuantityAvailable());
				if(i.getQuantity() + slm <= i.getQuantityAvailable()) {
					updateSLItem(sId,i, i.getQuantity() + slm);
					updateTotalPriceItem(sId, i, i.getQuantity() + slm);
					System.out.println("upload");
				}else {
					System.out.println(i.getQuantity() + slm);
					System.out.println(i.getQuantityAvailable());
					System.out.println("sp khong du sl ton kho");
				}
				
			// neu trong list item do chua co sp muon them thi them sp do vao list item trong cart
			} else {
				System.out.println("adddddddddddddddddddddd");
				System.out.println("item chua co trong cart");
				String sql = "INSERT INTO CART_ITEMS (SHOPPINGCART_ID, ITEM_ID, QUANTITY_ITEM) " + "VALUES (?, ?, ?);";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setInt(1, sId);
				ps.setInt(2, iId);
				ps.setInt(3, slm);
				status = ps.executeUpdate();
				System.out.println("da them");
				c.close();

			}

		} catch (Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
			System.exit(0);
		}

		return status;
	}
	
	public int subITEM(int sId, int iId, int slb) throws SQLException {
		int status = 0;
		try (Connection c = connectionDB.connect()) {
			List<Item> list = new ArrayList<Item>();
			list = getListItemByCartID(sId);
			
			boolean test = false;
			Item i = new Item();
			for (Item item : list) {
				if (item.getId()== iId) {
					test = true;
					i = item;
				} 
			}

			if (test) {
				if(i.getQuantity() > slb ) {
					updateSLItem(sId,i, i.getQuantity() - slb);
					updateTotalPriceItem(sId, i, i.getQuantity() - slb);
					System.out.println("upload");
				}else if(i.getQuantity() <= slb) {
					deleteITEM(sId, i.getId());
					System.out.println("xoa sp");
				}
				
			} else {
				System.out.println("item chua co trong cart");
				c.close();

			}

		} catch (Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
			System.exit(0);
		}

		return status;
	}

	public int deleteITEM(int sId, int iId) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {

			List<Item> list = new ArrayList<Item>();
			list = getListItemByCartID(sId);
			
			boolean test = false;
			Item i = new Item();
			for (Item item : list) {
				if (item.getId()== iId) {
					test = true;
					i = item;
				} 
			}
			if (test) {
				String sql = "DELETE FROM CART_ITEMS  WHERE ITEM_ID = ? and SHOPPINGCART_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setInt(1, iId);
				ps.setInt(2, sId);
				status = ps.executeUpdate();
			}else {
				System.out.println("item khong co trong gio hang");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
	
	public int deleteAllITEM(int shoppingCartId) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {

			String sql = "DELETE FROM CART_ITEMS  WHERE SHOPPINGCART_ID = ?;";
			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setInt(1, shoppingCartId);
			status = ps.executeUpdate();
		

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public int updateSLItem(int sId, Item item, int slm) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
			if(slm >= 0) {
				String sql = "UPDATE CART_ITEMS SET QUANTITY_ITEM = ? WHERE ITEM_ID = ? and SHOPPINGCART_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setInt(1, slm);
				ps.setInt(2, item.getId());
				ps.setInt(3, sId);
				System.out.println("update thanh cong");
				status = ps.executeUpdate();
			}
			else {
				System.out.println("so luong khong du");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
	
	public int updateTotalPriceItem(int sId, Item item, int slm) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
				String sql = "UPDATE CART_ITEMS SET TOTAL_PRICE = ? WHERE ITEM_ID = ? and SHOPPINGCART_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setDouble(1, (item.getUnitPrice()) * slm);
				ps.setInt(2, item.getId());
				ps.setInt(3, sId);
				System.out.println("update thanh cong");
				status = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	
	public Item getItem(int cart_id, int item_id) throws SQLException {
		Item i = null;

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT * FROM  CART_ITEMS WHERE ci.SHOPPINGCART_ID = ? ci.ITEM_ID = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"

			ps.setInt(1, cart_id);
			ps.setInt(2, item_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
//		                int CART_ID = rs.getInt("SHOPPINGCART_ID");
//		                int USER_ID = rs.getInt("USER_ID");
				int ITEM_ID = rs.getInt("ITEM_ID");
				String ITEM_NAME = rs.getString("ITEM_NAME");
				double PRICE = rs.getDouble("TOTAL_PRICE");
//				int QUANTITY_AVAILABLE = rs.getInt("QUANTITY_AVAILABLE");
				int QUANTITY_ITEM = rs.getInt("QUANTITY_ITEM");
				i = new Item(ITEM_ID, ITEM_NAME, PRICE, QUANTITY_ITEM);

			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return i;
	}

	public List<Item> getListItemByCartID(int cart_id) throws SQLException {
		System.out.println("toiiiiiiiiiiiiiiiiiii");
		List<Item> list = new ArrayList<Item>();
		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT * FROM  CART_ITEMS ci JOIN ITEMS i on i.ITEM_ID = ci.ITEM_ID	JOIN SHOPPINGCARTS s on s.SHOPPINGCART_ID = ci.SHOPPINGCART_ID  WHERE s.SHOPPINGCART_ID = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"

			ps.setInt(1, cart_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int ITEM_ID = rs.getInt("ITEM_ID");
				String ITEM_NAME = rs.getString("ITEM_NAME");
				double UNITPRICE = rs.getDouble("UNITPRICE");
				double PRICE = rs.getDouble("TOTAL_PRICE");
				int QUANTITY_AVAILABLE = rs.getInt("QUANTITY_AVAILABLE");
				int QUANTITY_ITEM = rs.getInt("QUANTITY_ITEM");
				String TYPE = rs.getString("TYPE");
				String IMAGES = rs.getString("IMAGES");
				
				Item i = new Item(ITEM_ID, ITEM_NAME, UNITPRICE, PRICE, QUANTITY_AVAILABLE, QUANTITY_ITEM, TYPE, IMAGES);
				list.add(i);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DBCartItems c = new DBCartItems();
		ShoppingCart s = new ShoppingCart(1);
		Item i = new Item(1,"mi y sot vang",35.5,3);
		System.out.println("--------------truoc khi add");
		List<Item> l = c.getListItemByCartID(2);
		for (Item item : l) {
			System.out.println(item.getName() + item.getQuantity()+" "+ item.getPrice());
		}
		c.addITEM(s.getId(), i.getId(),3);
//		c.deleteITEM(s, i);
		System.out.println("----------------sau khi add");
		List<Item> m = c.getListItemByCartID(2);
		for (Item item : m) {
			System.out.println(item.getName() + item.getQuantity()+" "+ item.getPrice());
		}
		
//		System.out.println("--------------truoc khi xoa");
//		List<Item> l = c.getListItemByCartID(1);
//		for (Item item : l) {
//			System.out.println(item.getName() + item.getQuantity());
//		}
//		c.deleteITEM(s, i);
//		System.out.println("----------------sau khi xoa");
//		List<Item> m = c.getListItemByCartID(1);
//		for (Item item : m) {
//			System.out.println(item.getName() + item.getQuantity());
//		}

	}

}
