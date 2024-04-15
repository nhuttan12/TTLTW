package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Item;
import model.ShoppingCart;
import model.User;

public class DBShoppingCart {
	
	
	public ShoppingCart getCartByID(int user_id) throws SQLException {
		ShoppingCart i = null;

//		try (Connection c = connectionDB.connect()) {
		Connection c = connectionDB.connect();

		String sql = "SELECT * FROM  USERS u JOIN SHOPPINGCARTS s on u.USER_ID = s.USER_ID  WHERE u.USER_ID = ?;";

		PreparedStatement ps = c.prepareStatement(sql);
		// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
		ps.setInt(1, user_id);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int CART_ID = rs.getInt("SHOPPINGCART_ID");
			int USER_ID = rs.getInt("USER_ID");

			i = new ShoppingCart(CART_ID);
		}
		rs.close();
//		
		return i;
	}

	public int addShoppingCart(ShoppingCart e) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
			String sql = "INSERT INTO SHOPPINGCARTS (USER_ID) " + "VALUES (?);";
			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setInt(1, e.getUser().getId());

			status = ps.executeUpdate();

			c.close();

		} catch (Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
			System.exit(0);
		}

		return status;
	}

	public int getCartId(int uid) throws SQLException {
		int sid = 0;

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT SHOPPINGCART_ID FROM SHOPPINGCARTS  WHERE USER_ID = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setInt(1, uid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				sid = rs.getInt("SHOPPINGCART_ID");

			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sid;
	}

}
