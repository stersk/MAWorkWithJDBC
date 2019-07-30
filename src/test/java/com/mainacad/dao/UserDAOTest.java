package com.mainacad.dao;

import com.mainacad.model.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserDAOTest {
  private static List<User> users = new ArrayList<>();
  private static String TEST_USER_LOGIN = "test_user";

  @BeforeAll
  static void setUp() {
    User user = new User(TEST_USER_LOGIN, "test_pass", "test_name", "test_surname");
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
    // checking update
    assertNull(users.get(0).getId());
    User createdUser = UserDAO.create(users.get(0));
    assertNotNull(createdUser);
    assertNotNull(createdUser.getId());

    // checking findById
    User checkedUser = UserDAO.findById(createdUser.getId());
    assertNotNull(checkedUser);
    assertNotNull(checkedUser.getId());

    // checking update
    checkedUser.setPassword("new_password");
    checkedUser = UserDAO.update(checkedUser);

    assertNotNull(checkedUser);
    assertEquals("new_password", checkedUser.getPassword());

    User checkedUserFromDB = UserDAO.findById(checkedUser.getId());
    assertNotNull(checkedUser);
    assertEquals("new_password", checkedUser.getPassword());

    // checking findByLogin
    List<User> checkedUsers = UserDAO.findByLogin(TEST_USER_LOGIN);
    assertNotNull(checkedUser);
    assertEquals(true, checkedUsers.size() >= 1);

    checkedUser = null;
    for (int i = 0; i < checkedUsers.size(); i++) {
      if (checkedUsers.get(i).getId() == createdUser.getId()) {
        checkedUser = checkedUsers.get(i);
      }
    }

    assertNotNull(checkedUser);

    // checking findAll
    checkedUsers = UserDAO.findAll();

    assertNotNull(checkedUsers);
    assertEquals(true, checkedUsers.size() > 0);
    for (User user: checkedUsers) {
      assertNotNull(user, "All users collection has null objects");
    }

    // checking delete
    UserDAO.delete(checkedUser);

    User deletedUser = UserDAO.findById(createdUser.getId());
    assertNull(deletedUser);
  }
}