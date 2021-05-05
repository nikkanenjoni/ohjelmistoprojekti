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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	private long SOLD_OUT_TICKET;
	private long SOLD_OUT_ORDER;

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

	/*
	 * This test has become a horror! It really needs to be cut down to multiple
	 * tests...
	 */
	@Test
	public void addTicketToOrderTest() throws Exception {
		// path: /api/orders/:id
		// method: POST
		// RequestBody: TicketOrderDTO
		// returns: 201 on success, 404 with incorrect ID, 415 with incorrect body

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Incorrect body
		ResponseEntity<String> incorrectBody = restTemplate.withBasicAuth(USERNAME, DEFAULT_PASSWORD)
				.postForEntity("/api/orders/" + ORDERID, null, String.class);
		assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, incorrectBody.getStatusCode());

		// Succesfull posting of multiple tickets
		String jsonbody = "[{\"ticketID\": " + TICKETID + ", \"ticketPrice\": 10},{\"ticketID\": " + TICKETID
				+ ", \"ticketPrice\": 10}]";
		HttpEntity<String> multipleTickets = new HttpEntity<String>(jsonbody, headers);
		ResponseEntity<String> multipleTicketsResponse = restTemplate.withBasicAuth(USERNAME, DEFAULT_PASSWORD)
				.postForEntity("/api/orders/" + ORDERID, multipleTickets, String.class);
		assertEquals(HttpStatus.CREATED, multipleTicketsResponse.getStatusCode());

		// Succesfull posting single ticket
		TicketOrderDTO dto = new TicketOrderDTO();
		dto.setTicketID(TICKETID);
		dto.setTicketPrice(19.99);
		HttpEntity<String> request = new HttpEntity<>("[" + dto.toString() + "]", headers);
		ResponseEntity<String> response = restTemplate.withBasicAuth(USERNAME, DEFAULT_PASSWORD)
				.postForEntity("/api/orders/" + ORDERID, request, String.class);
		// Correct response code
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		// Correct response body
		assertTrue(response.getBody().contains("19.99"));

		// Incorrect (order)id
		// We take body from previous run, to make otherwise successfull request. Just
		// id is not found
		ResponseEntity<String> incorrectID = restTemplate.withBasicAuth(USERNAME, DEFAULT_PASSWORD)
				.postForEntity("/api/orders/1000000", request, String.class);
		assertEquals(HttpStatus.NOT_FOUND, incorrectID.getStatusCode());

		// Incorrect ticket ID
		TicketOrderDTO dto2 = new TicketOrderDTO();
		dto2.setTicketID(-1);
		dto2.setTicketPrice(10.00);
		HttpEntity<String> incorrectRequest = new HttpEntity<>("[" + dto2.toString() + "]", headers);
		ResponseEntity<String> incorrectTicketId = restTemplate.withBasicAuth(USERNAME, DEFAULT_PASSWORD)
				.postForEntity("/api/orders/" + ORDERID, incorrectRequest, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, incorrectTicketId.getStatusCode());

		// Finally try to sell tickets to an sold-out event.
		TicketOrderDTO soldOutDto = new TicketOrderDTO();
		soldOutDto.setTicketID(SOLD_OUT_TICKET);
		soldOutDto.setTicketPrice(15.00);
		HttpEntity<String> soldOutRequest = new HttpEntity<>("[" + soldOutDto.toString() + "]", headers);
		ResponseEntity<String> soldOutOrder = restTemplate.withBasicAuth(USERNAME, DEFAULT_PASSWORD)
				.postForEntity("/api/orders/" + SOLD_OUT_ORDER, soldOutRequest, String.class);
		assertEquals(HttpStatus.CONFLICT, soldOutOrder.getStatusCode());
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
			Event e3 = eRepo.save(new Event(DEFAULT_EVENTS[1], mockCity, "place3", 2, "", LocalDateTime.now()));
			TicketType tt = ttRepo.save(new TicketType("Normal"));
			Ticket ticket = tRepo.save(new Ticket(tt, e1, 10.00, ""));
			Ticket ticket3 = tRepo.save(new Ticket(tt, e3, 20.00, ""));
			TICKETID = ticket.getTicketID();
			SOLD_OUT_TICKET = ticket3.getTicketID();
			Orders order = oRepo.save(new Orders(LocalDateTime.now()));
			Orders order3 = oRepo.save(new Orders(LocalDateTime.now()));
			ORDERID = order.getOrderID();
			SOLD_OUT_ORDER = order3.getOrderID();
			TicketOrder to1 = toRepo.save(new TicketOrder(order, ticket, 9.00));
			TicketOrder to2 = toRepo.save(new TicketOrder(order, ticket, 10.00));
			TicketOrder to3 = toRepo.save(new TicketOrder(order3, ticket3, 19.00));
			TicketOrder to4 = toRepo.save(new TicketOrder(order3, ticket3, 19.00));
			to1.setCharacter();
			to2.setCharacter();
			to3.setCharacter();
			to4.setCharacter();
			toRepo.save(to1);
			toRepo.save(to2);
			toRepo.save(to3);
			toRepo.save(to4);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
