package eu.codecache.linko.web;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import eu.codecache.linko.domain.City;
import eu.codecache.linko.domain.CityRepository;
import eu.codecache.linko.domain.Event;
import eu.codecache.linko.domain.EventRepository;

/*
 * Tests was done with the help of following tutorial:
 * 		https://www.baeldung.com/spring-security-integration-tests
 */

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EventControllerTest {

	// These are for easier modifications
	private final String USERNAME = "user";
	private final String ADMIN_NAME = "admin";
	private final String DEFAULT_PASSWORD = "password";
	private final String[] DEFAULT_EVENTS = { "First event!", "Name of event 2" };
	private long CORRECT_EVENT_ID = 5;
	private final int INCORRECT_EVENT_ID = 1000;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private EventRepository eRepo;

	@Autowired
	private CityRepository cRepo;

	@BeforeEach
	public void setDataToDB() {
		try {
			City mockCity = new City("Mock");
			cRepo.save(mockCity);
			Event e1 = eRepo
					.save(new Event(DEFAULT_EVENTS[0], mockCity, "place1", 1000, "some desc", LocalDateTime.now()));
			eRepo.save(new Event(DEFAULT_EVENTS[1], mockCity, "place2", 100, "", LocalDateTime.now()));
			CORRECT_EVENT_ID = e1.getEventID();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/*
	 * Let's test getting all events from events api
	 */
	@Test
	public void getAllTest() throws Exception {
		// First let's see that only authenticated user has access
		ResponseEntity<String> correctUserAndPass = restTemplate.withBasicAuth(USERNAME, DEFAULT_PASSWORD)
				.getForEntity("/api/events", String.class);
		assertEquals(HttpStatus.OK, correctUserAndPass.getStatusCode());
		ResponseEntity<String> incorrectUser = restTemplate.withBasicAuth(USERNAME + "2", DEFAULT_PASSWORD)
				.getForEntity("/api/events", String.class);
		assertEquals(HttpStatus.UNAUTHORIZED, incorrectUser.getStatusCode());

		// After that we will also check the contents of the successfull fetch
//		System.err.println(correctUserAndPass.getBody());
		assertTrue(correctUserAndPass.getBody().contains(DEFAULT_EVENTS[0]));
		assertTrue(correctUserAndPass.getBody().contains(DEFAULT_EVENTS[1]));
	}

	/*
	 * Now let's see if we can get a single event and also get 404 when not found
	 */
	@Test
	public void getSingleTest() throws Exception {
		ResponseEntity<String> correctID = restTemplate.withBasicAuth(USERNAME, DEFAULT_PASSWORD)
				.getForEntity("/api/events/" + CORRECT_EVENT_ID, String.class);
		ResponseEntity<String> incorrectID = restTemplate.withBasicAuth(USERNAME, DEFAULT_PASSWORD)
				.getForEntity("/api/events/" + INCORRECT_EVENT_ID, String.class);
		assertEquals(HttpStatus.OK, correctID.getStatusCode());
		assertEquals(HttpStatus.NOT_FOUND, incorrectID.getStatusCode());
	}

	/*
	 * Test posting an event to api
	 */
	@Test
	public void postEvent() throws Exception {
		Event newEvent = new Event("Matin rokkibändi!", new City("Turhala"), "Paikka", 100, "", LocalDateTime.now());
		HttpEntity<Event> request = new HttpEntity<>(newEvent);
//		ResponseEntity<String> incorrectUser = restTemplate.withBasicAuth("user", "password")
//				.postForEntity("/api/events", request, String.class);
//		assertEquals(HttpStatus.UNAUTHORIZED, incorrectUser.getStatusCode());
		ResponseEntity<String> correctUser = restTemplate.withBasicAuth(ADMIN_NAME, DEFAULT_PASSWORD)
				.postForEntity("/api/events", request, String.class);
		assertEquals(HttpStatus.CREATED, correctUser.getStatusCode());
	}
}
