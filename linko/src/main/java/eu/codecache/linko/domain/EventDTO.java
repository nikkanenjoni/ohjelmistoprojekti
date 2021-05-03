package eu.codecache.linko.domain;

import java.time.LocalDateTime;
import java.util.List;

/*
 * https://asbnotebook.com/2019/10/29/customizing-restful-web-service-json-response-spring-boot/
 */
public class EventDTO {

	private long eventID;
	private String event;
	private String eventPlace;
	private int soldTickets;
	private int capacity;
	private String description;
	private LocalDateTime datetime;
	private List<Ticket> tickets;

	public EventDTO() {

	}

	public EventDTO(Event event, int soldTickets, List<Ticket> tickets) {
		this.eventID = event.getEventID();
		this.event = event.getEvent();
		this.eventPlace = event.getEventPlace();
		this.soldTickets = soldTickets;
		this.capacity = event.getCapacity();
		this.description = event.getDescription();
		this.datetime = event.getDatetime();
		this.tickets = tickets;
	}

	public EventDTO(long eventID, String event, String eventPlace, int soldTickets, int capacity, String description,
			LocalDateTime datetime, List<Ticket> tickets) {
//		super();
		this.eventID = eventID;
		this.event = event;
		this.eventPlace = eventPlace;
		this.soldTickets = soldTickets;
		this.capacity = capacity;
		this.description = description;
		this.datetime = datetime;
		this.tickets = tickets;
	}

	public long getEventID() {
		return eventID;
	}

	public void setEventID(long eventID) {
		this.eventID = eventID;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventPlace() {
		return eventPlace;
	}

	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}

	public void setSoldTickets(int soldTickets) {
		this.soldTickets = soldTickets;
	}

	public int getSoldTickets() {
		return soldTickets;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

}
