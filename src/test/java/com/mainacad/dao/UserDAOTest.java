package com.mainacad.dao;

import com.mainacad.model.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
  @Order(1)
  void testCreateFind() {
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
/*    testUpdate(checkedUser);
    testFindUserByLogin(createdUser);
    testFindAll();
    testDelete(createdUser, checkedUser);*/
  }

  @Test
  @Order(2)
  void testUpdate() {
    User checkedUser = users.get(0);
    checkedUser.setPassword("new_password");
    checkedUser = UserDAO.update(checkedUser);

    User checkedUserFromDB = UserDAO.findById(checkedUser.getId());
    assertNotNull(checkedUserFromDB);
    assertEquals("new_password", checkedUserFromDB.getPassword());
  }

  @Test
  @Order(2)
  void testFindUserByLogin() {
    User createdUser = users.get(0);
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

  @Test
  @Order(2)
  void testFindAll() {
    List<User> checkedUsers = UserDAO.findAll();

    assertNotNull(checkedUsers);
    assertEquals(true, checkedUsers.size() > 0);
    for (User user: checkedUsers) {
      assertNotNull(user, "All_Users collection has null objects");
    }
  }

  @Test
  @Order(3)
  void testDelete() {
    User checkedUser = users.get(0);
    UserDAO.delete(checkedUser);

    User deletedUser = UserDAO.findById(checkedUser.getId());
    assertNull(deletedUser, "User delete failed");
  }
}