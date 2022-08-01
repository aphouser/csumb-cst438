package cst438;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Movie {

  @Id
  @GeneratedValue
  private long id;

  @NotNull
  @Size(min=3, max=25)
  private String title;

  @NotNull
  @Size(min=3, max=25)
  private String reviewer;

  @NotNull
  @Max(5)
  @Min(1)
  private int rating;

  @NotNull
  private String time;

  public Movie() {
    this.time = new java.util.Date().toString();
  }

  public Movie(long id, String title, String reviewer, int rating, String time) {
    super();
    this.id = id;
    this.title = title;
    this.reviewer = reviewer;
    this.rating = rating;
    this.time = new java.util.Date().toString();
  }

  public long getId() { 
    return id; 
  }
  
  public void setId(long id) { 
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getReviewer() {
    return reviewer;
  }

  public void setReviewer(String reviewer) {
    this.reviewer = reviewer;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = new java.util.Date().toString();
  }

}
