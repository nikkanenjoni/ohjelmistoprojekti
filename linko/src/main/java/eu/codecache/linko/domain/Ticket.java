package eu.codecache.linko.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long TicketID, TicketTypeID, EventID;
	private double price;
	private String description;
	
	public Ticket() {}
	
	public Ticket(Long TicketID, Long TicketTypeID, Long EventID, double price, String description) {
		this.TicketID = TicketID;
		this.TicketTypeID = TicketTypeID;
		this.EventID = EventID;
		this.price = price; 
		this.description = description;
	}
	

	@Override
	public String toString() {
		return "Ticket [TicketID=" + TicketID + ", TicketTypeID=" + TicketTypeID + ", EventID=" + EventID + ", price="
				+ price + ", description=" + description + "]";
	}
	
	
}
