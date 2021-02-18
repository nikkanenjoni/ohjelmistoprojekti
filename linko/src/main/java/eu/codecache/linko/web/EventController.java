package eu.codecache.linko.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {

	@GetMapping("/test")
	public String test(Model model) {
		return "test";
	}
}
