package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Item;

public class DBCategory {
	public List<Category> getCategoryList() {
		List<Category> b = new ArrayList<Category>();
		Connection c = connectionDB.connect();
		String sql = "SELECT c.CATEGORY_ID as 'id', c.CATEGORY_NAME as 'name' FROM `category` c;";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category category=new Category();
				int id=rs.getInt("id");
				String categoryName=rs.getString("name");
				category.setId(id);
				category.setCategoryName(categoryName);
				b.add(category);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
}
