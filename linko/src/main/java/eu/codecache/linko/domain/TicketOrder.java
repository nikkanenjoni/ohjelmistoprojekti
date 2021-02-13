package eu.codecache.linko.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TicketOrder {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ticketOrderID;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "orderID")
	@NotNull
	private Order order;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "ticketID")
	@NotNull
	private Ticket ticket;

	@NotNull
	@DecimalMin("0.0")
	@DecimalMax("1000.0")
	private double price;

	public TicketOrder() {
		// empty default constructor
	}

	public TicketOrder(Order order, Ticket ticket, double price) {
		super();
		this.order = order;
		this.ticket = ticket;
		this.price = price;
	}

	public long getTicketOrderID() {
		return ticketOrderID;
	}

	public void setTicketOrderID(long ticketOrderID) {
		this.ticketOrderID = ticketOrderID;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
