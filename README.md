# 🚌 Bus Ticket Generation System

A web-based Java application to register, log in, and book bus tickets. This system allows users to manage ticket bookings securely and stores all data in an Oracle database.

## 🚀 Features

- 👤 **User Registration & Login**
- 🎫 **Book Bus Tickets**
- 🧾 **View Booked Tickets**
- 🔐 **Secure Authentication**
- 🗃️ **Oracle 10g Database Connectivity**

## 🛠️ Technologies Used

- **Backend:** Java 
- **Database:** Oracle 10g
- **JDBC:** Java Database Connectivity


## 💾 Database Setup (Oracle 10g)

1. Create a table for user and ticket details.
2. Use JDBC to connect using `DBConnection.java`.
3. Example connection string:
   ```java
   Class.forName("oracle.jdbc.driver.OracleDriver");
   Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "username", "password");


