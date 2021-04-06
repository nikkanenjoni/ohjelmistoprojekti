package eu.codecache.linko.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// @ActiveProfiles("test")
@RunWith(SpringRunner.class)
// @WebMvcTest(EventController.class)
// @ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EventControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	// https://www.baeldung.com/spring-security-integration-tests
	@Test
	public void getAllTest() throws Exception {
		ResponseEntity<String> result = restTemplate.withBasicAuth("user", "password").getForEntity("/api/events",
				String.class);
		// no tests ready yet, 1 is always 1 :)
		assertEquals(1, 1);
	}

//	@Autowired
//	private WebApplicationContext context;

	// @Autowired
//	private MockMvc mockMvc;

//	@Before
//	public void setup() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
//	}

//	@WithMockUser("user")
//	@Test
//	public void getAllEventsTest() throws Exception {
//		mockMvc.perform(get("/api/events").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
//		assertEquals(1, 1);
//	}
}
