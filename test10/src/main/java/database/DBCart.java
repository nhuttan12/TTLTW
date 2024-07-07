package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Cart;
import model.Item;
import model.User;

public class DBCart {

	// phần chưa làm đang cmt
	
//	public int addITEM(int sId, int iId, int slm) throws SQLException {
//		int status = 0;
//		try (Connection c = connectionDB.connect()) {
//			List<Item> list = new ArrayList<Item>();
//			list = getListItemByCartID(sId);
//			
//			boolean test = false;
//			Item i = new Item();
//			for (Item item : list) {
//				if (item.getId()== iId) {
//					test = true;
//					i = item;
//				} 
//			}
//
//			// neu trong list item do da co sp muon them thi chi update so luong mua
//			if (test) {
//				System.out.println(i.getName() +" "+ i.getQuantity() +" "+ i.getQuantityAvailable());
//				if(i.getQuantity() + slm <= i.getQuantityAvailable()) {
//					updateSLItem(sId,i, i.getQuantity() + slm);
//					updateTotalPriceItem(sId, i, i.getQuantity() + slm);
//					System.out.println("upload");
//				}else {
//					System.out.println(i.getQuantity() + slm);
//					System.out.println(i.getQuantityAvailable());
//					System.out.println("sp khong du sl ton kho");
//				}
//				
//			// neu trong list item do chua co sp muon them thi them sp do vao list item trong cart
//			} else {
//				System.out.println("adddddddddddddddddddddd");
//				System.out.println("item chua co trong cart");
//				String sql = "INSERT INTO CART_ITEMS (SHOPPINGCART_ID, ITEM_ID, QUANTITY_ITEM) " + "VALUES (?, ?, ?);";
//				PreparedStatement ps = c.prepareStatement(sql);
//				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
//				ps.setInt(1, sId);
//				ps.setInt(2, iId);
//				ps.setInt(3, slm);
//				status = ps.executeUpdate();
//				System.out.println("da them");
//				c.close();
//
//			}
//
//		} catch (Exception ex) {
//			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
//			System.exit(0);
//		}
//
//		return status;
//	}
//	
//	public int subITEM(int cId, int iId, int slb) throws SQLException {
//		int status = 0;
//		try (Connection c = connectionDB.connect()) {
//			List<Item> list = new ArrayList<Item>();
//			list = getListItemByCartID(cId);
//			
//			boolean test = false;
//			Item i = new Item();
//			for (Item item : list) {
//				if (item.getId()== iId) {
//					test = true;
//					i = item;
//				} 
//			}
//
//			if (test) {
//				if(i.getQuantity() > slb ) {
//					updateSLItem(sId,i, i.getQuantity() - slb);
//					updateTotalPriceItem(sId, i, i.getQuantity() - slb);
//					System.out.println("upload");
//				}else if(i.getQuantity() <= slb) {
//					deleteITEM(sId, i.getId());
//					System.out.println("xoa sp");
//				}
//				
//			} else {
//				System.out.println("item chua co trong cart");
//				c.close();
//
//			}
//
//		} catch (Exception ex) {
//			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
//			System.exit(0);
//		}
//
//		return status;
//	}

