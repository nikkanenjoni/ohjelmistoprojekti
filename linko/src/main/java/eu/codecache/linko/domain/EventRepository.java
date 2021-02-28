package eu.codecache.linko.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface EventRepository extends JpaRepository<Event, Long> {

	// get-toiminnallisuus ( hae kaikki tapahtumat )
	List<Event> findAll();
	
	//get, yksi tapahtuma
	List<Event> findById(long eventID);
	
	// delete-toiminnallisuus
	void deleteById(long eventID);

	
}