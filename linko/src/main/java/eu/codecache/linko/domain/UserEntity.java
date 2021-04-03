package eu.codecache.linko.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	private String passwordHash;

	@ManyToOne
	@JoinColumn(name = "authID")
	@JsonIgnore
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

	public boolean setPassword(String password1, String password2) {
		// We can use this to check if the passwords are strong enough
		return this.hashPassword(password1, password2);
	}

	private boolean hashPassword(String pass1, String pass2) {
		// if passwords dont match
		if (!pass1.equals(pass2)) {
			return false;
		}
		// else
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.passwordHash = encoder.encode(pass1);
		return true;
	}

}
