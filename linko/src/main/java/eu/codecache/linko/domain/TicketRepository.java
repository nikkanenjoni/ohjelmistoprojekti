package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.codecache.linko.exception.EventNotFoundException;
import eu.codecache.linko.exception.TicketNotFoundException;

// let's implement this once we get this to work on events. 
// import eu.codecache.linko.exception.TicketNotFoundException;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	
	// get-toiminnallisuus ( hae kaikki liput )
	List<Ticket> findAll();


	// get single ticket
	Ticket findByTicketID(long ticketID) throws TicketNotFoundException;
	
	// Lists tickets by event
	List<Ticket> findByEvent(Event event);

	// delete-toiminnallisuus
	void deleteById(long ticketID) throws TicketNotFoundException;


}
