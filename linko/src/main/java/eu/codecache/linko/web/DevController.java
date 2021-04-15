package eu.codecache.linko.web;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
import eu.codecache.linko.domain.UserEntityRepository;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class DevController {

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

	@Autowired
	private UserEntityRepository ueRepo;

	@GetMapping("/api/dev")
	public @ResponseBody String resetDB(@RequestParam String reset, Principal principal) {
		if (!isAdmin(principal)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		if (!reset.equals("ok")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		// User is admin and want's to reset database, let's make it so
		if (!this.deleteAllFromDB()) {
			return "Failed to delete old DB";
		}
		cRepo.save(new City("Ilmala"));
		cRepo.save(new City("Rovaniemi"));
		ttRepo.save(new TicketType("Normaali"));
		ttRepo.save(new TicketType("Opiskelija"));
		Event e1 = eRepo.save(new Event("Hippafesti", cRepo.findCityByCity("Rovaniemi").get(0), "Hippakenttä", 1000,
				"Kuvaus tapahtumasta tähän.", LocalDateTime.now()));
		Event e2 = eRepo.save(new Event("Musadiggarit", cRepo.findCityByCity("Ilmala").get(0),
				"Mutakenttä jäähallin takana", 6, "", LocalDateTime.now()));
		Event e3 = eRepo.save(new Event("Antin rokkibändi", cRepo.findCityByCity("Rovaniemi").get(0), "Kellariklubi",
				150, "Hieno bändi!", LocalDateTime.now()));
		Event e4 = eRepo.save(new Event("Matin rokkibändi", cRepo.findCityByCity("Rovaniemi").get(0), "Pieniklubi", 100,
				"Hieno bändi!", LocalDateTime.now()));
		// let's add some tickets:
		tRepo.save(new Ticket(ttRepo.findTicketTypeByTicketType("Opiskelija").get(0), e1, 20.00, ""));
		tRepo.save(new Ticket(ttRepo.findTicketTypeByTicketType("Normaali").get(0), e1, 20.00, ""));
		tRepo.save(new Ticket(ttRepo.findTicketTypeByTicketType("Opiskelija").get(0), e2, 20.00, ""));
		tRepo.save(new Ticket(ttRepo.findTicketTypeByTicketType("Normaali").get(0), e2, 30.00, ""));
		// let's add orders:
		oRepo.save(new Orders(LocalDateTime.now()));
		oRepo.save(new Orders(LocalDateTime.now()));
		// now let's get one of the orders and add ticket to it
		List<Orders> orders = oRepo.findAll();
		List<Ticket> tickets = tRepo.findAll();
		TicketOrder to1 = toRepo.save(new TicketOrder(orders.get(0), tickets.get(0), tickets.get(0).getPrice()));
		TicketOrder to2 = toRepo.save(new TicketOrder(orders.get(0), tickets.get(1), tickets.get(1).getPrice()));
		to1.setCharacter();
		to2.setCharacter();
		toRepo.save(to1);
		toRepo.save(to2);

		return "Database reset";
	}

	private boolean deleteAllFromDB() {
		try {
			toRepo.deleteAll();
			oRepo.deleteAll();
			tRepo.deleteAll();
			ttRepo.deleteAll();
			eRepo.deleteAll();
			cRepo.deleteAll();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	private boolean isAdmin(Principal p) {
		return ueRepo.findByUsername(p.getName()).getUserAuth().getAuthorization().equals("ADMIN");
	}
}
