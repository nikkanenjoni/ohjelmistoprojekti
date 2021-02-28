package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.codecache.linko.exception.EventNotFoundException;


public interface EventRepository extends JpaRepository<Event, Long> {

	// get-toiminnallisuus ( hae kaikki tapahtumat )
	List<Event> findAll();
	
	//get, yksi tapahtuma
	List<Event> findByEventID(long eventID) throws EventNotFoundException;
	
	// delete-toiminnallisuus
	void deleteById(long eventID);

	
}