package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Item;
import model.User;
import java.util.ArrayList;
import java.util.List;

import com.oracle.wls.shaded.org.apache.bcel.generic.RETURN;

public class DBUser {

	// cho chức năng Login
	public User checkUSER(String taikhoan, String matkhau) {

		String sql = "SELECT *  FROM USERS WHERE USER_NAME=? AND PASSWORD=?";
		try (Connection c = connectionDB.connect()) {
			System.out.println("ket noi ok!!!");
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, taikhoan);
			ps.setString(2, matkhau);
			System.out.println(sql);
			ResultSet rs = ps.executeQuery();
//vttoan@hcmuaf.edu.vn

			while (rs.next()) {
				int USER_ID = rs.getInt("USER_ID");
				String USER_NAME = rs.getString("USER_NAME");
				String PASSWORD = rs.getString("PASSWORD");
				String NAME = rs.getString("NAME");
				String PHONE = rs.getString("PHONE");
				String GENDER = rs.getString("GENDER");
				String MESSAGE = rs.getString("MESSAGE");
				int ROLE = rs.getInt("ROLE");
				int SHOPPINGCART_ID = rs.getInt("SHOPPINGCART_ID");
				String EMAIL = rs.getString("EMAIL");
				int STATUS = rs.getInt("STATUS");
				User user = new User(USER_ID, NAME, PASSWORD, NAME, PHONE, GENDER, MESSAGE, ROLE, SHOPPINGCART_ID,
						EMAIL, STATUS);
				System.out.println("ok!!!");
				return user;
			}
			rs.close();
		} catch (Exception ex) {
			System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
			ex.printStackTrace();
		}
		return null;

	}

	public int addUSER(User e) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
			User a = getUserByUserName(e.getUserName());
			if (a == null) {
				String sql = "INSERT INTO USERS (USER_NAME, PASSWORD, NAME, PHONE, GENDER,ROLE,EMAIL,STATUS) "
						+ "VALUES (?, ?, ?, ?, ?, 1,?,1);";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setString(1, e.getUserName());
				ps.setString(2, e.getPassword());
				ps.setString(3, e.getName());
				ps.setString(4, e.getPhone());
				ps.setString(5, e.getGender());
				ps.setString(6, e.getEmail());
				status = ps.executeUpdate();

				c.close();
			} else {
				System.out.println("ten tai khoan da ton tai");
			}

		} catch (Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
			System.exit(0);
		}

		return status;
	}

	public int deleteUSER(User e) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
			User a = getUserByUserName(e.getUserName());
			if ((a != null) && (a.getRole() != 2)) {
				String sql = "DELETE FROM USERS  WHERE USER_ID = ?;";

				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setInt(1, e.getId());

				status = ps.executeUpdate();

				c.close();
			} else {
				System.out.println("ten tai khoan khong ton tai");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public int deleteUSERByID(int userId) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
			User a = getUserByID(userId);
			if ((a != null) && (a.getRole() != 2)) {
				String sql = "DELETE FROM USERS  WHERE USER_ID = ?;";

				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setInt(1, userId);

				status = ps.executeUpdate();

				c.close();
			} else {
				System.out.println("ten tai khoan khong ton tai");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public int update(User e) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
			User a = getUserByID(e.getId());
			if (a != null) {
				String sql = "UPDATE USERS SET USER_NAME = ?, PASSWORD = ?, NAME = ?, PHONE = ?, GENDER = ?, MESSAGE = ? WHERE USER_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setString(1, e.getUserName());
				ps.setString(2, e.getPassword());
				ps.setString(3, e.getName());
				ps.setString(4, e.getPhone());
				ps.setString(5, e.getGender());
				ps.setString(6, e.getMessage());
				ps.setInt(7, a.getId());
				status = ps.executeUpdate();
			} else {
				System.out.println("khong tim thay user");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public int updateByStatus(int id) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
			User a = getUserByID(id);
			if (a != null) {
				String sql = "UPDATE USERS SET STATUS=? WHERE USER_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				if(a.getStatus()==1) {
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setInt(1, 2);
				}else if(a.getStatus()==2) {
					ps.setInt(1, 1);
				}
				ps.setInt(2, id);
				status = ps.executeUpdate();
			} else {
				System.out.println("khong tim thay user");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public int updateCart(User e, int sid) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
			User a = getUserByID(e.getId());
			if (a != null) {
				String sql = "UPDATE USERS SET SHOPPINGCART_ID = ? WHERE USER_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setInt(1, sid);
				ps.setInt(2, e.getId());

				status = ps.executeUpdate();
			} else {
				System.out.println("khong tim thay user");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public int updatePas(int uid, String pas) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {

			String sql = "UPDATE USERS SET PASSWORD = ? WHERE USER_ID = ?;";
			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"

			ps.setString(1, pas);
			ps.setInt(2, uid);
			status = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public int update1(int id, String name, String phone, String gender, String email) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
			User a = getUserByID(id);
			if (a != null) {
				String sql = "UPDATE USERS SET NAME = ?, PHONE = ?, GENDER = ?, EMAIL = ? WHERE USER_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"

				ps.setString(1, name);
				ps.setString(2, phone);
				ps.setString(3, gender);
				ps.setString(4, email);
				ps.setInt(5, id);
				status = ps.executeUpdate();
			} else {
				System.out.println("khong tim thay user");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public User getUserByUserName(String usn) throws SQLException {
		User b = null;

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT * FROM USERS  WHERE USER_NAME = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setString(1, usn);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int ID = rs.getInt("USER_ID");
				String USER_NAME = rs.getString("USER_NAME");
				String PASSWORD = rs.getString("PASSWORD");
				String NAME = rs.getString("NAME");
				String PHONE = rs.getString("PHONE");
				String GENDER = rs.getString("GENDER");
				String MESSAGE = rs.getString("MESSAGE");
				int ROLE = rs.getInt("ROLE");
				int SHOPPINGCART_ID = rs.getInt("SHOPPINGCART_ID");
				String EMAIL = rs.getString("EMAIL");
				int STATUS = rs.getInt("STATUS");

				b = new User(ID, USER_NAME, PASSWORD, NAME, PHONE, GENDER, MESSAGE, ROLE, SHOPPINGCART_ID, EMAIL,
						STATUS);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	public User getUserByID(int id) throws SQLException {
		User b = null;

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT * FROM USERS  WHERE USER_ID = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int ID = rs.getInt("USER_ID");
				String USER_NAME = rs.getString("USER_NAME");
				String PASSWORD = rs.getString("PASSWORD");
				String NAME = rs.getString("NAME");
				String PHONE = rs.getString("PHONE");
				String GENDER = rs.getString("GENDER");
				String MESSAGE = rs.getString("MESSAGE");
				int ROLE = rs.getInt("ROLE");
				int SHOPPINGCART_ID = rs.getInt("SHOPPINGCART_ID");
				String EMAIL = rs.getString("EMAIL");
				int STATUS = rs.getInt("STATUS");

				b = new User(ID, USER_NAME, PASSWORD, NAME, PHONE, GENDER, MESSAGE, ROLE, SHOPPINGCART_ID, EMAIL,
						STATUS);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	public List<User> getUserByRole(int role) throws SQLException {
		List<User> b = new ArrayList<User>();

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT * FROM USERS  WHERE ROLE = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setInt(1, role);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int ID = rs.getInt("USER_ID");
				String USER_NAME = rs.getString("USER_NAME");
				String PASSWORD = rs.getString("PASSWORD");
				String NAME = rs.getString("NAME");
				String PHONE = rs.getString("PHONE");
				String GENDER = rs.getString("GENDER");
				String MESSAGE = rs.getString("MESSAGE");
				int ROLE = rs.getInt("ROLE");
				int SHOPPINGCART_ID = rs.getInt("SHOPPINGCART_ID");
				String EMAIL = rs.getString("EMAIL");
				int STATUS = rs.getInt("STATUS");

				User a = new User(ID, USER_NAME, PASSWORD, NAME, PHONE, GENDER, MESSAGE, ROLE, SHOPPINGCART_ID, EMAIL,
						STATUS);
				b.add(a);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	public int getUserId(User e) throws SQLException {
		int sid = 0;

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT USER_ID FROM USERS  WHERE USER_NAME = ? AND PASSWORD = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			System.out.println(e.getUserName());
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setString(1, e.getUserName());
			ps.setString(2, e.getPassword());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				sid = rs.getInt("USER_ID");

			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sid;
	}

	public static void main(String[] args) throws SQLException {
		DBUser l = new DBUser();
//		User a = l.checkUSER("PERSON1", "123");
//		User a = new User(1,"PERSON1", "123","Thu","03","nu","");
//		User b = new User(2,"PERSON2", "456","Thao","02","nu","");
		// System.out.println(l.update(b));
//		System.out.println(l.deleteUSER("PERSON"));
//		System.out.println(l.getUserByUserName("PERSON").getId());
		// System.out.println(a.getUserName());
//
//		System.out.println(
//				l.getUserByID(1));
//		l.update1(new User(1, "tuuuuuuuuu", "999999999"));
//		System.out.println(
//				l.getUserByID(1));
		User a =l.getUserByID(8);
		System.out.println(a.getStatus());
		l.updateByStatus(8);
		a=l.getUserByID(8);
		System.out.println(a.getStatus());
	}

}
