package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

import log.MyLog;
import model.Logging;

public class connectionDB {
	public static Connection connect(){
//		String Path="D:\\HOCTAP\\PROJECT\\database\\";
        //System.out.println(path);
        Connection c = null;
//        String url = "jdbc:sqlite:"+Path+"database.db";
//        System.out.println("url="+url );
        try {
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");  
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	c=DriverManager.getConnection("jdbc:mysql://localhost:3306/csdl","root","");
            System.out.println("connection database!!!!" );
        } catch (  SQLException e ) {
            System.out.println( e.getClass().getName() + ": " + e.getMessage() );
//            MyLog.insertLog(new Logging())
        }
        return c;
    }
	
	
	public static void main(String[] args) throws SQLException {
		Connection a = connectionDB.connect();
		PreparedStatement preparedStatement = a.prepareStatement("SELECT * FROM ITEMS");
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.first());
			
		}
		
	}
	
}
