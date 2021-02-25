package eu.codecache.linko.web;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


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
	
	// viittaus EventRepositoryyn
	@Autowired
	public EventRepository repository;

	@GetMapping("/test")
	public String test(Model model) {
		return "test";
	}
	
	@GetMapping("/events")
	public List<Event> retrieveAllEvents() {
		return (List<Event>) repository.findAll();
	}
	
	@PostMapping("/events")
		Event newEmployee(@RequestBody Event newEvent) {
		return repository.save(newEvent);
	  }
	
	@PutMapping(path="/events")
	public Event update(@RequestBody Event eventID) {
	    return repository.save(eventID);
	}
	
	// Delete-toiminnallisuus:
	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET) //{id} is the path variable. you can delete by localhost/8080/idnumber
	 public String deleteBook(@PathVariable("id") Long eventID, Model model) { // saves it to the variable BookId
	 	repository.deleteById(eventID);
	     return "redirect:../events";
	 }  

}
