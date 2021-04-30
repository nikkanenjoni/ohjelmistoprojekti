package eu.codecache.linko.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TicketOrderRepositoryTest {

	// These are for easier modifications
	private final String[] DEFAULT_EVENTS = { "First event!", "Name of event 2" };
	private long event1ID;
//	private String CODE1;
//	private String CODE2;

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
	 * Set mock data to H2 for tests
	 */
	@BeforeEach
	public void setDataToDB() {
		try {
			City mockCity = new City("Mock");
			cRepo.save(mockCity);
			Event e1 = eRepo
					.save(new Event(DEFAULT_EVENTS[0], mockCity, "place1", 1000, "some desc", LocalDateTime.now()));
			this.event1ID = e1.getEventID();
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
//			this.CODE1 = to1.getCode();
//			this.CODE2 = to2.getCode();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	// Test getting the cound of sold tickets
	@Test
	public void soldTicketCountTest() throws Exception {
		int count = toRepo.soldTicketCount(this.event1ID);
		List<TicketOrder> ticketOrders = toRepo.findAll();
		List<Ticket> tickets = tRepo.findByEvent(eRepo.findByEventID(event1ID));
		int count2 = 0;
		for (TicketOrder to : ticketOrders) {
			for (Ticket t : tickets) {
				if (t.getTicketID() == to.getTicket().getTicketID()) {
					count2++;
				}
			}
		}
		assertEquals(count2, count);
		assertFalse(1 == count);
		assertTrue(2 == count);
	}
}
