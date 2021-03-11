package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//import eu.codecache.linko.web.TicketType;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
	
	// We need to be able to get a single tickettype out from the database (Viittaa Application-sivuun)
	List<TicketType> findTicketTypeByTicketType(String ticketType);
	
	// List all tickettypes
	List<TicketType> findAll();
	
	// delete a ticketType
	void deleteById(long ticketTypeID);

}
