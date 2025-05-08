package Reservation;

import java.util.Random;

public class PassengerDetails {
	
	private String firstName;
	private String lastName;
	private Long phNo;

	public String getFirstName() {
		return firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public Long getPhNo() {
		return phNo;
	}
	
	public void passengerDetails() {
		System.out.println("\n\n******** Passenger Detail ********");
		System.out.print("First Name : ");
		firstName = GenerateTicket.sc.next();

		System.out.print("Last Name : ");
		lastName = GenerateTicket.sc.next();

		System.out.print("Enter Mobile Number : ");
		phNo = GenerateTicket.sc.nextLong();
	}
}