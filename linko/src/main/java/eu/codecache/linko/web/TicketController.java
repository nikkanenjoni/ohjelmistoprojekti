package eu.codecache.linko.web;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	@Autowired
	private TicketOrderRepository ticketOrderRepo;
	@Autowired
	private UserEntityRepository ueRepo;
	@Autowired
	private UserAuthorizationRepository uaRepo;
	
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
	public @ResponseBody Ticket newTicket(@Valid @RequestBody Ticket ticket, Principal principal) {
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
	public @ResponseBody Ticket updateTicket(@PathVariable("id") Long ticketID, @Valid @RequestBody Ticket ticket, 
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

	// Single item
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(API_BASE + "/{id}")
//	public @ResponseBody Ticket findTicket(@PathVariable("id") Long ticketID) {
	public @ResponseBody List<Ticket> findByEvent(@PathVariable("id") Long eventID) {
		// We need to handle error and remove all the crap in comments :)
		try {
			Event event = eRepository.findByEventID(eventID);
			return tRepository.findByEvent(event);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		/*
		 * For now, let's keep this simple & stupid -> we don't handle case where ticket
		 * with the ID isn't found (let's fix that later, but for now, an issue on
		 * Github is enough)
		 */
//		return tRepository.findByTicketID(ticketID);
	}

	// Delete ticket
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = API_BASE + "/{id}", method = RequestMethod.DELETE) // {id} is the path variable. you can
																				// delete by localhost/8080/idnumber
	public String deleteTicket(@PathVariable("id") Long ticketID, Model model, Principal principal) throws Exception { // saves it to the variable eventID
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

	/*
	 * This method is broken, I've commented it out for now (Ville)
	 */
	// Delete-functionality:
//	@RequestMapping(value = "/api/tickets/delete/{id}", method = RequestMethod.GET)
//	public String deleteTicket(@PathVariable("id") Long ticketID, Model model) {
//		tRepository.deleteById(ticketID);
//		return "Ticket deleted";
//	}

	/* TICKETTYPE */

	// Get all tickettypes:
	// displays ALL tickets in the database
	@GetMapping("/api/tickettypes")
	public @ResponseBody List<TicketType> All() {
		return tyRepository.findAll();

	}

	// Save a new ticketType
	@PostMapping("/api/tickettypes")
	public @ResponseBody TicketType newTicketType(@RequestBody TicketType ticketType) {
		tyRepository.save(ticketType);
		return ticketType;
	}

	// Delete a ticketType

	@RequestMapping(value = "/api/tickettypes/{id}", method = RequestMethod.DELETE) // {id} is the path variable. you
																					// can
	// delete by localhost/8080/idnumber
	public String deleteTicketType(@PathVariable("id") Long ticketTypeID, Model model) { // saves it to the variable
																							// eventID
		tyRepository.deleteById(ticketTypeID);
		return "TicketType deleted";
	}


// This is a private method for checking to see, if user is actually an admin
private boolean isAdmin(Principal p) {
	return ueRepo.findByUsername(p.getName()).getUserAuth().getAuthorization().equals("ADMIN");
	}
}
