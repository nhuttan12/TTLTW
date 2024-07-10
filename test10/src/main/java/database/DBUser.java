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
				String NAME = rs.getString("FULL_NAME");
				String PHONE = rs.getString("PHONE");
				int GENDER = rs.getInt("GENDER");
				String MESSAGE = rs.getString("MESSAGE");
				int ROLE = rs.getInt("ROLE_ID");
				String EMAIL = rs.getString("EMAIL");
				int STATUS = rs.getInt("STATUS_USER_ID");
				User user = new User(USER_ID, NAME, PASSWORD, NAME, PHONE, GENDER, MESSAGE, ROLE,
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
	
	public User checkUSERByEmail(String email) {

		String sql = "SELECT *  FROM USERS WHERE EMAIL=?";
		try (Connection c = connectionDB.connect()) {
			System.out.println("ket noi ok!!!");
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, email);
			System.out.println(sql);
			ResultSet rs = ps.executeQuery();
//vttoan@hcmuaf.edu.vn

			while (rs.next()) {
				int USER_ID = rs.getInt("USER_ID");
				String USER_NAME = rs.getString("USER_NAME");
				String PASSWORD = rs.getString("PASSWORD");
				String NAME = rs.getString("FULL_NAME");
				String PHONE = rs.getString("PHONE");
				int GENDER = rs.getInt("GENDER");
				String MESSAGE = rs.getString("MESSAGE");
				int ROLE = rs.getInt("ROLE_ID");
				String EMAIL = rs.getString("EMAIL");
				int STATUS = rs.getInt("STATUS_USER_ID");
				User user = new User(USER_ID, NAME, PASSWORD, NAME, PHONE, GENDER, MESSAGE, ROLE,
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
				String sql = "INSERT INTO USERS (FULL_NAME, USER_NAME, PASSWORD, PHONE, GENDER, ROLE_ID, EMAIL, MESSAGE, STATUS_USER_ID) "
						+ "VALUES (?, ?, ?, ?, ?, 1,?,?,1);";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setString(1, e.getName());
				ps.setString(2, e.getUserName());
				ps.setString(3, e.getPassword());
				ps.setString(4, e.getPhone());
				ps.setInt(5, e.getGender());
				ps.setString(6, e.getEmail());
				ps.setString(7, e.getMessage());
				status = ps.executeUpdate();
//				System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
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
	
	public int addUSERForFB(String name, String email) throws SQLException {
		int status = 0;
		
		try (Connection c = connectionDB.connect()) {
			User a = getUserByEmail(email);
			if (a == null) {
				String sql = "INSERT INTO USERS (FULL_NAME, USER_NAME, EMAIL, ROLE_ID, STATUS_USER_ID) "
						+ "VALUES (?, ?, ?, 1,1);";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setString(1, name);
				ps.setString(2, name);
				ps.setString(3, email);
				status = ps.executeUpdate();
//				System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
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
				String sql = "UPDATE USERS SET FULL_NAME = ?, USER_NAME = ?, PASSWORD = ?, PHONE = ?, GENDER = ?, EMAIL = ?, MESSAGE = ? WHERE USER_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
				ps.setString(1, e.getName());
				ps.setString(2, e.getUserName());
				ps.setString(3, e.getPassword());
				ps.setString(4, e.getPhone());
				ps.setInt(5, e.getGender());
				ps.setString(6, e.getEmail());
				ps.setString(7, e.getMessage());
				ps.setInt(8, a.getId());
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
				String sql = "UPDATE USERS SET STATUS_USER_ID=? WHERE USER_ID = ?;";
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

	public int update1(int id, String name, String phone, String gender, String email, String message) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
			User a = getUserByID(id);
			if (a != null) {
				String sql = "UPDATE USERS SET FULL_NAME = ?, PHONE = ?, GENDER = ?, EMAIL = ?, MESSAGE = ? WHERE USER_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"

				ps.setString(1, name);
				ps.setString(2, phone);
				ps.setString(3, gender);
				ps.setString(4, email);
				ps.setString(5, message);
				ps.setInt(6, id);
				status = ps.executeUpdate();
			} else {
				System.out.println("khong tim thay user");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
	public int updateInfor(int id, String fullname,String phone, String gender) throws SQLException {
		int status = 0;

		try (Connection c = connectionDB.connect()) {
			User a = getUserByID(id);
			if (a != null) {
				String sql = "UPDATE USERS SET FULL_NAME = ?, PHONE = ?, GENDER = ? WHERE USER_ID = ?;";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, fullname);
				ps.setString(2, phone);
				ps.setString(3, gender);
				ps.setInt(4, id);
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
				String FULL_NAME = rs.getString("FULL_NAME");
				String USER_NAME = rs.getString("USER_NAME");
				String PASSWORD = rs.getString("PASSWORD");
				String PHONE = rs.getString("PHONE");
				int GENDER = rs.getInt("GENDER");
				int ROLE_ID = rs.getInt("ROLE_ID");
				String EMAIL = rs.getString("EMAIL");
				String MESSAGE = rs.getString("MESSAGE");
				int STATUS_USER_ID = rs.getInt("STATUS_USER_ID");

				b = new User(ID, USER_NAME, PASSWORD, FULL_NAME, PHONE, GENDER, MESSAGE, ROLE_ID, EMAIL, STATUS_USER_ID);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	public User getUserByEmail(String email) throws SQLException {
		User b = null;

		try (Connection c = connectionDB.connect()) {

			String sql = "SELECT * FROM USERS  WHERE EMAIL = ?;";

			PreparedStatement ps = c.prepareStatement(sql);
			// '"+title+"',"+Authorid+" , 18022018, 0000, "+Isbn+"
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int ID = rs.getInt("USER_ID");
				String FULL_NAME = rs.getString("FULL_NAME");
				String USER_NAME = rs.getString("USER_NAME");
				String PASSWORD = rs.getString("PASSWORD");
				String PHONE = rs.getString("PHONE");
				int GENDER = rs.getInt("GENDER");
				int ROLE_ID = rs.getInt("ROLE_ID");
				String EMAIL = rs.getString("EMAIL");
				String MESSAGE = rs.getString("MESSAGE");
				int STATUS_USER_ID = rs.getInt("STATUS_USER_ID");

				b = new User(ID, USER_NAME, PASSWORD, FULL_NAME, PHONE, GENDER, MESSAGE, ROLE_ID, EMAIL, STATUS_USER_ID);
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
				String FULL_NAME = rs.getString("FULL_NAME");
				String USER_NAME = rs.getString("USER_NAME");
				String PASSWORD = rs.getString("PASSWORD");
				String PHONE = rs.getString("PHONE");
				int GENDER = rs.getInt("GENDER");
				int ROLE_ID = rs.getInt("ROLE_ID");
				String EMAIL = rs.getString("EMAIL");
				String MESSAGE = rs.getString("MESSAGE");
				int STATUS_USER_ID = rs.getInt("STATUS_USER_ID");

				b = new User(ID, USER_NAME, PASSWORD, FULL_NAME, PHONE, GENDER, MESSAGE, ROLE_ID, EMAIL, STATUS_USER_ID);
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
				String FULL_NAME = rs.getString("FULL_NAME");
				String USER_NAME = rs.getString("USER_NAME");
				String PASSWORD = rs.getString("PASSWORD");
				String PHONE = rs.getString("PHONE");
				int GENDER = rs.getInt("GENDER");
				int ROLE_ID = rs.getInt("ROLE_ID");
				String EMAIL = rs.getString("EMAIL");
				String MESSAGE = rs.getString("MESSAGE");
				int STATUS_USER_ID = rs.getInt("STATUS_USER_ID");

				User a = new User(ID, USER_NAME, PASSWORD, FULL_NAME, PHONE, GENDER, MESSAGE, ROLE_ID, EMAIL, STATUS_USER_ID);
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

			String sql = "SELECT USER_ID FROM USERS WHERE USER_NAME = ? AND PASSWORD = ?;";

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
		User a =l.getUserByID(1);
		System.out.println(a.getStatus());
		l.updateByStatus(1);
		a=l.getUserByID(1);
		System.out.println(a.getStatus());
	}

}
