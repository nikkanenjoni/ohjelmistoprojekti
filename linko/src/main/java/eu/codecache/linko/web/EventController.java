package eu.codecache.linko.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@GetMapping("/events")
	public List<Event> retrieveAllEvents() {
		return (List<Event>) repository.findAll();
	}
	
	
	// Delete-toiminnallisuus:
	 @RequestMapping(value = "/events/delete/{id}", method = RequestMethod.GET) //{id} is the path variable. you can delete by localhost/8080/idnumber
	 public String deleteBook(@PathVariable("id") Long eventID, Model model) { // saves it to the variable eventID
	 	repository.deleteById(eventID);
	     return "redirect:../events";
	 }  

}
