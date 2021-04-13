package eu.codecache.linko.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import eu.codecache.linko.domain.City;
import eu.codecache.linko.domain.CityRepository;
import eu.codecache.linko.domain.Event;
import eu.codecache.linko.domain.EventRepository;
import eu.codecache.linko.domain.OrderRepository;
import eu.codecache.linko.domain.Orders;
import eu.codecache.linko.domain.Ticket;
import eu.codecache.linko.domain.TicketOrder;
import eu.codecache.linko.domain.TicketOrderRepository;
import eu.codecache.linko.domain.TicketRepository;
import eu.codecache.linko.domain.TicketType;
import eu.codecache.linko.domain.TicketTypeRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TicketOrderControllerTest {

	// These are for easier modifications
	private final String USERNAME = "user";
	private final String ADMIN_NAME = "admin";
	private final String DEFAULT_PASSWORD = "password";
	private final String[] DEFAULT_EVENTS = { "First event!", "Name of event 2" };
	private String CODE1;
	private String CODE2;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private EventRepository eRepo;

	@Autowired
	private CityRepository cRepo;

	@Autowired
	private TicketTypeRepository ttRepo;

	@Autowired
	private TicketRepository tRepo;

	@Autowired
	private OrderRepository oRepo;

	@Autowired
	private TicketOrderRepository toRepo;

	// now for patching
	private class PatchBody {
		private String code;

		public PatchBody(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}

	/*
	 * Set mock data to H2 for tests
	 */
	@BeforeEach
	public void setDataToDB() {
		try {
			City mockCity = new City("Mock");
			cRepo.save(mockCity);
			Event e1 = eRepo
					.save(new Event(DEFAULT_EVENTS[0], mockCity, "place1", 1000, "some desc", LocalDateTime.now()));
			eRepo.save(new Event(DEFAULT_EVENTS[1], mockCity, "place2", 100, "", LocalDateTime.now()));
			TicketType tt = ttRepo.save(new TicketType("Normal"));
			Ticket ticket = tRepo.save(new Ticket(tt, e1, 10.00, ""));
			Orders order = oRepo.save(new Orders(LocalDateTime.now()));
			TicketOrder to1 = toRepo.save(new TicketOrder(order, ticket, 9.00));
			TicketOrder to2 = toRepo.save(new TicketOrder(order, ticket, 10.00));
			to1.setCharacter();
			to2.setCharacter();
			toRepo.save(to1);
			toRepo.save(to2);
			this.CODE1 = to1.getCode();
			this.CODE2 = to2.getCode();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/*
	 * Test getting ticket with code
	 */
	@Test
	public void getTicketTest() throws Exception {
		ResponseEntity<String> correctCode = restTemplate.withBasicAuth(USERNAME, DEFAULT_PASSWORD)
				.getForEntity("/api/soldtickets?code=" + CODE1, String.class);
		ResponseEntity<String> incorrectCode = restTemplate.withBasicAuth(USERNAME, DEFAULT_PASSWORD)
				.getForEntity("/api/soldtickets?code=foobar", String.class);
		assertEquals(HttpStatus.OK, correctCode.getStatusCode());
		assertEquals(HttpStatus.NOT_FOUND, incorrectCode.getStatusCode());
	}

	/*
	 * Test patch a ticket in database
	 */
//	@Test
//	public void patchTicket() throws Exception {
//		try {
			// Seems we can't run this test as Patch isn't working

			// more information:
			// https://stackoverflow.com/questions/57426607/patchforobject-how-to
//			long ticketID = toRepo.findByCode(CODE1).getTicketOrderID();
//			ResponseEntity<String> response = restTemplate.withBasicAuth(USERNAME, DEFAULT_PASSWORD)
//					.patchForObject("/api/soldtickets/" + ticketID, new PatchBody(CODE1), ResponseEntity.class);
//			assertEquals(response.getStatusCode(), HttpStatus.OK);
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//	}

}
