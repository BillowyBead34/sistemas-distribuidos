package com.mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author HikingCarrot7
 */
public class DBConnection {

  public static Connection createConnection() {
    Connection con = null;
    String url = "jdbc:mysql://localhost:3306/customers?zeroDateTimeBehavior=convertToNull"; //Copia y pega el enlace completo desde el NETBEANS
    String username = "root";
    String password = "password";
    try {
      try {
        Class.forName("com.mysql.jdbc.Driver"); // mysql driver MySQL 5
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      con = DriverManager.getConnection(url, username, password); //connect MySQL database
      System.out.println("Printing connection object " + con);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return con;
  }
}
