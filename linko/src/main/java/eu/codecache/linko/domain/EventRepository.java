package eu.codecache.linko.domain;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
	
	<S extends Event> S save(S event);
	
	void deleteById(Long EventID);
	

	
}