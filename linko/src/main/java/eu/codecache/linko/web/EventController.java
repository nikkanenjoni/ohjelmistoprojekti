package eu.codecache.linko.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.codecache.linko.domain.EventRepository;


@Controller
public class EventController {

	@GetMapping("/test")
	public String test(Model model) {
		return "test";
	}
	
	
	// Delete-toiminnallisuus:
	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET) //{id} is the path variable. you can delete by localhost/8080/idnumber
	 public String deleteBook(@PathVariable("id") Long eventID, Model model) { // saves it to the variable BookId
	 	EventRepository.deleteById(eventID);
	     return "redirect:../events";
	 }  

}
