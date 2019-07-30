package com.mainacad.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  private Integer id;
  private Integer itemId;
  private Integer amount;
  private Integer cadtId;

  public Order(Integer itemId, Integer amount, Integer cadtId) {
    this.itemId = itemId;
    this.amount = amount;
    this.cadtId = cadtId;
  }
}
