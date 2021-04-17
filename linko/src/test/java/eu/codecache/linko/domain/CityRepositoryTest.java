package eu.codecache.linko.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CityRepositoryTest {

	@Autowired
	private CityRepository cRepo;

	private String[] cities = { "Mock City 1", "Mock City 2" };

	/*
	 * Test finding city by name
	 */
	@Test
	public void findCityByNameTest() throws Exception {
		List<City> city = cRepo.findCityByCity(cities[0]);
		assertEquals(city.size(), 1);
		assertTrue(city.get(0).getCity().equals(cities[0]));
		assertFalse(city.get(0).getCity().equals(cities[1]));
	}

	/*
	 * Test finding all cities
	 */
	@Test
	public void findAllCitiesTest() throws Exception {
		// I don't know why this method is manually added to the repository
		// Doesn't JpaRepository have this by default?
		List<City> allCities = cRepo.findAll();
		assertEquals(allCities.size(), 2);
		assertTrue(allCities.get(0).getCity().equals(cities[0]) || allCities.get(0).getCity().equals(cities[1]));
	}

	@BeforeEach
	private void addCitiesToDB() throws Exception {
		cRepo.save(new City(cities[0]));
		cRepo.save(new City(cities[1]));
	}
}
