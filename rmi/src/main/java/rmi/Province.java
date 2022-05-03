package rmi;

import java.io.Serializable;

public class Province implements Serializable {
  private static final long serialVersionUID = 1L;
  private int id;
  private String shortName;
  private String name;

  public Province() {
  }

  public Province(int id, String name, String shortName) {
    this.id = id;
    this.shortName = shortName;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toString() {
    return id + " - " + shortName + " - " + name;
  }
}
