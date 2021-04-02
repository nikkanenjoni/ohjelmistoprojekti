package eu.codecache.linko.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;

@Entity
public class UserAuthorization {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "userID", nullable = false, updatable = false)
	private long authID;

	// User authorization, user or admin
//	@Column(name = "authorization", nullable = false)
	private String authorization;
	
	public UserAuthorization() {
		
	}

	public long getAuthID() {
		return authID;
	}

	public void setAuthID(long authID) {
		this.authID = authID;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

}
