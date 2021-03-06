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

@RestController
public class TicketController {

	@Autowired
	public OrderRepository oRepository;
	public TicketRepository tRepository;
	public TicketOrderRepository ticketOrderRepo;

	// displays ALL tickets in the database
	@GetMapping("/api/tickets")
	public @ResponseBody List<Ticket> all() {
		return tRepository.findAll();
	}

	@PostMapping("/api/tickets")
	public @ResponseBody Ticket newTicket(@RequestBody Ticket ticket) {
		tRepository.save(ticket);
		return ticket;
	}

	/*
	 * this one needs quite some fixing, but we have bigger priorities at the moment
	 */
	@PutMapping("/api/tickets/{id}")
	public @ResponseBody Ticket updateTicket(@PathVariable("id") Long ticketID, @RequestBody Ticket ticket) {
		// first let's see if we have an event with the id
		Ticket dbTicket = tRepository.findByTicketID(ticketID);
		// ok, we have found the event
		// update it with the new information
		dbTicket.setTicketType(ticket.getTicketType());
		dbTicket.setEvent(ticket.getEvent());
		dbTicket.setDescription(ticket.getDescription());
		// now we should be able to (over)write to database without accidentally
		// creating a new event
		tRepository.save(dbTicket);
		// return the updated event
		return dbTicket;
	}

	// Single item
	@GetMapping("/api/tickets/{id}")
	public @ResponseBody Ticket findTicket(@PathVariable("id") Long ticketID) {
		/*
		 * For now, let's keep this simple & stupid -> we don't handle case where ticket
		 * with the ID isn't found (let's fix that later, but for now, an issue on
		 * Github is enough)
		 */
		return tRepository.findByTicketID(ticketID);
	}

	// Delete-functionality:
	@RequestMapping(value = "/api/tickets/delete/{id}", method = RequestMethod.GET)
	public String deleteTicket(@PathVariable("id") Long ticketID, Model model) {
		tRepository.deleteById(ticketID);
		return "Ticket deleted";
	}

}
