package carRental.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reservation")
public class CarReservation {

  public static final String pickup = "pickup";
  public static final String dropoff = "dropoff";
  @Id
  @GeneratedValue
  private long reservationID;
  private String email;
  private String pickupLocation;
  private String pickupDate;
  private String returnLocation;
  private String returnDate;
  private long carID;

  private float total;
  public CarReservation(){}


  public CarReservation(long reservationID, String email,
      String pickupLocation, String pickupDate,
      String returnLocation, String returnDate, long carID, float total){
    super();
    this.reservationID = reservationID;
    this.email = email;
    this.pickupLocation = pickupLocation;
    this.pickupDate = pickupDate;
    this.returnLocation = returnLocation;
    this.returnDate = returnDate;
    this.carID = carID;
    this.total = total;
  }

  public CarReservation(String email,
      String pickupLocation, String pickupDate,
      String returnLocation, String returnDate, long carID, float total){
    super();
    this.email = email;
    this.pickupLocation = pickupLocation;
    this.pickupDate = pickupDate;
    this.returnLocation = returnLocation;
    this.returnDate = returnDate;
    this.carID = carID;
    this.total = total;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CarReservation other = (CarReservation) obj;
    if (pickupDate == null) {
      if (other.pickupDate != null)
        return false;
    } else if (!pickupDate.equals(other.pickupDate))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (pickupLocation == null) {
      if (other.pickupLocation != null)
        return false;
    } else if (!pickupLocation.equals(other.pickupLocation))
      return false;
    if (reservationID != other.reservationID)
      return false;
    if (returnLocation == null) {
      if (other.returnLocation != null)
        return false;
    } else if (!returnLocation.equals(other.returnLocation))
      return false;
    if (carID != other.carID)
      return false;
    if (returnDate == null) {
      if (other.returnDate != null)
        return false;
    } else if (!returnDate.equals(other.returnDate))
      return false;
    return true;
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

  public long getCarID() {
    return carID;
  }

  public void setCarID(long carID) {
    this.carID = carID;
  }

  public float getTotal() {
    return total;
  }

  public void setTotal(float total) {
    this.total = total;
  }

  public void convertDate(String date, String dateType) throws ParseException {
    try {
      Date dt = new SimpleDateFormat("MM/dd/yyy").parse(date);
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      if (dateType.equals(pickup))
        this.pickupDate = df.format(dt);
      else
        this.returnDate = df.format(dt);
    }
    catch(ParseException e) {e.printStackTrace();}
  }

  @Override
  public String toString() {
    return "CarReservation [reservationID=" + reservationID + ", email=" + email +
        ", pickupLocation=" + pickupLocation + ", pickupDate=" + pickupDate +
        ", returnLocation=" + returnLocation + ", returnDate=" + returnDate +
            ",total=" + total + "]";
  }
}