	public int deleteITEM(int uId, int iId) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {

			List<Item> list = new ArrayList<Item>();
			list = getListItemByUserID(uId);
			
			// kiểm tra xem sp muốn xóa có trong giỏ hàng hay không?
			boolean test = false;
			Item i = new Item();
			for (Item item : list) {
				if (item.getId()== iId) {
					test = true;
					i = item;
				} 
			}
			// nếu sp muốn xóa có trong giỏ hàng
			if (test) {
				String sql = "DELETE FROM cart  WHERE ITEM_ID = ? and USER_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setInt(1, iId);
				ps.setInt(2, uId);
				status = ps.executeUpdate();
			}else {
				System.out.println("item khong co trong gio hang");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
	//khi khách hàng thực hiện thành toán rồi
	public int deleteCartByUserID(int UserId) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {

			String sql = "DELETE FROM cart where USER_ID = ?;";
			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setInt(1, UserId);
			status = ps.executeUpdate();
		

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
	//khi khách hàng muốn xóa 1 sp khỏi giỏ hàng
	public int deleteCartByCartID(int cartID) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {

			String sql = "DELETE FROM cart where CART_ID = ?;";
			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setInt(1, cartID);
			status = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
// UPLOAD Số lượng mua của từng item trong cart
	public int updateSLItem(int uId, Item item, int slm) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
			if(slm >= 0) {
				String sql = "UPDATE cart SET QUANTITY = ? WHERE ITEM_ID = ? and USER_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setInt(1, slm);
				ps.setInt(2, item.getId());
				ps.setInt(3, uId);
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
	
	public int updateTotalPriceItem(int uId, Item item, int slm) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
				String sql = "UPDATE cart SET TOTAL_PRICE = ? WHERE ITEM_ID = ? and USER_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setDouble(1, (item.getPrice()) * slm);
				ps.setInt(2, item.getId());
				ps.setInt(3, uId);
				System.out.println("update thanh cong");
				status = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	
	public Item getItem(int user_id, int item_id) throws SQLException {
		Item i = null;

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT i.* FROM  cart ci JOIN items i on i.ITEM_ID = ci.ITEM_ID  WHERE ci.USER_ID = ? && ci.ITEM_ID = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"

			ps.setInt(1, user_id);
			ps.setInt(2, item_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int ITEM_ID = rs.getInt("ITEM_ID");
				String ITEM_NAME = rs.getString("ITEM_NAME");
				double PRICE = rs.getDouble("PRICE");
				double DISCOUNT = rs.getDouble("DISCOUNT");
				String DISCRIPTION = rs.getString("DISCRIPTION");
				int CATEGORY_ID = rs.getInt("CATEGORY_ID");
				String IMAGES = rs.getString("IMAGES");
				

//				i = new Item(ITEM_ID, ITEM_NAME, PRICE, IMAGES, DISCOUNT, CATEGORY_ID, DISCRIPTION);
				i.setId(ITEM_ID);
				i.setName(ITEM_NAME);
				i.setPrice(PRICE);
				i.setDiscount(DISCOUNT);
				i.setDiscription(DISCRIPTION);
				i.getCategory().setId(CATEGORY_ID);
				i.setImageName(IMAGES);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return i;
	}

	public List<Item> getListItemByUserID(int user_id) throws SQLException {
		System.out.println("toiiiiiiiiiiiiiiiiiii");
		List<Item> list = new ArrayList<Item>();
		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT i.* FROM  cart ci JOIN items i on i.ITEM_ID = ci.ITEM_ID  WHERE ci.USER_ID = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"

			ps.setInt(1, user_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int ITEM_ID = rs.getInt("ITEM_ID");
				String ITEM_NAME = rs.getString("ITEM_NAME");
				double PRICE = rs.getDouble("PRICE");
				double DISCOUNT = rs.getDouble("DISCOUNT");
				String DISCRIPTION = rs.getString("DISCRIPTION");
				int CATEGORY_ID = rs.getInt("CATEGORY_ID");
				String IMAGES = rs.getString("IMAGES");
				
<<<<<<< HEAD
				Item i = new Item(ITEM_ID, ITEM_NAME, PRICE, IMAGES, DISCOUNT, CATEGORY_ID, DISCRIPTION);
=======
				Item i = new Item();
				i.setId(ITEM_ID);
				i.setName(ITEM_NAME);
				i.setPrice(PRICE);
				i.setDiscount(DISCOUNT);
				i.setDiscription(DISCRIPTION);
				i.getCategory().setId(CATEGORY_ID);
				i.setImageName(IMAGES);
>>>>>>> origin/code
				list.add(i);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
//dùng để show giỏ hàng
	public List<Cart> getListCartByUserID(int user_id) throws SQLException {
		System.out.println("toiiiiiiiiiiiiiiiiiii");
		List<Cart> list = new ArrayList<Cart>();
		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT * FROM  cart ci JOIN items i on i.ITEM_ID = ci.ITEM_ID  WHERE ci.USER_ID = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"

			ps.setInt(1, user_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int CART_ID = rs.getInt("CART_ID");
				int USER_ID = rs.getInt("USER_ID");
				int ITEM_ID = rs.getInt("ITEM_ID");
				int QUANTITY = rs.getInt("QUANTITY");
				double TOTAL_PRICE = rs.getDouble("TOTAL_PRICE");
				
				Cart i = new Cart(CART_ID, USER_ID, ITEM_ID, QUANTITY, TOTAL_PRICE) ;
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
//		DBCartItems c = new DBCartItems();
//		ShoppingCart s = new ShoppingCart(1);
//		Item i = new Item(1,"mi y sot vang",35.5,3);
//		System.out.println("--------------truoc khi add");
//		List<Item> l = c.getListItemByCartID(2);
//		for (Item item : l) {
//			System.out.println(item.getName() + item.getQuantity()+" "+ item.getPrice());
//		}
//		c.addITEM(s.getId(), i.getId(),3);
////		c.deleteITEM(s, i);
//		System.out.println("----------------sau khi add");
//		List<Item> m = c.getListItemByCartID(2);
//		for (Item item : m) {
//			System.out.println(item.getName() + item.getQuantity()+" "+ item.getPrice());
//		}
		
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
		
		DBCart cart = new DBCart();
		List<Cart> a= cart.getListCartByUserID(1);
		for (Cart c : a) {
			System.out.println(c.getUserId());
			
		}
		cart.deleteCartByUserID(1);
		System.out.println("đã xóa");
		List<Cart> b= cart.getListCartByUserID(1);
		for (Cart c : b) {
			System.out.println(c.getUserId());
			
		}
	}

}
