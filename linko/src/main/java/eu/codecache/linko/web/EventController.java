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
@RestController
public class EventController {

	// viittaus EventRepositoryyn/CityRepositoryyn. Autowire the repository so that
	// we can retrieve
	// and save data to database.
	@Autowired
	private EventRepository repository;
	@Autowired
	private CityRepository cityrepository;
	@Autowired
	private UserEntityRepository ueRepo;
	@Autowired
	private UserAuthorizationRepository uaRepo;

	// displays ALL events in the database
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/api/events")
	public @ResponseBody List<Event> all(Principal principal) {
		return repository.findAll();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/events")
	public @ResponseBody Event newEvent(@Valid @RequestBody Event event, Principal principal) throws Exception {
		// Let's only allow admins to add events
		if (isAdmin(principal)) {
			repository.save(event);
			return event;
		} else {
			// if not an admin, let's throw exception
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/api/events/{id}")
	public @ResponseBody Event updateEvent(@PathVariable("id") Long eventID, @Valid @RequestBody Event event)
			throws Exception {
		// first let's see if we have an event with the id
		try {
			Event dbEvent = repository.findByEventID(eventID);
			// ok, we have found the event
			// update it with the new information
			dbEvent.setEvent(event.getEvent());
			dbEvent.setEventPlace(event.getEventPlace());
			dbEvent.setCapacity(event.getCapacity());
			dbEvent.setDatetime(event.getDatetime());
			// now we should be able to (over)write to database without accidentally
			// creating a new event
			repository.save(dbEvent);
			// return the updated event
			return dbEvent;
		} catch (EventNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		}
	}

	// näytä yksi tapahtuma
	// Single item
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/api/events/{id}")
	public @ResponseBody Event findEvent(@PathVariable("id") Long eventID) throws EventNotFoundException {
		if (eventID == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		} else {
			return repository.findByEventID(eventID);
			// } catch (EventNotFoundException e) {
			// throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		}
	}

	// Delete a event:
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/events/{id}", method = RequestMethod.DELETE)
	public String deleteEvent(@PathVariable("id") Long eventID, Model model) throws EventNotFoundException {
		if (eventID == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		} else {
			repository.deleteById(eventID);
			return "Event deleted";
		}

	}

	// This is a private method for checking to see, if user is actually an admin
	private boolean isAdmin(Principal p) {
		return ueRepo.findByUsername(p.getName()).getUserAuth().getAuthorization().equals("ADMIN");
	}
}
