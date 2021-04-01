package eu.codecache.linko.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

	
	@Entity
	public class UserAuthorization {
	@OneToOne
	@JoinColumn(name = "userID")
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "userID", nullable = false, updatable = false)
	 private Long userID;
	 
	 
	// User authorization, user or admin
	 @Column(name = "authorization", nullable = false)
	 private String authorization;
	 
	 

}
