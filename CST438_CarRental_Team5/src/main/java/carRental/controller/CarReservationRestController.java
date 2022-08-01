package carRental.controller;

import carRental.domain.CarReservation;
import carRental.domain.NewApiReservation;
import carRental.service.CarReservationService;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarReservationRestController {

  @Autowired
  private CarReservationService carReservationService;

  // this is a get method for finding all reservations
  @GetMapping("/api/reservations")
  public ResponseEntity<Iterable<CarReservation>> getAllCarReservation() {

    // testing/debug statement
    System.out.println("getting all reservations");

    Iterable<CarReservation> carReservation = carReservationService.getResInfo();

    // look up city info from database.  Might be multiple cities with same name.
    if (carReservation == null) {
      // testing/debug statement
      System.out.println("null response");

      // reservation not found.  Send 404 return code.
      return new ResponseEntity<Iterable<CarReservation>>( HttpStatus.NOT_FOUND);
    } else {
      // testing/debug statement
      System.out.println("good response");

      // return 200 status code (OK) and city information in JSON format
      return new ResponseEntity<Iterable<CarReservation>>(carReservation, HttpStatus.OK);
    }
  }

  // this is a get method for finding a single reservation by reservation id
  @GetMapping(value = "/api/reservation/id", consumes = "application/json", produces = "application/json")
  public ResponseEntity<CarReservation> getSingleCarReservation(@RequestBody long resID) {

    // testing/debug statement
    System.out.println("getting car reservation by ID");

    CarReservation carReservation = carReservationService.getResInfo(resID);

    // look up city info from database.  Might be multiple cities with same name.
    if (carReservation == null) {
      // testing/debug statement
      System.out.println("null response");

      // reservation not found.  Send 404 return code.
      return new ResponseEntity<CarReservation>( HttpStatus.NOT_FOUND);
    } else {
      // testing/debug statement
      System.out.println("good response");

      // return 200 status code (OK) and information in JSON format
      return new ResponseEntity<CarReservation>(carReservation, HttpStatus.OK);
    }
  }

  // this is a get method for finding all reservations by email
  @GetMapping(value = "/api/reservation/email", consumes = "application/json", produces = "application/json")
  public ResponseEntity<List<CarReservation>> getEmailCarReservation(@RequestBody String email) {

    // testing/debug statement
    System.out.println("getting car reservation by email");

    List<CarReservation> carReservation = carReservationService.getResInfo(email);

    // look up city info from database.  Might be multiple cities with same name.
    if (carReservation.size() == 0) {
      // testing/debug statement
      System.out.println("null response");

      // reservation not found.  Send 404 return code.
      return new ResponseEntity<List<CarReservation>>( HttpStatus.NOT_FOUND);
    } else {
      // testing/debug statement
      System.out.println("good response");

      // return 200 status code (OK) and information in JSON format
      return new ResponseEntity<List<CarReservation>>(carReservation, HttpStatus.OK);
    }
  }

  // this is a get method for creating a new reservation
  @PostMapping(value = "/api/reservation/new", consumes = "application/json", produces = "application/json")
  public ResponseEntity<CarReservation> createNewCarReservation(@RequestBody NewApiReservation newApiReservation)
      throws ParseException {

    // testing/debug statement
    System.out.println("creating new reservation");

    CarReservation carReservation = carReservationService.newRes(newApiReservation);

    // testing/debug statement
    System.out.println(carReservation);

    if (carReservation == null) {
      // testing/debug statement
      System.out.println("null response");

      // reservation not found.  Send 404 return code.
      return new ResponseEntity<CarReservation>( HttpStatus.NOT_FOUND);
    } else {
      // testing/debug statement
      System.out.println("good response");

      // return 200 status code (OK) and information in JSON format
      return new ResponseEntity<CarReservation>(carReservation, HttpStatus.OK);
    }
  }

  // this is a delete method for deleting a reservation
  @PostMapping(value = "/api/reservation/cancel", consumes = "application/json")
  public ResponseEntity<Void> cancelReservation(@RequestBody CarReservation carReservation) {

    // testing/debug statement
    System.out.println("cancelling car reservation by ID");

    CarReservation cancelledReservation = carReservationService.cancelRes(carReservation.getReservationID());

    if (cancelledReservation == null) {
      // testing/debug statement
      System.out.println("null response");

      // reservation not found.  Send 404 return code.
      return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    } else {
      // testing/debug statement
      System.out.println("good response");

      // return 204 status code (No Content) and information in JSON format
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }

}