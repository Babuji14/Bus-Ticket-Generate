package Reservation;

import java.sql.*;

public class DBConnection {
	private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String userName = "scott";
	private static final String password = "tiger";

	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, userName, password);
	}
}
