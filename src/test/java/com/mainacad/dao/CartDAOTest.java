package com.mainacad.dao;

import com.mainacad.model.Cart;
import com.mainacad.model.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartDAOTest {
  private static List<Cart> carts = new ArrayList<>();
  private static List<User> users = new ArrayList<>();
  private static final Long NEW_CREATION_TIME = 1565024869119L;

  @BeforeAll
  static void setUp() {
    User user = new User("user_login", "test_pass", "test_name", "test_surname");
    user = UserDAO.create(user);
    users.add(user);

    Cart cart = new Cart(1565024867119L, false, user.getId());
    carts.add(cart);
  }

  @AfterAll
  static void tearDown() {
    for (Cart cart: carts) {
      CartDAO.delete(cart);
    }

    for (User user: users) {
      UserDAO.delete(user);
    }
  }

  @Test
  @Order(1)
  void testCreateFind() {
    // checking create
    Cart cart = CartDAO.create(carts.get(0));
    assertNotNull(cart, "Creation method return null object");
    assertNotNull(cart.getId(), "Object id is null. Creation method must update id field of Cart object.");

    // checking findById
    Cart checkedCart = CartDAO.findById(cart.getId());
    assertNotNull(checkedCart, "Cart not found, but it should");
  }

  @Test
  @Order(2)
  void testFindOpenCartByUser() {
    Cart cart = carts.get(0);

    Cart checkedCart = CartDAO.findOpenCartByUser(cart.getUserId());
    assertNotNull(checkedCart, "findOpenCartByUser method return null object");

    assertEquals(cart.getUserId(), checkedCart.getUserId());
    assertEquals(cart.getClosed(), false);

    checkedCart.setClosed(true);
    CartDAO.update(checkedCart);

    Cart checkedCartAfterUpdate = CartDAO.findOpenCartByUser(cart.getUserId());
    if (checkedCartAfterUpdate != null) {
      assertNotEquals(checkedCart.getId(), checkedCartAfterUpdate.getId());
    }
  }

  @Test
  @Order(2)
  void testFindByUser() {
    Cart cart = carts.get(0);

    List<Cart> checkedCartList = CartDAO.findByUser(cart.getUserId());
    assertNotNull(checkedCartList, "findByUser method return null object");

    Cart cartForCheck = null;
    for (Cart cartFromCollection: checkedCartList) {
      assertEquals(cart.getUserId(), cartFromCollection.getUserId());

      if (cartFromCollection.getId() == cart.getId()) {

        cartForCheck = cartFromCollection;
      }
    }
    assertNotNull(cartForCheck, "Test cart not found, but it should");
  }

  @Test
  @Order(2)
  void testUpdate() {
    Cart cart = carts.get(0);

    cart.setCreationTime(NEW_CREATION_TIME);
    Cart checkedCart = CartDAO.update(cart);
    assertNotNull(cart, "Update method return null object");
    assertEquals(NEW_CREATION_TIME, checkedCart.getCreationTime());

    checkedCart = CartDAO.findById(checkedCart.getId());
    assertEquals(NEW_CREATION_TIME, checkedCart.getCreationTime());
  }

  @Test
  @Order(3)
  void testDelete() {
    Cart cart = carts.get(0);
    CartDAO.delete(cart);

    Cart checkedCart = CartDAO.findById(cart.getId());
    assertNull(checkedCart, "Cart not deleted from DB");
  }
}