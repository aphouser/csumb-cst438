package carRental.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import carRental.domain.CarReservation;
import carRental.domain.NewApiReservation;
import carRental.service.CarReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CarReservationRestController.class)
public class CarReservationRestControllerTest {

	@MockBean
	private CarReservationService carReservationService;

	@Autowired
	private MockMvc mvc;

	// This object will be magically initialized by the initFields method below.
	private JacksonTester<CarReservation> json;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void contextLoads() {}

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

    // stub for the CarReservationService.
    given(carReservationService.getResInfo(1)).willReturn(carReservation);

    // id to pass in for JSON body content
    String id = "1";

    // perform the test by making simulated HTTP get using URL of "/api/reservation/id"
    MockHttpServletResponse response = mvc.perform(get("/api/reservation/id").contentType(APPLICATION_JSON_UTF8)
				.content(id)).andReturn().getResponse();

    // verify that result is as expected
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    // convert returned data from JSON string format to City object
    CarReservation result = json.parseObject(response.getContentAsString());

    CarReservation expectedResult = new CarReservation(

        "test@email.com",
        "San Diego",
        "1/1/2021",
        "San Diego",
        "1/2/2021",
        1,
        1000
    );

    // Assertions
    assertThat(result).isEqualTo(expectedResult);

  }

	@Test
	public void testReservationNotFoundID() throws Exception {
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

		// stub for the CarReservationService.
		given(carReservationService.getResInfo(1)).willReturn(carReservation);

		// using a different ID for failure test
		String id = "2";

		// perform the test by making simulated HTTP get using URL of "/api/reservation/id"
		MockHttpServletResponse response = mvc.perform(get("/api/reservation/id").contentType(APPLICATION_JSON_UTF8)
				.content(id)).andReturn().getResponse();

		// verify that result is as expected
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
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

		// stub for the CarReservationService.
		given(carReservationService.getResInfo("test@email.com")).willReturn(carReservation);

		// id to pass in for JSON body content
		String email = "test@email.com";

		// perform the test by making simulated HTTP get using URL of "/api/reservation/email"
		MockHttpServletResponse response = mvc.perform(get("/api/reservation/email").contentType(APPLICATION_JSON_UTF8)
				.content(email)).andReturn().getResponse();

		String stringResponse = mvc.perform(get("/api/reservation/email").contentType(APPLICATION_JSON_UTF8)
				.content(email)).andReturn().getResponse().getContentAsString();

		// verify that result is as expected
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		// convert returned data from JSON string format to City object
		ObjectMapper mapper = new ObjectMapper();
		List<CarReservation> resultList = mapper.readValue
				(stringResponse, new TypeReference<List<CarReservation>>() {});

		CarReservation expectedResult1 = new CarReservation(

				"test@email.com",
				"San Diego",
				"1/1/2021",
				"San Diego",
				"1/2/2021",
				1,
				1000
		);

		List<CarReservation> expectedResult = new ArrayList<CarReservation>();

		expectedResult.add(expectedResult1);

		// Assertions
		assertThat(resultList).isEqualTo(expectedResult);

	}

	@Test
	public void testReservationNotFoundEmail() throws Exception {
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

		// stub for the CarReservationService.
		given(carReservationService.getResInfo("test@email.com")).willReturn(null);

		//  to pass in for JSON body content
		String email = "test2@email.com";

		// perform the test by making simulated HTTP get using URL of "/api/reservation/email"
		MockHttpServletResponse response = mvc.perform(get("/api/reservation/email").contentType(APPLICATION_JSON_UTF8)
				.content(email)).andReturn().getResponse();

		// verify that result is as expected
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}

	@Test
	public void testAllReservations() throws Exception {

		// create multiple new car reservations
		CarReservation carReservation1 = new CarReservation(

				"test@email.com",
				"Honolulu",
				"1/1/2021",
				"Honolulu",
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
				2000
		);
		CarReservation carReservation3 = new CarReservation(

				"test3@email.com",
				"San Diego",
				"1/3/2021",
				"San Diego",
				"1/3/2021",
				3,
				3000
		);

		List<CarReservation> carReservation = new ArrayList<CarReservation>();
		carReservation.add(carReservation1);
		carReservation.add(carReservation2);
		carReservation.add(carReservation3);

		// stub for the CarReservationService.
		given(carReservationService.getResInfo()).willReturn(carReservation);

		// perform the test by making simulated HTTP get using URL of "/api/reservation/email"
		MockHttpServletResponse response = mvc.perform(get("/api/reservations")).andReturn().
				getResponse();

		String stringResponse = mvc.perform(get("/api/reservations")).andReturn().
				getResponse().getContentAsString();

		// verify that result is as expected
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		// convert returned data from JSON string format to City object
		ObjectMapper mapper = new ObjectMapper();
		List<CarReservation> resultList = mapper.readValue
				(stringResponse, new TypeReference<List<CarReservation>>() {});

		CarReservation expectedResult1 = new CarReservation(

				"test@email.com",
				"Honolulu",
				"1/1/2021",
				"Honolulu",
				"1/1/2021",
				1,
				1000
		);
		CarReservation expectedResult2 = new CarReservation(

				"test2@email.com",
				"Monterey",
				"1/2/2021",
				"Monterey",
				"1/2/2021",
				2,
				2000
		);
		CarReservation expectedResult3 = new CarReservation(

				"test3@email.com",
				"San Diego",
				"1/3/2021",
				"San Diego",
				"1/3/2021",
				3,
				3000
		);

		List<CarReservation> expectedResult = new ArrayList<CarReservation>();
		expectedResult.add(expectedResult1);
		expectedResult.add(expectedResult2);
		expectedResult.add(expectedResult3);

		// Assertions
		assertThat(resultList).isEqualTo(expectedResult);
	}

	// we think the following test is written correctly, but the given statement does not
	// seem to work as intended for some reason.  The query is still returning null as opposed
	// to the given CarReservation object, so the test comes back as running, but due to the
	// null return, we are getting a 404 instead of 200 response.  If we change the Service
	// class newRes() function to be a .equals() instead of =, the test will pass, but the actual
	// operational side does not work and the reservation is not created in the database.
	@Test
	public void testNewReservation() throws ParseException {
		// create a NewApiReservation object
		NewApiReservation newApiReservation = new NewApiReservation(
				"test@email.com",
				"San Diego",
				"1/1/2021",
				"San Diego",
				"1/2/2021",
				"SUV"
		);

		ObjectMapper mapper = new ObjectMapper();
		String jsonInfo = "";
		try {
			jsonInfo = mapper.writeValueAsString(newApiReservation);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.out.println(jsonInfo);

		// create a new car reservation
		CarReservation carReservation = new CarReservation(

				39,
				"test@email.com",
				"San Diego",
				"1/1/2021",
				"San Diego",
				"1/2/2021",
				0,
				150
		);

		// stub for the CarReservationService.
		given(carReservationService.newRes(newApiReservation)).willReturn(carReservation);

		// perform the test by making simulated HTTP get using URL of "/api/reservation/new"
		MockHttpServletResponse response = null;
		try {
			response = mvc.perform(post("/api/reservation/new")
					.contentType(APPLICATION_JSON_UTF8).content(jsonInfo))
					.andReturn().getResponse();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// verify that result is as expected
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
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

		// stub for the CarReservationService.
		given(carReservationService.cancelRes(1)).willReturn(carReservation);

		// id to pass in for JSON body content
		String id = "1";

		// perform the test by making simulated HTTP get using URL of "/api/reservation/cancel"
		MockHttpServletResponse response = null;
		try {
			response = mvc.perform(delete("/api/reservation/cancel").
					contentType(APPLICATION_JSON_UTF8).content(id)).andReturn().getResponse();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// verify that result is as expected
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
	}
}
