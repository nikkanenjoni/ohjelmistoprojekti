package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long> {

	// we need to find what tickets are included in specific order
	List<TicketOrder> findByOrder(Orders order);
	
	// We need to find a specific ticketOrder
	TicketOrder findByTicketOrderID(Long ticktOrderID);
	
	// delete ticket, ticketorder:
	void deleteById(long ticketOrderID);
	
	// find ticket by code
	TicketOrder findByCode(String code);

}
