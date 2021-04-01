package eu.codecache.linko.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserEntity {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "userID", nullable = false, updatable = false)
 private Long userID;
 
 
// Username with unique constraint (if needed)
 @Column(name = "username", nullable = false, unique = true)
 private String username;
 
 
 @Column(name = "password", nullable = false)
 private String passwordHash;

}
