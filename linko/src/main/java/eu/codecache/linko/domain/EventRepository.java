package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.codecache.linko.exception.EventNotFoundException;

public interface EventRepository extends JpaRepository<Event, Long> {

	// get-toiminnallisuus ( hae kaikki tapahtumat )
	List<Event> findAll();

	// get, yksi tapahtuma
	// this is not working as intented, if eventID is not found,
	// it return empty event instead of throwing EventNotFoundException
	// ... I wonder if this could be fixed somehow?
	Event findByEventID(long eventID) throws Exception;

	// delete-toiminnallisuus
	void deleteById(long eventID) throws Exception;

}