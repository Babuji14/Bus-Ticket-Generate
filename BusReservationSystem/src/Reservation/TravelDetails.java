package Reservation;

import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;

public class TravelDetails {
	Date date;
	static int num;
	String from;
	String to;

	public void travelDetails() {
		System.out.println("\n\n******** Journey Detail ********");
		System.out.print("From : ");
		from = GenerateTicket.sc.next();
		System.out.print("To : ");
		to = GenerateTicket.sc.next();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false); // Strict format checking

		while (true) {
			System.out.print("Enter date (dd/MM/yyyy): ");
			String dateInput = GenerateTicket.sc.next();

			try {
				date = dateFormat.parse(dateInput);

				// Check if the date is after today
				Date today = new Date();
				if (date.after(today)) {
					break; // valid future date
				} else {
					System.out.println("Date must be in the future. Please enter a valid future date.");
				}
			} catch (ParseException e) {
				System.out.println("Invalid date format. Please use dd/MM/yyyy.");
			}
		}

		Random ran = new Random();
		num = 1 + ran.nextInt(4); // 0 to 4
		return;
	}
}
