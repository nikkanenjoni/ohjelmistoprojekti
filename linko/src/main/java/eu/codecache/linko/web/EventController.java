package eu.codecache.linko.web;

import java.util.List;
//import java.util.Optional;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

	// viittaus EventRepositoryyn/CityRepositoryyn. Autowire the repository so that
	// we can retrieve
	// and save data to database.
	@Autowired
	public EventRepository repository;
	public CityRepository cityrepository;

	// displays ALL events in the database
	@GetMapping("/api/events")
	public @ResponseBody List<Event> all() {
		return repository.findAll();
	}

	@PostMapping("/api/events")
	public @ResponseBody Event newEvent(@RequestBody Event event)
		throws Exception {
		
		if (event != null) {
		repository.save(event);
		return event;
	}else {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@ExceptionHandler
	@PutMapping("/api/events/{id}")
	public @ResponseBody Event updateEvent(@PathVariable("id") Long eventID, @RequestBody Event event)
			throws Exception {
		// first let's see if we have an event with the id
		Event dbEvent = repository.findByEventID(eventID);
		if (dbEvent != null) {
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
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		}
	}

	// näytä yksi tapahtuma (tulee tyhjä, jos ei toimi)
	// Single item
	@ExceptionHandler
	@GetMapping("/api/events/{id}")
	public @ResponseBody Event findEvent(@PathVariable("id") Long eventID) 
			throws Exception {

		if (eventID != null) {
			return repository.findByEventID(eventID);
			
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
			// } catch (EventNotFoundException e) {
			// throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		}
	}

	// Delete a event:
	@ExceptionHandler
	@RequestMapping(value = "/api/events/{id}", method = RequestMethod.DELETE) // {id} is the path variable. you can //
																				// delete by localhost/8080/idnumber
	public String deleteEvent(@PathVariable("id") Long eventID, Model model) 
			throws Exception { 
		if (eventID != null) {
			repository.deleteById(eventID);
			return "Event deleted";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		}

	}
}
