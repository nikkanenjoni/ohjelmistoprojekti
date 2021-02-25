package eu.codecache.linko.web;


import java.util.List;
import java.util.Optional;
<<<<<<< HEAD

import javax.validation.Valid;

=======
>>>>>>> 3ad69f86818069f808dc2a61f872a53513cf8b01

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
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

import eu.codecache.linko.domain.Event;
import eu.codecache.linko.domain.EventRepository;


@Controller
public class EventController {
	
	// viittaus EventRepositoryyn. Autowire the repository so that we can retrieve and save data to database.
	@Autowired
	public EventRepository repository;

	@GetMapping("/test")
	public String test(Model model) {
		return "test";
	}
	
	// näytä kaikki tapahtumat
	@GetMapping("/events")
	public List<Event> all() {
		return repository.findAll();
	}
	
<<<<<<< HEAD
	@PostMapping("/events")
		Event newEmployee(@RequestBody Event newEvent) {
		return repository.save(newEvent);
	  }
	
	@PutMapping(path="/events")
	public Event update(@RequestBody Event eventID) {
	    return repository.save(eventID);
	}
=======
	// näytä yksi tapahtuma
	// Single item
	 @GetMapping("/events/{id}")
	public Optional<Event> findById(Long eventID) {

	    return repository.findById(eventID);
	 }
	
>>>>>>> 3ad69f86818069f808dc2a61f872a53513cf8b01
	
	// Delete-toiminnallisuus:
	 @RequestMapping(value = "/events/delete/{id}", method = RequestMethod.GET) //{id} is the path variable. you can delete by localhost/8080/idnumber
	 public String deleteEvent(@PathVariable("id") Long eventID, Model model) { // saves it to the variable eventID
	 	repository.deleteById(eventID);
	     return "redirect:../events";
	 }  

}
