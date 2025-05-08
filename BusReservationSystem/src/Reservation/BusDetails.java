package Reservation;

import java.util.Random;

public class BusDetails {
	static int acCost = 50;
	static int acBill = 0;
	static int nonacCost = 25;
	static int nonacBill = 0;
	static int sleeperAc = 100;
	static int sleeperAcBill = 0;
	String selectedBusNo;
	public String selectedBusName;
	static int i;

	
	// Bus fields
	String busNo;
	String busName;
	String driverName;
	int capacity;
	int bookedSeats;
	int optionNumber;

	static String[] numberOfArray = new String[5];
	static int count = 0; // To keep track of how many bus numbers are added to the array

	// Constructor
	public BusDetails() {
		this.capacity = busCapacity();
		this.bookedSeats = generateBookedSeats(capacity);
		this.busNo = String.valueOf(busNoGenerate());
		this.busName = getRandomBusName();
		this.driverName = getRandomDriverName();
	}

	public String busNoGenerate() {
		Random rand = new Random();
		String busNumber = String.valueOf(1000 + rand.nextInt(9000));

		// Store the bus number in the array if there's space
		if (count < numberOfArray.length) {
			numberOfArray[count] = busNumber; // Store bus number
			count++; // Increment the counter to track the number of stored bus numbers
		}
		return busNumber;
	}

	// Generate random capacity
	public int busCapacity() {
		Random rand = new Random();
		return 30 + rand.nextInt(21); // 30–50
	}

	// Randomly booked seats between 0 and capacity
	private int generateBookedSeats(int capacity) {
		Random rand = new Random();
		return rand.nextInt(capacity + 1);
	}

	// Random bus name
	private String getRandomBusName() {
		String[] names = { "Orange Travels", "SRS Bus", "VRL Express", "Greenline", "Bharathi", "VTS" };
		return names[new Random().nextInt(names.length)];
	}

	// Random driver name
	private String getRandomDriverName() {
		String[] drivers = { "Pavan", "Suresh", "Naresh", "Dinesh", "Vinay", "Abinav", "Badrinath" };
		return drivers[new Random().nextInt(drivers.length)];
	}

	// Show bus info
	public void showBusDetails(int optionNumber) {
		System.out.println("\nBus Option " + optionNumber + ":");
		System.out.println("Bus Number   : " + busNo);
		System.out.println("Bus Name     : " + busName);
		System.out.println("Driver Name  : " + driverName);
		System.out.println("Capacity     : " + capacity + " seats");
		System.out.println("Booked Seats : " + bookedSeats + " seats");
		System.out.println("Available    : " + (capacity - bookedSeats) + " seats");
	}

	// Display 3 to 5 random bus options
	private void displayBusOptions() {
		Random rand = new Random();
		int busCount = rand.nextInt(3) + 3; // returns 3, 4, or 5

		System.out.println("\n******** Bus Details ********");

		for (int i = 1; i <= busCount; i++) {
			BusDetails bus = new BusDetails();
			bus.showBusDetails(i);
		}
	}

	// Main bus selection logic
	public void busDetails() {
		char yn;

		System.out.println("\n\n******** Bus Selection ********");

		while (true) {
			System.out.print("Do You Want Sleeper Bus (y/n): ");
			yn = Character.toLowerCase(GenerateTicket.sc.next().charAt(0));

			switch (yn) {
			case 'y': {
				sleeperAcBill = sleeperAc * TravelDetails.num;
				System.out.println("\nAvailable Sleeper Buses:");
				displayBusOptions();
				return;
			}
			case 'n': {
				char actype;

				while (true) {
					System.out.print("In General You Want AC or Non-AC (y/n): ");
					actype = Character.toLowerCase(GenerateTicket.sc.next().charAt(0));

					switch (actype) {
					case 'y': {
						acBill = acCost * TravelDetails.num;
						System.out.println("\nAvailable AC Buses:");
						displayBusOptions();
						return;
					}
					case 'n': {
						nonacBill = nonacCost * TravelDetails.num;
						System.out.println("\nAvailable Non-AC Buses:");
						displayBusOptions();
						return;
					}
					default:
						System.out.println("Invalid choice! Please choose 'y' for AC or 'n' for Non-AC.");
					}
				}
			}
			default:
				System.out.println("Invalid input! Please choose 'y' or 'n' for Sleeper Bus.");
			}
		}
	}

	// Fix for selectBusOption to correctly handle bus selection
	public void selectBusOption() {
		while (true) {
			System.out.print("Enter the Bus Number to Generate the Ticket: ");
			selectedBusNo = GenerateTicket.sc.next();

			boolean busFound = false;
			for (i = 0; i < count; i++) {
				if (numberOfArray[i].equals(selectedBusNo)) { // Check if bus number is in the array
					busFound = true;
				    selectedBusName = busName;
					break;
				}
			}

			if (busFound) {
				if (capacity == bookedSeats) {
					System.out.println("Sorry, Bus Seats Are not available. Please try another bus.");
				} else {
					break; // Exit the loop once a valid bus number is entered and ticket is generated
				}
			} else {
				System.out.println("Bus number does not match! Please try again.");
			}
		}
	}
}
