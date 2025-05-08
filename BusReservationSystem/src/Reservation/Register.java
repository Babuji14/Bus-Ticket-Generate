package Reservation;

import java.sql.SQLException;

public class Register {
	private String name;
	private String password;

	private String lname;
	private String lpassword;

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getlName() {
		return lname;
	}

	public String getlPassword() {
		return lpassword;
	}


	public void register() {
		boolean check = true;
		while (check) {
			System.out.println("1. Sign up \n2. Login");
			int ch = GenerateTicket.sc.nextInt();

			switch (ch) {
			case 1: {
				System.out.print("******** SIGN UP ********\n");
				System.out.print("Enter Name : ");
				name = GenerateTicket.sc.next();
				System.out.print("Enter Password : ");
				password = GenerateTicket.sc.next();

				try {
					RegisterDAO dao = new RegisterDAO();
					dao.insertDetails(this); // Use current object
				} catch (SQLException e) {
					System.out.println("Database insertion failed: " + e.getMessage());
				}

				// Proceed to login
				System.out.println("\n******** LOGIN ********");
				login();
				break;
			}
			case 2: {
				System.out.println("******** LOGIN ********");
				login();
				break;
			}
			default: {
				System.out.println("Invalid option.");
				break;
			}
			}
			check = false;
		}
	}

	private void login() {
		System.out.print("Enter Name : ");
		lname = GenerateTicket.sc.next();
		System.out.print("Enter Password : ");
		lpassword = GenerateTicket.sc.next();
		
		 try {
		        RegisterDAO dao = new RegisterDAO();
		        boolean isValid = dao.checkLogin(lname, lpassword);

		        if (isValid) {
		            System.out.println("Login Successful!\n");
		        } else {
		            System.out.println("Invalid credentials. Please try again.\n");
		            login(); // Prompt again
		        }
		    } catch (SQLException e) {
		        System.out.println("Error during login: " + e.getMessage());
		    }

		// You can validate login here by querying the DB
		// Example (optional):
		// SELECT * FROM register WHERE name=? AND password=?
		// And display login success or failure message
	}
}
