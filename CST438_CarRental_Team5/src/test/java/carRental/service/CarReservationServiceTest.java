package carRental.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import carRental.domain.CarReservation;
import carRental.domain.CarReservationRepository;
import carRental.service.CarReservationService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CarReservationServiceTest {

	@Autowired
	private CarReservationService carReservationService;

	@MockBean
	private CarReservationRepository carReservationRepository;

	@Test
	public void contextLoads() {
	}


	@Test
	public void testReservationFoundID() throws Exception {

		// create a new car reservation
		CarReservation carReservation = new CarReservation(
				"test@email.com",
				"San Diego",
				"1/1/2021",
				"San Diego",
				"1/2/2021",
				1,
				1000
		);

		// stub for the CarReservationRepository.
		given(carReservationRepository.findByReservationID(1)).willReturn(carReservation);

		CarReservation testReservation = carReservationService.getResInfo(1);

		// Assertions
		assertThat(testReservation).isEqualTo(carReservation);

	}

	@Test
	public void  testReservationNotFoundID() {

		// create a new car reservation
		CarReservation carReservation = new CarReservation(
				"test@email.com",
				"San Diego",
				"1/1/2021",
				"San Diego",
				"1/2/2021",
				1,
				1000
		);

		// stub for the CarReservationRepository.
		given(carReservationRepository.findByReservationID(1)).willReturn(carReservation);


		CarReservation testReservation = carReservationService.getResInfo(9999);
		CarReservation expectedResult = null;

		// Assertions
		assertThat(testReservation).isEqualTo(expectedResult);
	}

	@Test
	public void testReservationFoundEmail() throws Exception {

		// create a new car reservation
		CarReservation carReservation1 = new CarReservation(

				"test@email.com",
				"San Diego",
				"1/1/2021",
				"San Diego",
				"1/2/2021",
				1,
				1000
		);

		List<CarReservation> carReservation = new ArrayList<CarReservation>();

		carReservation.add(carReservation1);

		// stub for the CarReservationRepository.
		given(carReservationRepository.findByEmail("test@email.com")).willReturn(carReservation);

		List<CarReservation> testReservation = carReservationService.getResInfo("test@email.com");

		// Assertions
		assertThat(testReservation).isEqualTo(carReservation);
	}

	@Test
	public void  testReservationNotFoundEmail() {

		// create a new car reservation
		CarReservation carReservation1 = new CarReservation(
				"test@email.com",
				"San Diego",
				"1/1/2021",
				"San Diego",
				"1/2/2021",
				1,
				1000
		);

		List<CarReservation> carReservation = new ArrayList<CarReservation>();

		carReservation.add(carReservation1);

		// stub for the CarReservationRepository.
		given(carReservationRepository.findByEmail("test@email.com")).willReturn(carReservation);

		List<CarReservation> testReservation = carReservationService.getResInfo("test2@email.com");
		List<CarReservation> expectedResult = Collections.emptyList();

		// Assertions
		assertThat(testReservation).isEqualTo(expectedResult);
	}

	@Test
	public void  testAllReservations() {
		// create 3 reservations
		CarReservation carReservation1 = new CarReservation(
				"test@email.com",
				"San Diego",
				"1/1/2021",
				"San Diego",
				"1/1/2021",
				1,
				1000
		);
		CarReservation carReservation2 = new CarReservation(

				"test2@email.com",
				"Monterey",
				"1/2/2021",
				"Monterey",
				"1/2/2021",
				2,
				2
		);
		CarReservation carReservation3 = new CarReservation(

				"test3@email.com",
				"San Diego",
				"1/3/2021",
				"San Diego",
				"1/3/2021",
				3,
				3
		);

		// Create an empty carReservation list to be used on the given
		List<CarReservation> carReservation = new ArrayList<CarReservation>();
		carReservation.add(carReservation1);
		carReservation.add(carReservation2);
		carReservation.add(carReservation3);

		// stub for the CarReservationRepository.
		given(carReservationRepository.findAll()).willReturn(carReservation);

		List<CarReservation> testReservation = carReservationService.getResInfo();

		// Assertions
		assertThat(testReservation).isEqualTo(carReservation);
	}

	@Test
	public void  testCancelReservation() {
		// create a new car reservation
		CarReservation carReservation = new CarReservation(
				"test@email.com",
				"San Diego",
				"1/1/2021",
				"San Diego",
				"1/2/2021",
				1,
				1000
		);

		// stub for the CarReservationRepository.
		given(carReservationRepository.findByReservationID(1)).willReturn(carReservation);
		given(carReservationRepository.deleteByReservationID(1)).willReturn(1);

		CarReservation testReservation = carReservationService.cancelRes(1);

		// Assertions
		assertThat(testReservation).isEqualTo(carReservation);
	}
}