package eu.codecache.linko.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * For the first demo app we will use H2 memory database.
 * You may access database from browser:
 *   localhost:8080/h2
 *   JDBC URL = jdbc:h2:mem:testdb (no password)
 */

@Controller
public class ThymeController {
	@GetMapping("/")
	public String home(Model model) {
		return "index";
	}
}
