package eu.codecache.linko.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
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
public class EventRepositoryTest {

	@Autowired
	private EventRepository eRepo;

	@Autowired
	private CityRepository cRepo;

	private String[] cities = { "Mock City 1", "Mock City 2" };

	/*
	 * Test fetching all events
	 */
	@Test
	public void findAllEvents() throws Exception {
		List<Event> events = eRepo.findAll();
		assertEquals(events.size(), 2);
	}

	/*
	 * Test finding by id
	 */
	@Test
	public void findEventById() throws Exception {
		Event e1 = eRepo
				.save(new Event("Test Event", cRepo.findAll().get(0), "Mockstreet", 50, "", LocalDateTime.now()));
		Event e2 = eRepo.findByEventID(e1.getEventID());
		assertFalse(e2 == null);
		assertTrue(e2.getEvent().equals("Test Event"));
	}

	/*
	 * Test delete by id
	 */
	@Test
	public void deleteEventById() throws Exception {
		Event e1 = eRepo
				.save(new Event("Test Event", cRepo.findAll().get(0), "Mockstreet", 50, "", LocalDateTime.now()));
		long e1Id = e1.getEventID();
		e1 = null;
		// Now we know the id of stored event, but we don't have the event stored
		// referred in any variable
		assertFalse(eRepo.findByEventID(e1Id) == null);
		eRepo.deleteById(e1Id);
		assertTrue(eRepo.findByEventID(e1Id) == null);
	}

	/*
	 * Add some test data to database
	 */
	@BeforeEach
	private void addData() {
		// Add cities to repository if not exists
		for (String city : cities) {
			if (cRepo.findCityByCity(city).size() < 1) {
				cRepo.save(new City(city));
			}
		}
		eRepo.deleteAll();
		eRepo.save(new Event("Event1", cRepo.findCityByCity(cities[0]).get(0), "Address 1", 1000, "Description",
				LocalDateTime.now()));
		eRepo.save(
				new Event("Event2", cRepo.findCityByCity(cities[1]).get(0), "Address 2", 100, "", LocalDateTime.now()));
	}

}
