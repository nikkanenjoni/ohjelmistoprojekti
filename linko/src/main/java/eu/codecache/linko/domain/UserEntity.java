package eu.codecache.linko.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "userID", nullable = false, updatable = false)
	private long userID;

// Username with unique constraint (if needed)
//	@Column(name = "username", nullable = false, unique = true)
	private String username;

//	@Column(name = "password", nullable = false)
	private String passwordHash;

	@ManyToOne
	@JoinColumn(name = "authID")
	private UserAuthorization userAuth;

	public UserEntity() {

	}

	public UserEntity(String username, UserAuthorization auth, String passwordHash) {
		this.username = username;
		this.userAuth = auth;
		this.passwordHash = passwordHash;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public UserAuthorization getUserAuth() {
		return userAuth;
	}

	public void setUserAuth(UserAuthorization userAuth) {
		this.userAuth = userAuth;
	}

}
