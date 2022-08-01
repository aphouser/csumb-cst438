package carRental.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="fullReservation")

public class CarFullQuery {

  @Id
  @NotEmpty
  private long reservationID;
  private String email;
  private String pickupLocation;
  private String pickupDate;
  private String returnLocation;
  private String returnDate;
  private String type;
  private float total;
  
  private String fName;
  private String lName;

  public CarFullQuery() {}

  public CarFullQuery(long reservationID, String email, String fName, String lName,
      String pickupLocation, String pickupDate,
      String returnLocation, String returnDate,
      String type, float total) {
    super();
    this.reservationID = reservationID;
    this.email = email;
    this.fName = fName;
    this.lName = lName;
    this.pickupLocation = pickupLocation;
    this.pickupDate = pickupDate;
    this.returnLocation = returnLocation;
    this.returnDate = returnDate;
    this.type = type;
    this.total = total;
  }

  public long getReservationID() {
    return reservationID;
  }

  public void setReservationID(long reservationID) {
    this.reservationID = reservationID;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getfName() {
    return fName;
  }

  public void setfName(String fName) {
    this.fName = fName;
  }

  public String getlName() {
    return lName;
  }

  public void setlName(String lName) {
    this.lName = lName;
  }

  public String getPickupLocation() {
    return pickupLocation;
  }

  public void setPickupLocation(String pickupLocation) {
    this.pickupLocation = pickupLocation;
  }

  public String getPickupDate() {
    return pickupDate;
  }

  public void setPickupDate(String pickupDate) {
    this.pickupDate = pickupDate;
  }

  public String getReturnLocation() {
    return returnLocation;
  }

  public void setReturnLocation(String returnLocation) {
    this.returnLocation = returnLocation;
  }

  public String getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(String returnDate) {
    this.returnDate = returnDate;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public float getTotal() {
    return total;
  }

  public void setTotal(float total) {
    this.total = total;
  }

  @Override
  public String toString() {
    return "CarFullQuery [reservationID=" + reservationID + ", email=" + email +
        ", fName=" + fName + ", lName=" + lName +
        ", pickupLocation=" + pickupLocation + ", pickupDate=" + pickupDate +
        ", returnLication=" + returnLocation + ", returnDate=" + returnDate + ", type=" + type +
        ", total=" + total + "]";
  }

}
