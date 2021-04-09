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
import eu.codecache.linko.domain.OrderRepository;
import eu.codecache.linko.domain.Orders;
import eu.codecache.linko.domain.Ticket;
import eu.codecache.linko.domain.TicketOrder;
import eu.codecache.linko.domain.TicketOrderRepository;
import eu.codecache.linko.domain.TicketRepository;
import eu.codecache.linko.domain.TicketType;
import eu.codecache.linko.domain.TicketTypeRepository;
import eu.codecache.linko.domain.UserAuthorization;
import eu.codecache.linko.domain.UserAuthorizationRepository;
import eu.codecache.linko.domain.UserEntity;
import eu.codecache.linko.domain.UserEntityRepository;

@SpringBootApplication
public class LinkoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkoApplication.class, args);
	}

	@Bean
	public CommandLineRunner h2Filler(CityRepository cRepo, EventRepository eRepo, TicketRepository tRepo,
			TicketTypeRepository ttRepo, TicketOrderRepository toRepo, OrderRepository oRepo,
			UserEntityRepository ueRepo, UserAuthorizationRepository uaRepo) {
		return (args) -> {
			System.out.println("Running CommandLineRunner");
			/*
			 * Let's fill H2-database with some testdata to test the server & API
			 * 
			 * To make queries directly to database (and to confirm there is data), visit:
			 * localhost:8080/h2
			 */
//			cRepo.save(new City("Ilmala"));
//			cRepo.save(new City("Rovaniemi"));
//			ttRepo.save(new TicketType("Normaali"));
//			ttRepo.save(new TicketType("Opiskelija"));
//			eRepo.save(new Event("Hippafesti", cRepo.findCityByCity("Rovaniemi").get(0), "Hippakenttä", 1000,
//					"Kuvaus tapahtumasta tähän.", LocalDateTime.now()));
//			eRepo.save(new Event("Musadiggarit", cRepo.findCityByCity("Ilmala").get(0), "Mutakenttä jäähallin takana",
//					6, "", LocalDateTime.now()));
//			eRepo.save(new Event("Antin rokkibändi", cRepo.findCityByCity("Rovaniemi").get(0), "Kellariklubi", 150,
//					"Hieno bändi!", LocalDateTime.now()));
//			eRepo.save(new Event("Matin rokkibändi", cRepo.findCityByCity("Rovaniemi").get(0), "Pieniklubi", 100,
//					"Hieno bändi!", LocalDateTime.now()));
//			// let's add some tickets:
//			tRepo.save(new Ticket(ttRepo.findTicketTypeByTicketType("Opiskelija").get(0), eRepo.findByEventID(5), 20.00,
//					""));
//			tRepo.save(new Ticket(ttRepo.findTicketTypeByTicketType("Normaali").get(0), eRepo.findByEventID(5), 20.00,
//					""));
//			tRepo.save(new Ticket(ttRepo.findTicketTypeByTicketType("Opiskelija").get(0), eRepo.findByEventID(6), 20.00,
//					""));
//			tRepo.save(new Ticket(ttRepo.findTicketTypeByTicketType("Normaali").get(0), eRepo.findByEventID(6), 30.00,
//					""));
			// let's add orders:
//			oRepo.save(new Orders(LocalDateTime.now()));
//			oRepo.save(new Orders(LocalDateTime.now()));
			// now let's get one of the orders and add ticket to it
//			List<Orders> orders = oRepo.findAll();
//			List<Ticket> tickets = tRepo.findAll();
//			toRepo.save(new TicketOrder(orders.get(0), tickets.get(0), tickets.get(0).getPrice()));
//			toRepo.save(new TicketOrder(orders.get(0), tickets.get(1), tickets.get(1).getPrice()));

			// let's add some users
			UserAuthorization adminAuth = uaRepo.findByAuthorization("ADMIN");
			if (adminAuth == null) {
				adminAuth = uaRepo.save(new UserAuthorization("ADMIN"));
				UserEntity admin1 = new UserEntity("admin", adminAuth, "");
				admin1.setPassword("password", "password");
				ueRepo.save(admin1);
			}
			UserAuthorization userAuth = uaRepo.findByAuthorization("USER");
			if (userAuth == null) {
				userAuth = uaRepo.save(new UserAuthorization("USER"));
				UserEntity user1 = new UserEntity("user", userAuth, "");
				user1.setPassword("password", "password");
				ueRepo.save(user1);
			}
		};
	}

}
