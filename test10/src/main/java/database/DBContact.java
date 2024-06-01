package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Contact;
import model.User;

public class DBContact {
	
	public int addContact(Contact e) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
			String sql = "INSERT INTO CONTACT (NAME, EMAIL, MESSAGE) "
					+ "VALUES (?, ?, ?);";
			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setString(1, e.getName());
			ps.setString(2, e.getEmail());
			ps.setString(3, e.getMessage());
			
			status = ps.executeUpdate();

			c.close();

		} catch (Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
			System.exit(0);
		}

		return status;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
