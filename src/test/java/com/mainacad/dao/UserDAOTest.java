package com.mainacad.dao;

import com.mainacad.model.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserDAOTest {
  private static List<User> users = new ArrayList<>();

  @BeforeAll
  static void setUp() {
    User user = new User("test_user", "test_pass", "test_name", "test_surname");
    users.add(user);
  }

  @AfterAll
  static void tearDown() {
    for (User user: users) {
      if (user.getId() != null) {
        UserDAO.delete(user);
      }
    }
  }

  @Test
  void testCreateFindAndDelete() {
    assertNull(users.get(0).getId());
    User user = UserDAO.create(users.get(0));
    assertNotNull(user);
    assertNotNull(user.getId());

    User checkedUser = UserDAO.findById(user.getId());
    assertNotNull(checkedUser);
    assertNotNull(checkedUser.getId());

    UserDAO.delete(checkedUser);

    User deletedUser = UserDAO.findById(user.getId());
    assertNull(deletedUser);
  }
}