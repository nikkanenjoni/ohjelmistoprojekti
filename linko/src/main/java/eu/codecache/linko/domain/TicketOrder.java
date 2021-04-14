package eu.codecache.linko.domain;

import java.time.LocalDateTime;
import java.util.Random;

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
	private Orders order;

	@ManyToOne
	@JoinColumn(name = "ticketID")
	@NotNull
	private Ticket ticket;

	@NotNull
	@DecimalMin("0.0")
	@DecimalMax("1000.0")
	private double price;

	@JsonIgnore
	private char character;
	private String code;

	@JsonIgnore
	private boolean used;
	@JsonIgnore
	private LocalDateTime usedDate;

	public TicketOrder() {
		// empty default constructor
		super();
	}

	public TicketOrder(Orders order, Ticket ticket, double price) {
		this();
		this.order = order;
		this.ticket = ticket;
		this.price = price;
		this.used = false;
		this.character = '-';
		this.code = "default";
	}
	
	public void generateCode() {
		// this is just to name the method better
		this.setCharacter();
	}

	public void setCharacter() {
		if (code.equals("default") || this.character=='-') {
			Random rand = new Random();
			String characters = "abcdefghijklmnopqrstuvwxyz";
			char c = characters.charAt(rand.nextInt(characters.length()));
			this.character = c;
			this.code = "" + ticketOrderID + c + order.getOrderID() + c + ticket.getTicketID();
		}
	}

	public long getTicketOrderID() {
		return ticketOrderID;
	}

	public void setTicketOrderID(long ticketOrderID) {
		this.ticketOrderID = ticketOrderID;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
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

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(LocalDateTime usedDate) {
		this.usedDate = usedDate;
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

}
