package com.mainacad.dao;

import com.mainacad.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
  public static User create(User user){
    String statement = "INSERT INTO users(login, password, first_name, second_name)" +
            "VALUES(?,?,?,?)";

    String curIdStatement = "SELECT currval(pg_get_serial_sequence('users','id'))";

    try (Connection connection = ConnectionToDB.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(statement);
         Statement seqStatement = connection.createStatement()) {

      preparedStatement.setString(1, user.getLogin());
      preparedStatement.setString(2, user.getPassword());
      preparedStatement.setString(3, user.getFirstName());
      preparedStatement.setString(4, user.getSecondName());

      preparedStatement.executeUpdate();

      ResultSet resultSet = seqStatement.executeQuery(curIdStatement);
      while (resultSet.next()) {
        Integer id = resultSet.getInt(1);
        user.setId(id);

        return user;
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static User update(User user) {
    String statement = "UPDATE users SET login=?, password=?, first_name=?, second_name=? WHERE id=?";

    try (Connection connection = ConnectionToDB.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(statement)) {

      preparedStatement.setString(1, user.getLogin());
      preparedStatement.setString(2, user.getPassword());
      preparedStatement.setString(3, user.getFirstName());
      preparedStatement.setString(4, user.getSecondName());
      preparedStatement.setInt(5, user.getId());

      preparedStatement.executeUpdate();

      return user;

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static User findById(Integer id) {
    String statement = "SELECT * FROM users WHERE id=?";

    try (Connection connection = ConnectionToDB.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(statement)) {

      preparedStatement.setInt(1, id);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        User user = getUserFromResultSetItem(resultSet);

        return user;
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static List<User> findByLogin(String login) {
    List<User> users = new ArrayList<>();

    String statement = "SELECT * FROM users WHERE login=?";

    try (Connection connection = ConnectionToDB.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(statement)) {

      preparedStatement.setString(1, login);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        users.add(getUserFromResultSetItem(resultSet));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return users;
  }

  public static List<User> findAll() {
    List<User> users = new ArrayList<>();

    String statement = "SELECT * FROM users";

    try (Connection connection = ConnectionToDB.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(statement)) {

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        users.add(getUserFromResultSetItem(resultSet));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return users;
  }

  public static void delete(User user) {
    String statement = "DELETE FROM users WHERE id=?";

    try (Connection connection = ConnectionToDB.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(statement)) {

      preparedStatement.setInt(1, user.getId());
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static User getUserFromResultSetItem(ResultSet resultSet) throws SQLException {
    User user = new User();

    user.setId(resultSet.getInt("id"));
    user.setLogin(resultSet.getString("login"));
    user.setPassword(resultSet.getString("password"));
    user.setFirstName(resultSet.getString("first_name"));
    user.setSecondName(resultSet.getString("second_name"));

    return user;
  }
}
