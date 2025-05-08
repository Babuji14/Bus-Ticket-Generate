package Reservation;

import java.sql.*;

public class BusDAO {
	String busNo;
	String busName;
	int acBill;
	int nonacBill;
	int sleeperAcBill;

	public void insertDetails(BusDetails b) throws SQLException {
		Connection con = DBConnection.getConnection();

		String query = "insert into bus(busNo, busName, acBill,nonacBill, sleeperACBill) values (?,?,?,?,?)";

		busNo = b.selectedBusNo;
		busName = b.busName;
		acBill = b.acBill;
		nonacBill = b.nonacBill;
		sleeperAcBill = b.sleeperAcBill;

		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, b.selectedBusNo);
		pst.setString(2, b.busName);
		pst.setInt(3, BusDetails.acBill);
		pst.setInt(4, BusDetails.nonacBill);
		pst.setInt(5, BusDetails.sleeperAcBill);

		int rows = pst.executeUpdate();
		System.out.println(rows + " row(s) inserted.");

		con.close();
	}

	public BusDetails checkBusByNumber(String busNo) throws SQLException {
	    Connection con = DBConnection.getConnection();
	    String query = "SELECT * FROM bus WHERE busno = ?";
	    PreparedStatement pst = con.prepareStatement(query);
	    pst.setString(1, busNo);
	    ResultSet rs = pst.executeQuery();

	    if (rs.next()) {
	        BusDetails b = new BusDetails();
	        b.selectedBusNo = rs.getString("busno");
	        b.busName = rs.getString("busname");

	        // Set the bill values
	        b.acBill = rs.getInt("acbill");
	        b.sleeperAcBill = rs.getInt("sleeperacbill");
	        b.nonacBill = rs.getInt("nonacbill");

	        return b;
	    }

	    return null;
	}


}
