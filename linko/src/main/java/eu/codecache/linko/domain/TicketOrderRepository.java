package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long> {

	// we need to find what tickets are included in specific order
	List<TicketOrder> findByOrder(Orders order);

	// We need to find a specific ticketOrder
	TicketOrder findByTicketOrderID(Long ticktOrderID);

	// delete ticket, ticketorder:
	void deleteById(long ticketOrderID);

	// find ticket by code
	TicketOrder findByCode(String code);

	// Get count of orders by event
	@Query(value = "SELECT COUNT(*) FROM ticket_order LEFT JOIN ticket ON ticket_order.ticketid=ticket.ticketid LEFT JOIN event ON ticket.eventid=event.eventid WHERE event.eventid=?1", nativeQuery = true)
	int soldTicketCount(long eventid);
}
