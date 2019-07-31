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
    // test create
    assertNull(users.get(0).getId());
    User createdUser = UserDAO.create(users.get(0));
    assertNotNull(createdUser);
    assertNotNull(createdUser.getId());

    // test findById
    User checkedUser = UserDAO.findById(createdUser.getId());
    assertNotNull(checkedUser);
    assertNotNull(checkedUser.getId());

    // another tests
    testUpdate(checkedUser);
    testFindUserByLogin(createdUser);
    testFindAll();
    testDelete(createdUser, checkedUser);
  }

  private void testUpdate(User checkedUser) {
    checkedUser.setPassword("new_password");
    checkedUser = UserDAO.update(checkedUser);

    User checkedUserFromDB = UserDAO.findById(checkedUser.getId());
    assertNotNull(checkedUserFromDB);
    assertEquals("new_password", checkedUserFromDB.getPassword());
  }

  private void testFindUserByLogin(User createdUser) {
    List<User> checkedUsers = UserDAO.findByLogin(TEST_USER_LOGIN);
    assertNotNull(checkedUsers);

    User checkedUser = null;
    for (int i = 0; i < checkedUsers.size(); i++) {
      if (checkedUsers.get(i).getId() == createdUser.getId()) {
        checkedUser = checkedUsers.get(i);
      }
    }

    assertNotNull(checkedUser, "User not found by login");
  }

  private void testFindAll() {
    List<User> checkedUsers = UserDAO.findAll();

    assertNotNull(checkedUsers);
    assertEquals(true, checkedUsers.size() > 0);
    for (User user: checkedUsers) {
      assertNotNull(user, "All_Users collection has null objects");
    }
  }

  private void testDelete(User createdUser, User checkedUser) {
    UserDAO.delete(checkedUser);

    User deletedUser = UserDAO.findById(createdUser.getId());
    assertNull(deletedUser, "User delete failed");
  }
}