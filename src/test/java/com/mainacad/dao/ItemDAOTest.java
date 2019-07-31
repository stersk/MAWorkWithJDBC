package com.mainacad.dao;

import com.mainacad.model.Item;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOTest {

  private static List<Item> items = new ArrayList<>();
  private static String TEST_ITEM_CODE = "qwerty12345";
  private static Integer TEST_NEW_PRICE = 1450000;

  @BeforeAll
  static void setUp() {
    Item item = new Item(TEST_ITEM_CODE, "Kellys Spider 40 (2014)", 1400000);
    items.add(item);
  }

  @AfterAll
  static void tearDown() {
    for (Item item: items) {
      if (item.getId() != null) {
        ItemDAO.delete(item);
      }
    }
  }

  @Test
  void testCreateFindAndDelete() {
    // checking update
    assertNull(items.get(0).getId());
    Item createdItem = ItemDAO.create(items.get(0));
    assertNotNull(createdItem);
    assertNotNull(createdItem.getId());

    // checking findById
    Item checkedItem = ItemDAO.findById(createdItem.getId());
    assertNotNull(checkedItem);
    assertNotNull(checkedItem.getId());

    // another tests
    testUpdate(checkedItem);
    testFindByItemCode(createdItem, checkedItem);
    testFindItemByPriceBetween(createdItem);
    testFindAll();
    сheckingDelete(createdItem, checkedItem);
  }

  private void testUpdate(Item checkedItem) {
    checkedItem.setPrice(TEST_NEW_PRICE);
    checkedItem = ItemDAO.update(checkedItem);

    assertNotNull(checkedItem);
    assertEquals(TEST_NEW_PRICE, checkedItem.getPrice());

    Item checkedItemFromDB = ItemDAO.findById(checkedItem.getId());
    assertNotNull(checkedItemFromDB);
    assertEquals(TEST_NEW_PRICE, checkedItemFromDB.getPrice());
  }

  private void testFindByItemCode(Item createdItem, Item checkedItem) {
    List<Item> checkedItems = ItemDAO.findByItemCode(TEST_ITEM_CODE);
    assertNotNull(checkedItems);

    checkedItem = null;
    for (int i = 0; i < checkedItems.size(); i++) {
      if (checkedItems.get(i).getId() == createdItem.getId()) {
        checkedItem = checkedItems.get(i);
      }
    }

    assertNotNull(checkedItem, "Item not found by code");
  }

  private void testFindItemByPriceBetween(Item createdItem) {
    List<Item> checkedItems = ItemDAO.findByItemPriceBetween(createdItem.getPrice() - 100, createdItem.getPrice() + 100);
    assertNotNull(checkedItems);

    Item checkedItem = null;
    for (int i = 0; i < checkedItems.size(); i++) {
      if (checkedItems.get(i).getId() == createdItem.getId()) {
        checkedItem = checkedItems.get(i);
      }
    }

    assertNotNull(checkedItem, "Item not found by price, but should");

    checkedItems = ItemDAO.findByItemPriceBetween(createdItem.getPrice() + 100, createdItem.getPrice() + 200);
    assertNotNull(checkedItem);

    checkedItem = null;
    for (int i = 0; i < checkedItems.size(); i++) {
      if (checkedItems.get(i).getId() == createdItem.getId()) {
        checkedItem = checkedItems.get(i);
      }
    }

    assertNotNull(checkedItem, "Item found by price, but should not");
  }

  private void testFindAll() {
    List<Item> checkedItems = ItemDAO.findAll();

    assertNotNull(checkedItems);
    assertEquals(true, checkedItems.size() > 0);
    for (com.mainacad.model.Item Item: checkedItems) {
      assertNotNull(Item, "All_Items collection has null objects");
    }
  }

  private void сheckingDelete(Item createdItem, Item checkedItem) {
    ItemDAO.delete(checkedItem);

    Item deletedItem = ItemDAO.findById(createdItem.getId());
    assertNull(deletedItem);
  }
}