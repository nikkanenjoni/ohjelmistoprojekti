package eu.codecache.linko.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class TicketType {
	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ticketTypeID;
	
	private String ticketType;
	
	public TicketType() {
		// empty default constructor
	}
	
	public TicketType(String ticketType) {
		super();
		this.ticketType = ticketType;
	}

	public Long getTicketTypeID() {
		return ticketTypeID;
	}

	public void setTicketTypeID(Long ticketTypeID) {
		this.ticketTypeID = ticketTypeID;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	
	

}
