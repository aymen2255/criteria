package fr.treeptik.criteria.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionUtils {

	
	private static Connection connection ;
	
	public static  Connection getConnection() throws ClassNotFoundException, SQLException{
		
		
		if (connection==null || connection.isClosed() ){
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/simple_orm", "root", "root");
			
			connection.setAutoCommit(false);
		}
		
		
		return connection;
	}
	
	
	
	
}



