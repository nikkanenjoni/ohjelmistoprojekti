package eu.codecache.linko.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

// tapahtuman tiedot (paikka, aika, nimi, kapasiteetti)
@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@OneToMany
	private long eventID;
	
	@NotNull
	private String event;
	
	@ManyToOne
	@JoinColumn(name = "CityID")
	@NotNull
	private City city;
	
	
	@NotNull
	private String eventPlace;
	
	@NotNull
	private int capacity;
	
	private String description;
	
	@NotNull
	private LocalDateTime dateTime;

	public Event() {
		// default constructor
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getEventPlace() {
		return eventPlace;
	}

	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
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
		return dateTime;
	}

	public void setDatetime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	
	// constructor
	public Event(@NotNull String event, @NotNull City city, @NotNull String eventPlace, @NotNull int capacity,
			String description, @NotNull LocalDateTime dateTime) {
		super();
		this.event = event;
		this.city = city;
		this.eventPlace = eventPlace;
		this.capacity = capacity;
		this.description = description;
		this.dateTime = dateTime;
	}

	



}
