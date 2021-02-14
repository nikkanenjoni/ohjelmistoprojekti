package eu.codecache.linko.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private long orderID;

	// I think this shouldn't be here but instead in the Ticket class
//	@ManyToOne
//	@JsonIgnore
//	@JoinColumn(name = "eventID")
//	@NotNull
//	private Ecent event;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	public Order() {
		// default constructor
	}

	public Order(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (orderID ^ (orderID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderID != other.orderID)
			return false;
		return true;
	}

}
