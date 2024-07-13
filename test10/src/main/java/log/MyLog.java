package log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;

import database.connectionDB;
import jakarta.servlet.http.HttpServletRequest;
import log.LevelLog;
import model.Logging;

public class MyLog {
/**
 * new Logging(String level,String message)
 * new Logging(String level,String message,String pre_value,String current_value)
 * @param logging
 * @param httpServletRequest
 * @return true:thanh cong ; false:khong thanh cong
 */
public static boolean insertLog(Logging logging,HttpServletRequest httpServletRequest) {
	try (Connection connection = connectionDB.connect()){
	String sql="INSERT INTO LOG(TIME,IP,LEVEL,MESSAGE,PRE_VALUE,CURRENT_VALUE) VALUES(?,?,?,?,?,?);";
		PreparedStatement preparedStatement =connection.prepareStatement(sql);
		LocalDateTime time=LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String time_curent=time.format(formatter);
		Gson gson = new Gson();
		preparedStatement.setString(1, time_curent);
		preparedStatement.setString(2, IP.getClientIP(httpServletRequest ));
		preparedStatement.setString(3, logging.getLevel());
		preparedStatement.setString(4, logging.getMessage());
		preparedStatement.setString(5,gson.toJson(logging.getPre_value()) );
		preparedStatement.setString(6, gson.toJson(logging.getCurrent_value()));
		int result =preparedStatement.executeUpdate(); 
			if(result>0) {
				return true;
			}
		
	}catch(

	Exception e)
	{
		System.out.println(e.getClass().getSimpleName()+"khong ket noi csdl " );
	}
	return false;
}


public static void main(String[] args) {
	  try {
          InetAddress localhost = InetAddress.getLocalHost();
          System.out.println("Localhost IP Address: " + localhost.getHostAddress());
      } catch (UnknownHostException e) {
          e.printStackTrace();
      }
	
}

}
