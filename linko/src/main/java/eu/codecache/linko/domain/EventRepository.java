package eu.codecache.linko.domain;

import java.util.List;
import java.util.Optional;


import org.springframework.data.repository.CrudRepository;


public interface EventRepository extends CrudRepository<Event, Long> {
	
<<<<<<< HEAD
	<S extends Event> S save(S event);
	
	void deleteById(Long EventID);
	
=======
	// get-toiminnallisuus ( hae kaikki tapahtumat )
	List<Event> findAll();
	
	//get, yksi tapahtuma
	Optional<Event> findById(Long eventID);
	
	// delete-toiminnallisuus
	void deleteById(Long eventID);
>>>>>>> 3ad69f86818069f808dc2a61f872a53513cf8b01

	
}