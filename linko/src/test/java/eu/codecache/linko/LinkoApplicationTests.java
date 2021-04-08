package eu.codecache.linko;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import eu.codecache.linko.web.EventController;
import eu.codecache.linko.web.OrderController;
import eu.codecache.linko.web.TicketController;
import eu.codecache.linko.web.TicketOrderController;

/*
 * Smoketest all controllers
 */

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
class LinkoApplicationTests {

	// Autowire all controllers
	@Autowired
	private EventController eventController;
	@Autowired
	private OrderController orderController;
	@Autowired
	private TicketController ticketController;
	@Autowired
	private TicketOrderController ticketOrderController;

	@Test
	void contextLoads() throws Exception {
		assertThat(eventController).isNotNull();
		assertThat(orderController).isNotNull();
		assertThat(ticketController).isNotNull();
		assertThat(ticketOrderController).isNotNull();
	}

}
