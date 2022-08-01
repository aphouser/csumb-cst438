package cst438hw2.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import cst438hw2.domain.City;
import cst438hw2.domain.CityInfo;
import cst438hw2.service.CityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CityRestController.class)
public class CityRestControllerTest {

	@MockBean
	private CityService cityService;

	@Autowired
	private MockMvc mvc;

	// This object will be magically initialized by the initFields method below.
	private JacksonTester<CityInfo> json;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void contextLoads() {}

	@Test
	public void testCityFound() throws Exception {
		// create a good test city
		City city = new City(1, "TestCity", "TST", "DistrictTest", 123456);

		// create the stub calls and return data for city service
		//  when the getCityInfo method is called with name parameter "TestCity",
		//  the stub will return the CityInfo object
		given(cityService.getCityInfo("TestCity"))
				.willReturn(new CityInfo(city, "TestCountry", 72.0, "15:13"));

		// perform the test by making simulated HTTP get using URL of "/api/cities/TestCity"
		MockHttpServletResponse response = mvc.perform(get("/api/cities/TestCity"))
				.andReturn().getResponse();

		// verify that result is as expected
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		// convert returned data from JSON string format to City object
		CityInfo cityResult = json.parseObject(response.getContentAsString());

		CityInfo expectedResult = new CityInfo(1, "TestCity", "TST", "TestCountry", "DistrictTest",
				123456, 72.0, "15:13");

		// Assertions
		assertThat(cityResult).isEqualTo(expectedResult);
	}

	@Test
	public void testCityNotFound() throws Exception {
		// create a good test city
		City city = new City(1, "TestCity", "TST", "DistrictTest", 123456);

		// create the stub calls and return data for city service
		//  when the getCityInfo method is called with name parameter "TestCity",
		//  the stub will return the CityInfo object
		given(cityService.getCityInfo("TestCity"))
				.willReturn(new CityInfo(city, "TestCountry", 72.0, "15:13"));

		// perform the test by making simulated HTTP get using URL of "/api/cities/badTestCity"
		MockHttpServletResponse response = mvc.perform(get("/api/cities/badTestCity"))
				.andReturn().getResponse();

		// verify that result is as expected
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}
}
