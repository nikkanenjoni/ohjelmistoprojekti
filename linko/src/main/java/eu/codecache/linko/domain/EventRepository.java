package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event, Long> {

	// get-toiminnallisuus ( hae kaikki tapahtumat )
	List<Event> findAll();
	
	//get, yksi tapahtuma
	List<Event> findByEventID(long eventID);
	
	// delete-toiminnallisuus
	void deleteById(long eventID);

	
}