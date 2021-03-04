package eu.codecache.linko.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.codecache.linko.domain.Event;
import eu.codecache.linko.domain.OrderRepository;
import eu.codecache.linko.domain.Ticket;
import eu.codecache.linko.domain.TicketOrderRepository;
import eu.codecache.linko.domain.TicketRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eu.codecache.linko.domain.CityRepository;
import eu.codecache.linko.domain.Event;
import eu.codecache.linko.domain.EventRepository;
import eu.codecache.linko.domain.OrderRepository;
import eu.codecache.linko.domain.Ticket;
import eu.codecache.linko.domain.TicketRepository;
import eu.codecache.linko.exception.EventNotFoundException;
import eu.codecache.linko.exception.TicketNotFoundException;

@RestController
public class TicketController {

	
	//controlleriin liittyvät repot (alustavasti)
	@Autowired
	public OrderRepository Orepository;
	public TicketRepository Trepository;
	public TicketOrderRepository ticketorderRepo;
	
	// displays ALL tickets in the database
	@GetMapping("/api/tickets")
	public @ResponseBody List<Ticket> all() {
		return Trepository.findAll();
	}
	

	@PostMapping("/api/tickets")
	public @ResponseBody Ticket newTicket(@RequestBody Ticket ticket) {
		Trepository.save(ticket);

		return ticket;
	}

	
	@PutMapping("/api/tickets/{id}")
	public @ResponseBody Ticket updateTicket(@PathVariable("id") Long ticketID, @RequestBody Ticket ticket) {
		// first let's see if we have an event with the id
		try {
			Ticket dbTicket = Trepository.findByTicketID(ticketID);
			// ok, we have found the event
			// update it with the new information
			dbTicket.setTicketType(ticket.getTicketType());
			dbTicket.setEvent(ticket.getEvent());
			dbTicket.setDescription(ticket.getDescription());
			// now we should be able to (over)write to database without accidentally
			// creating a new event
			Trepository.save(dbTicket);
			// return the updated event
			return dbTicket;
		} catch (TicketNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		}
	}
	
	
	// näytä yksi tapahtuma
	// Single item
	@GetMapping("/api/tickets/{id}")
	public @ResponseBody Ticket findTicket(@PathVariable("id") Long ticketID) {

		try {
			return Trepository.findByTicketID(ticketID);
		} catch (TicketNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		}
	}

	// Delete-toiminnallisuus:
	@RequestMapping(value = "/api/tickets/delete/{id}", method = RequestMethod.GET) // {id} is the path variable. you can
																					// delete by localhost/8080/idnumber
	public String deleteTicket(@PathVariable("id") Long ticketID, Model model) { // saves it to the variable ticketID
		Trepository.deleteById(ticketID);
		return "Ticket deleted";
	}

}

