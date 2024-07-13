package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.Logging;

public class DBLog {
	public static final Gson gson = new Gson();

	public List<Logging> getAll() {
		List<Logging> result = new ArrayList<Logging>();
		try (Connection connection = connectionDB.connect()) {
			String sql = "SELECT * FROM LOG;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Logging logging = new Logging();
				logging.setId(resultSet.getInt("ID"));
				logging.setTime(resultSet.getString("TIME"));
				logging.setIP(resultSet.getString("IP"));
				logging.setLevel(resultSet.getString("LEVEL"));
				logging.setMessage(resultSet.getString("MESSAGE"));
				logging.setPre_value(gson.fromJson(resultSet.getString("PRE_VALUE"), Object.class));
				logging.setCurrent_value(gson.fromJson(resultSet.getString("CURRENT_VALUE"), Object.class));
				result.add(logging);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("khong ket noi duoc csdl");
		}

		return result;
	}
	public static void main(String[] args) {
		DBLog dbLog = new DBLog();
		List<Logging> list = dbLog.getAll();
		for(Logging logging :list) {
			System.out.println(logging.toString());
		}
	}

}
