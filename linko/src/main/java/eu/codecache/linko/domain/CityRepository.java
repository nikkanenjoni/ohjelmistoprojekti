package eu.codecache.linko.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends JpaRepository<City, Long> {

	// We need to be able to get a single city out from the database
	List<City> findCityByCity(String city);
}