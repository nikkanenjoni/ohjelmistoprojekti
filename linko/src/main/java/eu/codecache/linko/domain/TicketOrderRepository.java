package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TicketOrderRepository extends CrudRepository<TicketOrder, Long> {

	// we need to find what tickets are included in specific order
	List<TicketOrder> findByOrder(Order order);
	
	// For now I guess this is all we need...
}
