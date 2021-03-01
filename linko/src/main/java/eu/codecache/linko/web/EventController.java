package eu.codecache.linko.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

//import eu.codecache.linko.domain.City;
import eu.codecache.linko.domain.CityRepository;
import eu.codecache.linko.domain.Event;
import eu.codecache.linko.domain.EventRepository;
import eu.codecache.linko.exception.EventNotFoundException;

/*
 * Changed from @Controller -> @RestController (Ville)
 */
@RestController
public class EventController {

	// viittaus EventRepositoryyn/CityRepositoryyn. Autowire the repository so that we can retrieve
	// and save data to database.
	@Autowired
	public EventRepository repository;
	public CityRepository cityrepository;

	// displays ALL events in the database
	@GetMapping("/api/events")
	public @ResponseBody List<Event> all() {
		return repository.findAll();
	}

	@PostMapping("/api/event")
	public @ResponseBody Event newEvent(@RequestBody Event event) {
		repository.save(event);
		
		return event;
	}

	@PutMapping("/api/event/{id}")
	public @ResponseBody Event updateEvent(@PathVariable("id") Long eventID, @RequestBody Event event) {
		// first let's see if we have an event with the id
		try {
			List<Event> oldEventList = repository.findByEventID(eventID);
			if (oldEventList.size() > 0) {
				// ok, we have found the event
				Event oldEvent = oldEventList.get(0);
				// update it with the new information
				oldEvent.setEvent(event.getEvent());
				oldEvent.setEventPlace(event.getEventPlace());
				oldEvent.setCapacity(event.getCapacity());
				oldEvent.setDatetime(event.getDatetime());
				// now we should be able to (over)write to database without accidentally
				// creating a new event
				repository.save(oldEvent);
				// return the updated event
				return oldEvent;
			}
			// if we didn't find the event, throw exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
			// this has to be cleaned up later...
		} catch (EventNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		}
	}

	// näytä yksi tapahtuma
	// Single item
	@GetMapping("/api/event/{id}")
	public @ResponseBody Event findEvent(@PathVariable("id") Long eventID) {

		try {
			return repository.findByEventID(eventID).get(0);
		} catch (EventNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		}
	}

	// Delete-toiminnallisuus:
	@RequestMapping(value = "/api/events/delete/{id}", method = RequestMethod.GET) // {id} is the path variable. you can
																				// delete by localhost/8080/idnumber
	public String deleteEvent(@PathVariable("id") Long eventID, Model model) { // saves it to the variable eventID
		repository.deleteById(eventID);
		return "Event deleted";
	}

}
