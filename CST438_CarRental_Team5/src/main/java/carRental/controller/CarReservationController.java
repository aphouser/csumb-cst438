package carRental.controller;

import carRental.domain.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.ParseException;

@Controller
public class CarReservationController {
  @Autowired
  private CarReservationRepository carReservationRepository;
  @Autowired
  private CarInfoRepository carInfoRepository;
  @Autowired
  private CarCustomerRepository carCustomerRepository;
  @Autowired
  private CarFullQueryRepository carFullQueryRepository;
  
  @GetMapping("")
  public String index(Model model) {
	  String email = new String();
	  model.addAttribute("email", email);
	  Iterable<CarFullQuery> reservations = carFullQueryRepository.findAllJoin();
	  model.addAttribute("reservationList", reservations);
	  return "index";
  }


  @PostMapping("")
  public String searchReservationsEmail(@RequestParam("email") String email, Model model) {
    Iterable<CarReservation> reservations = carReservationRepository.findByEmail(email);
    CarCustomer customer = carCustomerRepository.findByEmail(email);
    CarInfo car = carInfoRepository.findByCarID(1);
    model.addAttribute("reservations", reservations);
    model.addAttribute("customer", customer);
    model.addAttribute("car", car);
    return "user_reservation";
  }

    /*
  localhost:8080/reservation/new
  presents the user the car_reservation.html page to fill-out
   */
  @GetMapping("/reservation/new")
  public String createReservation(Model model){
    CarReservation carReservation = new CarReservation();
    CarInfo carInfo = new CarInfo();
    CarCustomer carCustomer = new CarCustomer();
    model.addAttribute("carReservation", carReservation);
    model.addAttribute("carInfo", carInfo);
    model.addAttribute("carCustomer", carCustomer);
    return "car_reservation";
  }

  /*
  localhost:8080/reservation/new
  presents the user the car_confirmation.html page to fill-out
   */
  @PostMapping("/reservation/new")
  public String processCarReservation(@Valid CarReservation carReservation,@Valid CarInfo carInfo, @Valid CarCustomer carCustomer,
      BindingResult result, Model model) throws ParseException {
    carInfoRepository.save(carInfo);
    carReservation.convertDate(carReservation.getPickupDate(), carReservation.pickup);
    carReservation.convertDate(carReservation.getReturnDate(), carReservation.dropoff);
    carReservation.setCarID(carInfo.getCarID());
    carReservationRepository.save(carReservation);
    carCustomerRepository.save(carCustomer);
    if (result.hasErrors()) {
      return "car_reservation";
    }
    return "car_confirmation";
  }

  /*
localhost:8080/reservation/cancel
presents the user the car_reservation.html page to fill-out
 */
  @GetMapping("/reservation/cancel")
  public String cancelReservation(Model model){
    return "car_cancel";
  }

  /*
  localhost:8080/reservation/cancel
  presents the user the car_confirmation.html page to fill-out
   */
  @PostMapping("/reservation/cancel")
  public String deleteCarReservation(@RequestParam("customerID") long customerID){

    CarReservation cancelled = carReservationRepository.findByReservationID(customerID);
    long temp = cancelled.getReservationID();
    carReservationRepository.deleteByReservationID(temp);

    return "car_cancelled_confirmation";
  }
}
