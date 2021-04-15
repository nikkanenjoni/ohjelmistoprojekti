package eu.codecache.linko.web;

import java.security.Principal;
import java.util.List;
//import java.util.Optional;

import javax.validation.Valid;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

//import eu.codecache.linko.domain.City;
import eu.codecache.linko.domain.CityRepository;
import eu.codecache.linko.domain.Event;
import eu.codecache.linko.domain.EventRepository;
import eu.codecache.linko.domain.UserAuthorizationRepository;
import eu.codecache.linko.domain.UserEntityRepository;
import eu.codecache.linko.exception.EventNotFoundException;

/*
 * Changed from @Controller -> @RestController (Ville)
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class EventController {

	@Autowired
	private EventRepository eRepo;
	@Autowired
	private CityRepository cRepo;
	@Autowired
	private UserEntityRepository ueRepo;
	@Autowired
	private UserAuthorizationRepository uaRepo;

	private final String API_BASE = "/api/events";

	// displays ALL events in the database
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(API_BASE)
	public @ResponseBody List<Event> all() {
		return eRepo.findAll();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(API_BASE)
	public @ResponseBody Event newEvent(@Valid @RequestBody Event event, Principal principal) throws Exception {
		// Let's only allow admins to add events
		if (isAdmin(principal)) {
			eRepo.save(event);
			return event;
		} else {
			// if not an admin, let's throw exception
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(API_BASE + "/{id}")
	public @ResponseBody Event updateEvent(@PathVariable("id") Long eventID, @Valid @RequestBody Event event,
			Principal principal) throws Exception {
		// This is admin only method
		if (!isAdmin(principal)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		// ok, user is admin
		// let's see if we have an event with the id
		Event dbEvent = eRepo.findByEventID(eventID);
		if (dbEvent != null) {
			// ok, we have found the event
			// update it with the new information
			dbEvent.setEvent(event.getEvent());
			dbEvent.setEventPlace(event.getEventPlace());
			dbEvent.setCapacity(event.getCapacity());
			dbEvent.setDatetime(event.getDatetime());
			// now we should be able to (over)write to database without accidentally
			// creating a new event
			try {
				eRepo.save(dbEvent);
			} catch (Exception e) {
				// For some reason we failed to write to DB, I guess this is a good enough
				// response in this case
				throw new ResponseStatusException(HttpStatus.INSUFFICIENT_STORAGE);
			}
			// return the updated event
			return dbEvent;
		} else {
			// No event found with the id, so we can't update it
			// Let's throw NOT_FOUND
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		}
	}

	// Single item
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(API_BASE + "/{id}")
	public @ResponseBody Event findEvent(@PathVariable("id") Long eventID) throws Exception {
		Event event = eRepo.findByEventID(eventID);
		if (event == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		} else {
			return event;
		}
	}

	// Delete an event
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = API_BASE + "/{id}", method = RequestMethod.DELETE)
	public String deleteEvent(@PathVariable("id") Long eventID, Model model, Principal principal) throws Exception {
		// This is admin only stuff, so let's check that first
		if (!isAdmin(principal)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		Event event = eRepo.findByEventID(eventID);
		if (event == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		} else {
			eRepo.deleteById(eventID);
			return "Event deleted";
		}
	}

	// This is a private method for checking to see, if user is actually an admin
	private boolean isAdmin(Principal p) {
		return ueRepo.findByUsername(p.getName()).getUserAuth().getAuthorization().equals("ADMIN");
	}
}
