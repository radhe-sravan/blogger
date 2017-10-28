package com.blogger.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class BlogUser implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  private String username;
  private String password;
  private String role;
  @Column(name = "enabled")
  private int enabled;

  protected BlogUser() {

  }

  public BlogUser(String username, String password, String role, int enabled) {
    this.username = username;
    this.password = password;
    this.role = role;
    this.enabled = enabled;
  }

  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getRole() {
    return role;
  }

  public int getEnabled() {
    return enabled;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
