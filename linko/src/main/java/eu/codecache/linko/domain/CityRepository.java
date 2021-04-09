package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import eu.codecache.linko.exception.EventNotFoundException;

public interface CityRepository extends JpaRepository<City, Long> {

	// We need to be able to get a single city out from the database (Viittaa Application-sivuun)
	List<City> findCityByCity(String city);
	
	// Find all the cities
	List<City> findAll();
	// Viittaa EventControlleriin, POST event
	//List<City> findByCityID(long cityID); //throws CitytNotFoundException;

	
}