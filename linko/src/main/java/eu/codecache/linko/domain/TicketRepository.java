package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// let's implement this once we get this to work on events. 
// import eu.codecache.linko.exception.TicketNotFoundException;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	
	// get-toiminnallisuus ( hae kaikki liput )
	List<Ticket> findAll();


	// get, yksi lippu
	// this is not working as intented, if eventID is not found,
	// it return empty event instead of throwing EventNotFoundException
	// ... I wonder if this could be fixed somehow?
	Ticket findByTicketID(long ticketID);

	// delete-toiminnallisuus
	void deleteById(long ticketID);


}
