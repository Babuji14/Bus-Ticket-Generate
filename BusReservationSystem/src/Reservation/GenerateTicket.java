package Reservation;

import java.util.Scanner;
import java.sql.*;

public class GenerateTicket {
	static Scanner sc = new Scanner(System.in);

	static PassengerDetails lastPassenger;
	static TravelDetails lastTravel;
	static BusDetails lastBus;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("******** Welcome to MakeMyTrip ********\n");
		boolean check = true;

		Register r = new Register();
		r.register();

		while (check) {
			System.out.println("\n\n1. Booking Ticket");
			System.out.println("2. Show Ticket");
			System.out.println("3. Exit");
			int ch = sc.nextInt();
			switch (ch) {
			case 1: {
				PassengerDetails p = new PassengerDetails();
				p.passengerDetails();

				// Insert PassengerDetails into database
				try {
					PassenegerDAO dao = new PassenegerDAO();
					dao.insertDetails(p); // Call the DAO to insert passenger details
				} catch (SQLException e) {
					System.out.println("Database insertion failed: " + e.getMessage());
				}

				TravelDetails t = new TravelDetails();
				t.travelDetails();

				// Insert BusDetails into database
				try {
					TravelDAO dao = new TravelDAO();
					dao.insertDetails(t); // Call the DAO to insert passenger details
				} catch (SQLException e) {
					System.out.println("Database insertion failed: " + e.getMessage());
				}

				BusDetails b = new BusDetails();
				b.busDetails();
				b.selectBusOption();

				// Insert BusDetails into database
				try {
					BusDAO dao = new BusDAO();
					dao.insertDetails(b); // Call the DAO to insert passenger details
				} catch (SQLException e) {
					System.out.println("Database insertion failed: " + e.getMessage());
				}

				GenerateTicket g = new GenerateTicket();
				g.generatingTicket(p, t, b);

				break;
			}
			case 2: {
				System.out.print("Enter Bus Number : ");
				String busno = sc.next();
				BusDAO dao = new BusDAO();
				try {
					BusDetails b = dao.checkBusByNumber(busno);
					if (b != null) {
						GenerateTicket g = new GenerateTicket();
						g.showTicket(busno);
					} else {
						System.out.println("Invalid Bus Number. Please try again.");
					}
				} catch (SQLException e) {
					System.out.println("Error fetching bus details: " + e.getMessage());
				}
				break;
			}
			case 3: {
				System.out.println("Bye 👋👋👋");
				check = false;

			}
				break;
			}
		}
	}

	public static void generatingTicket(PassengerDetails p, TravelDetails t, BusDetails b) {

		System.out.println("\n\n******** Ticket ********");

		System.out.println("Name : " + p.getFirstName() + " " + p.getlastName());
		System.out.println("Mobile Number : " + p.getPhNo());

		System.out.println("\nJourney Detail");
		System.out.println("From : " + t.from + "         To : " + t.to);
		System.out.println("Date : " + t.date + "         Duration : " + TravelDetails.num);

		System.out.println("Bus Name  : " + b.selectedBusName + "  Bus Number : AP 03 " + b.selectedBusNo);

		if (b.sleeperAcBill > 0) {
			System.out.println("Total Bill : Rs. " + b.sleeperAcBill + " (Sleeper AC)\n");
		} else if (b.acBill > 0) {
			System.out.println("Total Bill : Rs. " + b.acBill + " (AC)\n");
		} else if (b.nonacBill > 0) {
			System.out.println("Total Bill : Rs. " + b.nonacBill + " (Non-AC)\n");
		} else {
			System.out.println("No bus type selected, bill not available.\n");
		}
	}

	public void showTicket(String busNo) {
		try {
			Connection con = DBConnection.getConnection();

			//passenger details
			String passengerQuery = "SELECT * FROM passenger";
			PreparedStatement pst1 = con.prepareStatement(passengerQuery);
			ResultSet rs1 = pst1.executeQuery();
			rs1.next();
			String firstName = rs1.getString("firstName");
			String lastName = rs1.getString("lastName");
			long phNo = rs1.getLong("phNo");

			//travel details
			String travelQuery = "select * from travel";
			PreparedStatement pst2 = con.prepareStatement(travelQuery);
			ResultSet rs2 = pst2.executeQuery();
			rs2.next();
			String from = rs2.getString("from_place");
			String to = rs2.getString("to_place");
			int duration = rs2.getInt("duration");
			Date date = rs2.getDate("travel_date");

			// bus by busNo
			BusDAO busDao = new BusDAO();
			BusDetails b = busDao.checkBusByNumber(busNo);

			System.out.println("\n\n******** Ticket ********");
			System.out.println("Name : " + firstName + " " + lastName);
			System.out.println("Mobile Number : " + phNo);

			System.out.println("\nJourney Detail");
			System.out.println("From : " + from + "         To : " + to);
			System.out.println("Date : " + date + "         Duration : " + duration);

			System.out.println("Bus Name  : " + b.busName + "  Bus Number : AP 03 " + b.selectedBusNo);

			if (b.sleeperAcBill > 0) {
				System.out.println("Total Bill : Rs. " + b.sleeperAcBill + " (Sleeper AC)\n");
			} else if (b.acBill > 0) {
				System.out.println("Total Bill : Rs. " + b.acBill + " (AC)\n");
			} else if (b.nonacBill > 0) {
				System.out.println("Total Bill : Rs. " + b.nonacBill + " (Non-AC)\n");
			} else {
				System.out.println("No bus type selected, bill not available.\n");
			}

			con.close();
		} catch (SQLException e) {
			System.out.println("Error retrieving ticket data: " + e.getMessage());
		}
	}

}
