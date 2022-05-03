package rmi;

import java.sql.*;

public class DBManager {
  private static DBManager _instance = null;
  private Connection _con = null;

  public DBManager() {
    _con = getMySQLConnection();
  }

  public static synchronized DBManager getInstance() {
    if (_instance == null) {
      _instance = new DBManager();
    }
    return _instance;
  }

  /**
   * Connection to SQLServer Database
   */
  private static Connection getSQLServerConnection() {
    Connection con = null;
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      String URL = "jdbc:sqlserver://localhost;databaseName=NID;user=sa;password=123;";
      con = DriverManager.getConnection(URL);
    } catch (Exception e) {
      System.out.println(e);
    }
    return con;
  }

  /**
   * Connection to MySQL Database
   */
  private static Connection getMySQLConnection() {
    Connection con = null;
    try {
      String strCon = "jdbc:mysql://127.0.0.1/Province?user=root&password=";
      con = DriverManager.getConnection(strCon);
    } catch (SQLException se) {
      System.out.println(se);
    }
    return con;
  }

  /**
   * Connection to Microsoft Access
   */
  private static Connection getMsAccessConnection() {
    Connection con = null;
    try {
      Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
      con = DriverManager.getConnection("jdbc:ucanaccess://D:/Programacion/mars/RMIServerSide/Province.mdb");
    } catch (Exception se) {
      System.out.println(se);
    }
    return con;
  }

  private static Connection getFirebirdDBConnection() {
    Connection con = null;
    try {
      Class.forName("org.firebirdsql.jdbc.FBDriver");
      con = DriverManager.getConnection(
          "jdbc:firebirdsql://localhost:3050/D:/Programacion/mars/RMIServerSide/PROVINCE.FDB",
          "SYSDBA",
          "masterkey");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        con.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return con;
  }

  public Connection getConnection() {
    return _con;
  }
}
