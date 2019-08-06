package com.mainacad.dao;

import com.mainacad.model.Cart;
import com.mainacad.model.Item;
import com.mainacad.model.User;
import com.mainacad.model.Order;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOTest {
  private static List<Item> items = new ArrayList<>();
  private static List<Cart> carts = new ArrayList<>();
  private static List<User> users = new ArrayList<>();
  private static List<Order> orders = new ArrayList<>();

  @BeforeAll
  static void setUp() {
    // crete test item
    Item item = null;
    List<Item> itemList = ItemDAO.findByItemCode("test_item");
    if (itemList.isEmpty()) {
      item = new Item("test_item", "Test item", 20000);
      item = ItemDAO.create(item);
    } else {
      item = itemList.get(0);
    }
    items.add(item);

    // create test user
    User user = null;
    List<User> listUsers = UserDAO.findByLogin("user_login");
    if (listUsers.isEmpty()) {
      user = new User("user_login", "test_pass", "test_name", "test_surname");
      user = UserDAO.create(user);
    } else {
      user = listUsers.get(0);
    }
    users.add(user);

    // create test cart
    Cart cart = null;
    List<Cart> cartList = CartDAO.findByUser(user.getId());
    if (cartList.isEmpty()) {
      cart = new Cart(1565024867119L, false, user.getId());
      cart = CartDAO.create(cart);
    } else {
      cart = cartList.get(0);
      if (cart.getClosed()) {
        cart.setClosed(false);
        CartDAO.update(cart);
      }
    }
    carts.add(cart);

    Order order = new Order(item.getId(), 2, cart.getId());
    orders.add(order);
  }

  @AfterAll
  static void tearDown() {
    for (Cart cart: carts) {
      cart.setClosed(true);
      CartDAO.update(cart);
    }

    for (Order order: orders) {
      OrderDAO.delete(order);
    }

    for (Item item: items) {
      ItemDAO.delete(item);
    }
  }

  @Test
  void testCreateFindAndDelete() {
    // checking create
    Order order = OrderDAO.create(orders.get(0));
    assertNotNull(order, "Creation method return null object");
    assertNotNull(order.getId(), "Object id is null. Creation method must update id field of Order object.");

    // checking findById
    Order checkedOrder = OrderDAO.findById(order.getId());
    assertNotNull(checkedOrder, "Order not found, but it should");

    // another tests
    testUpdate(checkedOrder);
    testFindByCart(carts.get(0), checkedOrder);
    testFindClosedOrdersByUserAndPeriod(users.get(0), carts.get(0), checkedOrder);
    testDelete(checkedOrder);
  }

  void testUpdate(Order order) {
    order.setAmount(3);
    Order checkedOrder = OrderDAO.update(order);
    assertNotNull(order, "Update method return null object");
    assertEquals(3, checkedOrder.getAmount());

    checkedOrder = OrderDAO.findById(checkedOrder.getId());
    assertEquals(3, checkedOrder.getAmount());
  }

  private void testFindByCart(Cart cart, Order order) {
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

  private void testFindClosedOrdersByUserAndPeriod(User user, Cart cart, Order order) {
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

  private void testDelete(Order order) {
    OrderDAO.delete(order);

    Order checkedOrder = OrderDAO.findById(order.getId());
    assertNull(checkedOrder, "Order not deleted from DB");
  }
}