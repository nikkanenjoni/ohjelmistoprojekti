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

}
