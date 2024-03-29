package com.mainacad.dao;

import com.mainacad.model.Cart;
import com.mainacad.model.Item;
import com.mainacad.model.User;
import com.mainacad.model.Order;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderDAOTest {
  private static List<Item> items = new ArrayList<>();
  private static List<Cart> carts = new ArrayList<>();
  private static List<User> users = new ArrayList<>();
  private static List<Order> orders = new ArrayList<>();

  @BeforeAll
  static void setUp() {
    // crete test item
    Item item = new Item("test_item", "Test item", 20000);
    item = ItemDAO.create(item);
    items.add(item);

    // create test user
    User user = new User("user_login", "test_pass", "test_name", "test_surname");
    user = UserDAO.create(user);
    users.add(user);

    // create test cart
    Cart cart = new Cart(1565024867119L, false, user.getId());
    cart = CartDAO.create(cart);
    carts.add(cart);

    Order order = new Order(item.getId(), 2, cart.getId());
    orders.add(order);
  }

  @AfterAll
  static void tearDown() {
    for (Cart cart: carts) {
      CartDAO.delete(cart);
    }

    for (Order order: orders) {
      OrderDAO.delete(order);
    }

    for (Item item: items) {
      ItemDAO.delete(item);
    }

    for (User user: users) {
      UserDAO.delete(user);
    }
  }

  @Test
  @org.junit.jupiter.api.Order(1)
  void testCreateFind() {
    // checking create
    Order order = OrderDAO.create(orders.get(0));
    assertNotNull(order, "Creation method return null object");
    assertNotNull(order.getId(), "Object id is null. Creation method must update id field of Order object.");

    // checking findById
    Order checkedOrder = OrderDAO.findById(order.getId());
    assertNotNull(checkedOrder, "Order not found, but it should");
  }

  @Test
  @org.junit.jupiter.api.Order(2)
  void testUpdate() {
    Order order = orders.get(0);
    order.setAmount(3);
    Order checkedOrder = OrderDAO.update(order);
    assertNotNull(order, "Update method return null object");
    assertEquals(3, checkedOrder.getAmount());

    checkedOrder = OrderDAO.findById(checkedOrder.getId());
    assertEquals(3, checkedOrder.getAmount());
  }

  @Test
  @org.junit.jupiter.api.Order(2)
  void testFindByCart() {
    Cart cart = carts.get(0);
    Order order = orders.get(0);

    List<Order> checkedOrdersList = OrderDAO.findByCart(cart.getId());
    assertNotNull(checkedOrdersList, "findByUser method return null object");

    Order checkedOrder = null;
    for (Order orderFromCollection: checkedOrdersList) {
      assertEquals(cart.getId(), orderFromCollection.getCartId());

      if (orderFromCollection.getId() == order.getId()) {
        checkedOrder = orderFromCollection;
      }
    }
    assertNotNull(checkedOrder, "Test order not found, but it should");
  }

  @Test
  @org.junit.jupiter.api.Order(2)
  void testFindClosedOrdersByUserAndPeriod() {
    User user = users.get(0);
    Cart cart = carts.get(0);
    Order order = orders.get(0);

    Long from = cart.getCreationTime() - 1000;
    Long to = cart.getCreationTime() + 1000;

    List<Order> checkedOrdersList = OrderDAO.findClosedOrdersByUserAndPeriod(user, from, to);
    assertNotNull(checkedOrdersList, "findClosedOrdersByUserAndPeriod method return null object");

    Order checkedOrder = null;
    for (Order orderFromCollection: checkedOrdersList) {
      Cart checkedCart = CartDAO.findById(orderFromCollection.getCartId());
      assertEquals(user.getId(), checkedCart.getUserId());
      assertEquals(true, checkedCart.getClosed());

      if (orderFromCollection.getId() == order.getId()) {
        checkedOrder = orderFromCollection;
      }
    }
    assertNull(checkedOrder, "Test order found, but it shouldn't"); // we haven't closed orders

    cart.setClosed(true);
    CartDAO.update(cart);

    checkedOrdersList = OrderDAO.findClosedOrdersByUserAndPeriod(user, from, to);

    for (Order orderFromCollection: checkedOrdersList) {
      Cart checkedCart = CartDAO.findById(orderFromCollection.getCartId());
      assertEquals(checkedCart.getUserId(), user.getId());
      assertEquals(true, checkedCart.getClosed());

      if (orderFromCollection.getId() == order.getId()) {
        checkedOrder = orderFromCollection;
      }
    }
    assertNotNull(checkedOrder, "Test order not found, but it should"); // we haven`t closed orders

    from = cart.getCreationTime() + 500;

    checkedOrdersList = OrderDAO.findClosedOrdersByUserAndPeriod(user, from, to);

    checkedOrder = null;
    for (Order orderFromCollection: checkedOrdersList) {
      Cart checkedCart = CartDAO.findById(orderFromCollection.getCartId());
      assertEquals(user.getId(), checkedCart.getUserId());
      assertEquals(true, checkedCart.getClosed());

      if (orderFromCollection.getId() == order.getId()) {
        checkedOrder = orderFromCollection;
      }
    }
    assertNull(checkedOrder, "Test order found, but it shouldn't"); // we haven't closed orders within that period
  }

  @Test
  @org.junit.jupiter.api.Order(3)
  void testDelete() {
    Order order = orders.get(0);
    OrderDAO.delete(order);

    Order checkedOrder = OrderDAO.findById(order.getId());
    assertNull(checkedOrder, "Order not deleted from DB");
  }
}