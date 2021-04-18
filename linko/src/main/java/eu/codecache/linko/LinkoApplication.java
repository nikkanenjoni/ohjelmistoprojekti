package eu.codecache.linko;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

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
	
	// copy & paste
	// https://www.baeldung.com/java-generating-barcodes-qr-codes
	@Bean
	public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
		return new BufferedImageHttpMessageConverter();
	}

	@Bean
	public CommandLineRunner h2Filler(UserEntityRepository ueRepo, UserAuthorizationRepository uaRepo) {
		return (args) -> {
			System.out.println("Running CommandLineRunner");
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
