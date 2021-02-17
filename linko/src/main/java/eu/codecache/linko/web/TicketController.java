package eu.codecache.linko.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import eu.codecache.linko.domain.OrderRepository;

/*
 * For the first demo app we will use H2 memory database.
 * You may access database from browser:
 *   localhost:8080/h2
 *   JDBC URL = jdbc:h2:mem:testdb (no password)
 */

@Controller
public class TicketController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		return "index";
	}
	
}
