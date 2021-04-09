package eu.codecache.linko.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class Ticket {
	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ticketID;
	
	@ManyToOne
	@JoinColumn(name = "ticketTypeID")
	@NotNull
	private TicketType ticketType;
	
	@ManyToOne
	@JoinColumn(name = "eventID")
	@NotNull
	private Event event;

	@NotNull
	@DecimalMin("0.0")
	@DecimalMax("1000.0")
	private double price;
	
	private String description;
	
	
	public Ticket() {
		// empty default constructor
	}
	
	public Ticket(TicketType ticketType, Event event, double price, String description) {
		super();
		this.ticketType = ticketType;
		this.event = event;
		this.price = price; 
		this.description = description;
	}

	public Long getTicketID() {
		return ticketID;
	}

	public void setTicketID(Long ticketID) {
		this.ticketID = ticketID;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}