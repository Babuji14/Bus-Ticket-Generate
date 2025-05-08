package Reservation;

import java.sql.*;

public class PassenegerDAO {

	String firstName;
	String lastName;
	long phNo;

	public void insertDetails(PassengerDetails p) throws SQLException {
		Connection con = DBConnection.getConnection();

		String query = "insert into passenger(firstName, lastName, phNo) values (?,?,?)";

		firstName = p.getFirstName();
		lastName = p.getlastName();
		phNo = p.getPhNo();

		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, firstName);
		pst.setString(2, lastName);
		pst.setLong(3, phNo);

		int rows = pst.executeUpdate();
		con.close();
	}
}
