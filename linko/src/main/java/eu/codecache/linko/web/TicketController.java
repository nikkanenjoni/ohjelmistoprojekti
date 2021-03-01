package eu.codecache.linko.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.codecache.linko.domain.Event;
import eu.codecache.linko.domain.OrderRepository;
import eu.codecache.linko.domain.Ticket;
import eu.codecache.linko.domain.TicketOrderRepository;
import eu.codecache.linko.domain.TicketRepository;

@Controller
public class TicketController {
	
	
	//controlleriin liittyvät repot (alustavasti)
	@Autowired
	public OrderRepository orderRepo;
	public TicketRepository ticketRepo;
	public TicketOrderRepository ticketorderRepo;
	
	// displays ALL tickets in the database
	@GetMapping("/api/tickets")
	public @ResponseBody List<Ticket> all() {
		return ticketRepo.findAll();
	}
	
	
}
