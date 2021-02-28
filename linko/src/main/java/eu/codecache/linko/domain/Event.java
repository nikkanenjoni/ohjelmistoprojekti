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

import com.fasterxml.jackson.annotation.JsonIgnore;

// tapahtuman tiedot (paikka, aika, nimi, kapasiteetti)
@Entity
public class Event {

	// Application won't run with this annotation, let's solve it later
//	@OneToMany
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private long eventID;

	@NotNull
	private String event;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cityID")
	// @NotNull
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

	public void setEventID(long eventID) {
		this.eventID = eventID;
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
