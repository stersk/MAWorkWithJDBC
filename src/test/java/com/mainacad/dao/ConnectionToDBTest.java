package com.mainacad.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionToDBTest {
  @Test
  void testGetConnection() {
    Connection connection = ConnectionToDB.getConnection();
    assertNotNull(connection);

    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}