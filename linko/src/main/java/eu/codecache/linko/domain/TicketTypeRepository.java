package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
	
	// We need to be able to get a single tickettype out from the database (Viittaa Application-sivuun)
	List<TicketType> findTicketTypeByTicketType(String ticketType);

}
