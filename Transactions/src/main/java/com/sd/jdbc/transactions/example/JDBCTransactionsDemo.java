package com.sd.jdbc.transactions.example;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.sql.*;

public class JDBCTransactionsDemo {
  public final static Logger logger = Logger.getLogger(JDBCTransactionsDemo.class);
  // JDBC Driver & Database URL
  private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  private final static String JDBC_DB_URL = "jdbc:mysql://localhost:3306/tutorialDb?zeroDateTimeBehavior=convertToNull";
  // JDBC DB usuario y passwd
  private final static String JDBC_USER = "root";
  private final static String JDBC_PASS = "";
  private static Connection connObj;

  public static void connectDb() {
    try {
      connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
      logger.info("\n=======CONEXIóN DB ABIERTA=======\n");
    } catch (Exception sqlException) {
      sqlException.printStackTrace();
    }
  }

  public static void disconnectDb() {
    try {
      connObj.close();
      logger.info("\n=======CONEXIÓN DB CERRADA=======\n");
    } catch (Exception sqlException) {
      sqlException.printStackTrace();
    }
  }

  public static void showTableRecords(String table_name) throws SQLException {
    ResultSet rsObj = null;
    Statement stmtObj = connObj.createStatement();
    rsObj = stmtObj.executeQuery("select user_id, user_name, created_date from " + table_name + ";");
    if (!rsObj.next()) {
      logger.info("No hay registros en la tabla\n");
    } else {
      logger.info("Id: " + rsObj.getInt("user_id") + ", Name: " + rsObj.getString("user_name") + ", Joining Date: " + rsObj.getInt("created_date") + "\n");
    }
  }

  public static void saveUserDetails(int userId, String userName, String sysName) {
    PreparedStatement insertStatement = null, updateStatement = null;
    try {
      connObj.setAutoCommit(false);

      logger.info("\n=======Insertando datos en la tabla=======\n");
      String insertTableSQL = "insert into user_table (user_id, user_name, created_by, created_date) VALUES (?, ?, ?, ?);";

      insertStatement = connObj.prepareStatement(insertTableSQL);
      insertStatement.setInt(1, userId);
      insertStatement.setString(2, userName);
      insertStatement.setString(3, sysName);
      insertStatement.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
      insertStatement.executeUpdate();        // Registro almacenado en este momento? sí o no?, si no, entocnes en qué momento ?

      logger.info("\n=======Updating Value In The Table=======\n");
      String updateTableSQL = "update user_table set user_name =? where user_id = ?";

      updateStatement = connObj.prepareStatement(updateTableSQL);

      // Esta línea causa una excepción, por qué ? Qué pasa con los datos insertados en la tabla anteriormente en la sentencia "insertTableSQL" ?
      updateStatement.setString(1, "Tres tristes tigres tragaban trigo en tres tristes trastos causando una excepción");

      updateStatement.setInt(2, userId);
      updateStatement.executeUpdate();

      connObj.commit();
      showTableRecords("user_table");
    } catch (Exception sqlException) {
      try {
        connObj.rollback();
        logger.info("\n=======!Db Exception! Ejecutando Roll Back =======\n");
        showTableRecords("user_table");
      } catch (SQLException sqlEx) {
        sqlEx.printStackTrace();
      }
    } finally {
      try {
        if (insertStatement != null) {
          insertStatement.close();
        }
        if (updateStatement != null) {
          updateStatement.close();
        }
        connObj.setAutoCommit(true);
      } catch (Exception sqlException) {
        sqlException.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    BasicConfigurator.configure();
    logger.info("Transacción Demo");
    connectDb();
    saveUserDetails(1, "SD", "sys_admin");
    disconnectDb();
  }
}

