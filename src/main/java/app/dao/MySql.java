package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {
    private static String ip = "jdbc:mysql://localhost:3306/";
    // private static String ip = "jdbc:mysql://05c6-14-231-130-155.ngrok-free.app";
	private static String schema = "httm";
	private static String username = "root";
	private static String password = "hiep0312";
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ip + schema, username, password);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	
}
