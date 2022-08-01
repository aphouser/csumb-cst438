package carRental.service;

import carRental.domain.CarCustomerRepository;
import carRental.domain.CarInfo;
import carRental.domain.CarInfoRepository;
import carRental.domain.CarReservation;
import carRental.domain.CarReservationRepository;
import carRental.domain.NewApiReservation;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarReservationService {

  @Autowired
  private CarReservationRepository carReservationRepository;

  @Autowired
  private CarCustomerRepository carCustomerRepository;

  @Autowired
  private CarInfoRepository carInfoRepository;

  // to find a reservation by an id
  public CarReservation getResInfo(long resID) {

    CarReservation carReservation = carReservationRepository.findByReservationID(resID);

    // return the first (only) car reservation (type CarReservation)
    return carReservation;
  }

  // to find a reservation by an email
  public List<CarReservation> getResInfo(String email) {

    List<CarReservation> carReservation = carReservationRepository.findByEmail(email);

    return carReservation;
  }

  // to find all reservations
  public List<CarReservation> getResInfo() {

    List<CarReservation> carReservation = (List<CarReservation>) carReservationRepository.findAll();

    // return the Iterable of type CarReservation
    return carReservation;
  }

  // to create a new reservation with api
  public CarReservation newRes(NewApiReservation newApiReservation)
      throws ParseException {

    // figure out car cost
    float tempCost = 0;
    if (newApiReservation.getCarType().equals("SUV")) {
      tempCost = 150;
    }
    else if (newApiReservation.getCarType().equals("Fullsize")) {
      tempCost = 125;
    }
    else if (newApiReservation.getCarType().equals("Economy")) {
      tempCost = 110;
    }
    else {
      tempCost = 5;
    }

    // testing/debug statement
    System.out.println("Car type was :" + newApiReservation.getCarType());
    System.out.println("cost is :" + tempCost);

    CarInfo tempCar = new CarInfo (newApiReservation.getCarType(), true);

    // testing/debug statement
    System.out.println("CarInfo created");

    // calculate total and add to carReservation
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
    Date pickup = sdf.parse(newApiReservation.getPickupDate());
    Date dropoff = sdf.parse(newApiReservation.getReturnDate());

    // testing/debug statement
    System.out.println("pickup date " + pickup);
    System.out.println("dropoff date " + dropoff);

    long diffInMillies = Math.abs(dropoff.getTime() - pickup.getTime());
    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

    // testing/debug statement
    System.out.println(diffInMillies);
    System.out.println(diff);

    // multiply by days and add 1 for rental car math
    tempCost = tempCost * (diff + 1);

    Date convertPickup = new SimpleDateFormat("MM/dd/yyy").
        parse(newApiReservation.getPickupDate());
    Date convertDropoff = new SimpleDateFormat("MM/dd/yyy").
        parse(newApiReservation.getReturnDate());
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    CarReservation carReservation = new CarReservation (newApiReservation.getEmail(),
        newApiReservation.getPickupLocation(), df.format(convertPickup),
        newApiReservation.getReturnLocation(), df.format(convertDropoff),
        tempCar.getCarID(), tempCost);

    carInfoRepository.save(tempCar);
    carReservationRepository.save(carReservation);

    return carReservation;
  }

  // to delete a car reservation
  public CarReservation cancelRes(long resID) {

    CarReservation cancelled = carReservationRepository.findByReservationID(resID);

    carReservationRepository.delete(cancelled);

    return cancelled;
  }
}

