package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDB {
	public static Connection connect(){
		String Path="D:\\HOCTAP\\PROJECT\\database\\";
        //System.out.println(path);
        Connection c = null;
        String url = "jdbc:sqlite:"+Path+"database.db";
//        System.out.println("url="+url );
        try {
            try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            c = DriverManager.getConnection(url);
//            System.out.println("connection database!!!!" );
        } catch (  SQLException e ) {
//            System.out.println("loi connection database" );
            System.out.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return c;
    }
	
	
	public static void main(String[] args) {
//		connectionDB c = new connectionDB();
		Connection a = connectionDB.connect();
		System.out.println(a);
		
	}
	
}
