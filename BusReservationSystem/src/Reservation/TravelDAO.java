package Reservation;

import java.sql.*;
import java.util.Date;

public class TravelDAO {

	String from_place;
	String to_place;
	int duration;
	java.sql.Date travelDate;

	public void insertDetails(TravelDetails t) throws SQLException {
		Connection con = DBConnection.getConnection();

		String query = "INSERT INTO travel(from_place, to_place, duration, travel_date) VALUES (?, ?, ?, ?)";

		from_place = t.from;
		to_place = t.to;
		duration = TravelDetails.num;

		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, t.from);
		pst.setString(2, t.to);
		pst.setInt(3, TravelDetails.num);
		
		travelDate = new java.sql.Date(t.date.getTime());
		pst.setDate(4, travelDate); // Corrected index to 4

		pst.executeUpdate();

		con.close();
	}
}
