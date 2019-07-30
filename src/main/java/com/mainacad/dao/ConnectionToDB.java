package com.mainacad.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

class ConnectionToDB {
  String name;
  private static Logger logger = Logger.getLogger(ConnectionToDB.class.getName());
  private static final String DB_URL = "jdbc:postgresql://localhost:5432/shop_db_ma";
  private static final String DB_USER = "postgres";
  private static final String DB_PASSWORD = "dedicated";

  protected static Connection getConnection() {
    Connection connection = null;

    String url;
    try {
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    } catch (SQLException e) {
      logger.severe("Connection to db failed");
    }

    return connection;
  }

}
