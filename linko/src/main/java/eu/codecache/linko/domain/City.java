package eu.codecache.linko.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


// täältä löytyy kunnat
@Entity
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	// Let's take a closer look to this annotation later,
	// for now I just comment it out as running the application fails because of it
//	@OneToMany
	private long cityID;
	
	// kuntanimi
	@NotNull
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public City(@NotNull String city) {
		super();
		this.city = city;
	}
	
	
}

