package com.mainacad.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private Integer id;
  private String login;
  private String password;
  private String firstName;
  private String secondName;

  public User(String login, String password, String firstName, String secondName) {
    this.login = login;
    this.password = password;
    this.firstName = firstName;
    this.secondName = secondName;
  }
}
