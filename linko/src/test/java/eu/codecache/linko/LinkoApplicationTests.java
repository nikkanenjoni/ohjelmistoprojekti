package eu.codecache.linko;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import eu.codecache.linko.web.TicketController;

@RunWith(SpringRunner.class)
@SpringBootTest
class LinkoApplicationTests {

	@Autowired
	private TicketController ticketController;

	@Test
	void contextLoads() throws Exception {
		assertThat(ticketController).isNotNull();
	}

}
