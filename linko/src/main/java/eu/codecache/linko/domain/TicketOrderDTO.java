package eu.codecache.linko.domain;

public class TicketOrderDTO {

	private long ticketID;
	private double ticketPrice;

	public TicketOrderDTO() {

	}

	public long getTicketID() {
		return ticketID;
	}

	public void setTicketID(long ticketID) {
		this.ticketID = ticketID;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String toString() {
		return "{\"ticketID\": " + ticketID + ", \"ticketPrice\": " + ticketPrice + "}";
	}
}
