package carRental.domain;

public class NewApiReservation {

  private String email;
  private String pickupLocation;
  private String pickupDate;
  private String returnLocation;
  private String returnDate;
  private String carType;

  public NewApiReservation() {}

  public NewApiReservation(String email, String pickupLocation, String pickupDate,
      String returnLocation,String returnDate, String carType) {
    super();
    this.email = email;
    this.pickupLocation = pickupLocation;
    this.pickupDate = pickupDate;
    this.returnLocation = returnLocation;
    this.returnDate = returnDate;
    this.carType = carType;
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

  public String getCarType() {
    return carType;
  }

  public void setCarType(String carType) {
    this.carType = carType;
  }

  @Override
  public String toString() {
    return "NewApiReservation [email=" + email + ", pickupLocation=" + pickupLocation +
        ", pickupDate=" + pickupDate + ", returnLocation=" + returnLocation +
        ", returnDate=" + returnDate + ",carType=" + carType + "]";
  }
}
