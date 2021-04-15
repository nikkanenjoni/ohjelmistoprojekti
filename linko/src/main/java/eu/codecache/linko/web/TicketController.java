package eu.codecache.linko.web;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import eu.codecache.linko.domain.Event;
import eu.codecache.linko.domain.OrderRepository;
import eu.codecache.linko.domain.Ticket;
import eu.codecache.linko.domain.TicketOrderRepository;
import eu.codecache.linko.domain.TicketRepository;
import eu.codecache.linko.domain.TicketType;
import eu.codecache.linko.domain.TicketTypeRepository;
import eu.codecache.linko.domain.UserAuthorizationRepository;
import eu.codecache.linko.domain.UserEntityRepository;

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
import eu.codecache.linko.domain.EventRepository;

import eu.codecache.linko.exception.EventNotFoundException;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class TicketController {

	@Autowired
	private EventRepository eRepository;
	@Autowired
	private OrderRepository oRepository;
	@Autowired
	private TicketRepository tRepository;
	@Autowired
	private TicketTypeRepository tyRepository;
//	@Autowired
//	private TicketOrderRepository ticketOrderRepo;
	@Autowired
	private UserEntityRepository ueRepo;
//	@Autowired
//	private UserAuthorizationRepository uaRepo;

	private final String API_BASE = "/api/tickets";

	// displays ALL tickets in the database
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(API_BASE)
	public @ResponseBody List<Ticket> all() {
		return tRepository.findAll();
	}

	// Post new ticket by giving it 1) type, 2) event and 3) price
	// This needs to be documented !!!
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(API_BASE)
	public @ResponseBody Ticket newTicket(@Valid @RequestBody Ticket ticket, Principal principal) throws Exception {
		if (!isAdmin(principal)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		long newID = tRepository.save(ticket).getTicketID();
		tRepository.flush();
		// I just can't understand why this returns all null while GetMapping with
		// same return statement works as expected
		return tRepository.findByTicketID(newID);
	}

	/*
	 * this one needs quite some fixing, but we have bigger priorities at the moment
	 */
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(API_BASE + "/{id}")
	public @ResponseBody Ticket updateTicket(@PathVariable("id") long ticketID, @Valid @RequestBody Ticket ticket,
			Principal principal) throws Exception {
		// This is admin only method
		if (!isAdmin(principal)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		// first let's see if we have an ticket with the id
		Ticket dbTicket = tRepository.findByTicketID(ticketID);
		if (dbTicket != null) {
			// ok, we have found the ticket
			// update it with the new information
			dbTicket.setTicketType(ticket.getTicketType());
			dbTicket.setEvent(ticket.getEvent());
			dbTicket.setPrice(ticket.getPrice());
			dbTicket.setDescription(ticket.getDescription());
			// now we should be able to (over)write to database without accidentally
			// creating a new ticket
			try {
				tRepository.save(dbTicket);
			} catch (Exception e) {
				// response in this case
				throw new ResponseStatusException(HttpStatus.INSUFFICIENT_STORAGE);
			}
			// return the updated ticket
			return dbTicket;
		} else {
			// No ticket found with the id, so we can't update it
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
		}
	}

	// List tickets available for a single event
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(API_BASE + "/{id}")
	public @ResponseBody List<Ticket> findByEvent(@PathVariable("id") long eventID) {
		Event event = eRepository.findByEventID(eventID);
		if (event != null) {
			return tRepository.findByEvent(event);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	// Delete ticket
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = API_BASE + "/{id}", method = RequestMethod.DELETE)
	public String deleteTicket(@PathVariable("id") Long ticketID, Model model, Principal principal) throws Exception {
		if (!isAdmin(principal)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		Ticket ticket = tRepository.findByTicketID(ticketID);
		if (ticket == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
		} else {
			tRepository.deleteById(ticketID);
			return "Ticket deleted";
		}
	}

	/* TICKETTYPE */

	// Get all tickettypes:
	// displays ALL tickets in the database
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/api/tickettypes")
	public @ResponseBody List<TicketType> All() {
		return tyRepository.findAll();
	}

	// Save a new ticketType
	@PostMapping("/api/tickettypes")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody TicketType newTicketType(@Valid @RequestBody TicketType ticketType, Principal principal)
			throws Exception {
		if (isAdmin(principal)) {
			tyRepository.save(ticketType);
			return ticketType;
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
	}

	// Delete a ticketType
	@RequestMapping(value = "/api/tickettypes/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteTicketType(@PathVariable("id") Long ticketTypeID, Model model, Principal principal)
			throws Exception {
		if (isAdmin(principal)) {
			tyRepository.deleteById(ticketTypeID);
			return "TicketType deleted";
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
	}

	// This is a private method for checking to see, if user is actually an admin
	private boolean isAdmin(Principal p) {
		return ueRepo.findByUsername(p.getName()).getUserAuth().getAuthorization().equals("ADMIN");
	}
}
