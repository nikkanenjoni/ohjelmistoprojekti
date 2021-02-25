package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
	
	// get-toiminnallisuus ( hae kaikki tapahtumat )
	List<Event> findAll();
	
	// delete-toiminnallisuus
	void deleteById(Long eventID);

}