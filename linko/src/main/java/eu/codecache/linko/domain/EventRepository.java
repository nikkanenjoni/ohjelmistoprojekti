package eu.codecache.linko.domain;

import java.util.List;
import java.util.Optional;


import org.springframework.data.repository.CrudRepository;


public interface EventRepository extends CrudRepository<Event, Long> {
	
	// get-toiminnallisuus ( hae kaikki tapahtumat )
	List<Event> findAll();
	
	//get, yksi tapahtuma
	Optional<Event> findById(Long eventID);
	
	// delete-toiminnallisuus
	void deleteById(Long eventID);

}