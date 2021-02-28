package eu.codecache.linko;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import eu.codecache.linko.domain.City;
import eu.codecache.linko.domain.CityRepository;
import eu.codecache.linko.domain.Event;
import eu.codecache.linko.domain.EventRepository;

@SpringBootApplication
public class LinkoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkoApplication.class, args);
	}

	@Bean
	public CommandLineRunner h2Filler(CityRepository cRepo, EventRepository eRepo) {
		return (args) -> {
			System.out.println("Running CommandLineRunner");
			/*
			 * Let's fill H2-database with some testdata to test the server & API
			 * 
			 * To make queries directly to database (and to confirm there is data), 
			 * visit: localhost:8080/h2
			 */
			cRepo.save(new City("Ilmala"));
			cRepo.save(new City("Rovaniemi"));
			eRepo.save(new Event("Hippafesti", cRepo.findCityByCity("Rovaniemi").get(0), "Hippakenttä", 1000,
					"Kuvaus tapahtumasta tähän.", LocalDateTime.now()));
			eRepo.save(new Event("Musadiggarit", cRepo.findCityByCity("Ilmala").get(0), "Mutakenttä jäähallin takana",
					6, "", LocalDateTime.now()));
			eRepo.save(new Event("Antin rokkibändi", cRepo.findCityByCity("Rovaniemi").get(0), "Kellariklubi", 150,
					"Hieno bändi!", LocalDateTime.now()));
		};
	}

}
