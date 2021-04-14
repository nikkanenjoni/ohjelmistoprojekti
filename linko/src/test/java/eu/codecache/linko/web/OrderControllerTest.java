package eu.codecache.linko.web;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
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
import eu.codecache.linko.domain.TicketOrderDTO;
import eu.codecache.linko.domain.TicketOrderRepository;
import eu.codecache.linko.domain.TicketRepository;
import eu.codecache.linko.domain.TicketType;
import eu.codecache.linko.domain.TicketTypeRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

	// These are for easier modifications
	private final String USERNAME = "user";
	private final String ADMIN_NAME = "admin";
	private final String DEFAULT_PASSWORD = "password";
	private final String[] DEFAULT_EVENTS = { "First event!", "Name of event 2" };
	private long TICKETID;
	private long ORDERID;

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

	/*
	 * Test getting all orders
	 */
	@Test
	public void getAllOrdersTest() {
		// TODO
	}

	/*
	 * Test getting single order
	 */
	@Test
	public void getOrderByIdTest() {
		// TODO
	}

	/*
	 * Test posting a new order to repository
	 */
	@Test
	public void postNewOrderTest() {
		// TODO
	}

	/*
	 * Test adding ticket to an order
	 */
	@Test
	public void addTicketToOrderTest() throws Exception {
		// path: /api/orders/:id
		// method: POST
		// RequestBody: TicketOrderDTO
		// returns: 201 on success, 404 with incorrect ID, 415 with incorrect body

		// Incorrect body
		ResponseEntity<String> incorrectBody = restTemplate.withBasicAuth(USERNAME, DEFAULT_PASSWORD)
				.postForEntity("/api/orders/" + ORDERID, null, String.class);
		assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, incorrectBody.getStatusCode());

		// Succesfull posting
		TicketOrderDTO dto = new TicketOrderDTO();
		dto.setTicketID(TICKETID);
		dto.setTicketPrice(19.99);
		HttpEntity<TicketOrderDTO> request = new HttpEntity<>(dto);
		ResponseEntity<String> response = restTemplate.withBasicAuth(USERNAME, DEFAULT_PASSWORD)
				.postForEntity("/api/orders/" + ORDERID, request, String.class);
		// Correct response code
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		// Correct response body
		assertTrue(response.getBody().contains("19.99"));

		// Incorrect id
		// We take body from previous run, to make otherwise successfull request. Just
		// id is not found
		ResponseEntity<String> incorrectID = restTemplate.withBasicAuth(USERNAME, DEFAULT_PASSWORD)
				.postForEntity("/api/orders/1000000", request, String.class);
		assertEquals(HttpStatus.NOT_FOUND, incorrectID.getStatusCode());
	}

	/*
	 * Test deleting a ticket from an order
	 */
	@Test
	public void deleteTicketFromOrderTest() {
		// TODO
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
			TICKETID = ticket.getTicketID();
			Orders order = oRepo.save(new Orders(LocalDateTime.now()));
			ORDERID = order.getOrderID();
			TicketOrder to1 = toRepo.save(new TicketOrder(order, ticket, 9.00));
			TicketOrder to2 = toRepo.save(new TicketOrder(order, ticket, 10.00));
			to1.setCharacter();
			to2.setCharacter();
			toRepo.save(to1);
			toRepo.save(to2);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
