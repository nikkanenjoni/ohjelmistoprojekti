package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	
	// get all tickets-toiminnallisuus (TicketController)
	List<Ticket> findAll();


}
