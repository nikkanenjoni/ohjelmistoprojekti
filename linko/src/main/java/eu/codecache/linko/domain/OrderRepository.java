package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

	// We need to be able to get a single city out from the database (Viittaa
	// Application-sivuun)
	Orders findByOrderID(Long orderID);

}
