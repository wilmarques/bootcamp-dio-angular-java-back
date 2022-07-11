package com.santander.bootcampdio.heroes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Hero {

  private @Id @GeneratedValue Long id;
  private String name;
  private String description;

  public Hero() {
  }

  public Hero(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Hero [description=" + description + ", id=" + id + ", name=" + name + "]";
  }

}
