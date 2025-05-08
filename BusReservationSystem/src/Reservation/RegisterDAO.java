package Reservation;

import java.sql.*;

public class RegisterDAO {
	public void insertDetails(Register r) throws SQLException {
		Connection con = DBConnection.getConnection();

		String query = "insert into register(name, password) values (?,?)";
		String select = "select * from register";

		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, r.getName());
		pst.setString(2, r.getPassword());

		int rows = pst.executeUpdate();
		
		con.close();
	}

	public boolean checkLogin(String name, String password) throws SQLException {
		Connection con = DBConnection.getConnection();
		String query = "SELECT * FROM register WHERE name = ? AND password = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();

		return rs.next(); // true if user exists with matching name and password
	}
}
