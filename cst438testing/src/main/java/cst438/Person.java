package cst438;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Person {

  @Id
  @GeneratedValue
  private long id;

  @NotNull
  @Size(min=3, max=25)
  private String firstName;

  @NotNull
  @Size(min=3, max=25)
  private String lastName;

  @NotNull
  @Size(min=3, max=25)
  private String favoriteFood;

  public Person() {

  }

  public Person(long id, String firstName, String lastName, String favoriteFood) {
    super();
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.favoriteFood = favoriteFood;
  }

  public long getId() { return id; }
  public void setId(long id) { this.id = id;}

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFavoriteFood() {
    return favoriteFood;
  }

  public void setFavoriteFood(String favoriteFood) {
    this.favoriteFood = favoriteFood;
  }

}