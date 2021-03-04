package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

	// We need to be able to get a single city out from the database (Viittaa Application-sivuun)
	List<Order> findOrderByOrderID(Long orderID);

}
