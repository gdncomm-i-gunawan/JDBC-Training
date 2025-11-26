package org.jdbc.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
  public static void main(String[] args) {
    // Step 1: Load the JDBC Driver
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("PostgreSQL JDBC Driver not found.");
      e.printStackTrace();
      return;
    }

    // Step 2: Define Database URL
    String url = "jdbc:postgresql://localhost:5432/mydatabase";

    // Step 3: Create Connection
    try {
      Connection connection = DriverManager.getConnection(url, "postgres", "password");
      System.out.println("Connected to PostgreSQL database!");

      // Use the 'connection' object for database operations

      Statement s = connection.createStatement();
      ResultSet rs = s.executeQuery("select * from users");
      while(rs.next()) {
          System.out.print("Name:"+rs.getString("name")+", ");
          System.out.println("Age:"+rs.getInt("age"));
      }
      // Don't forget to close the connection when done
      rs.close();
      connection.close();
    } catch (SQLException e) {
      System.out.println("Connection to PostgreSQL database failed.");
      e.printStackTrace();
    }
  }
}