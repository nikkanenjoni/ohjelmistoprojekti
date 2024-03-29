package eu.codecache.linko.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorizationRepository extends JpaRepository<UserAuthorization, Long> {
	
	UserAuthorization findByAuthID(long authID);
	
	UserAuthorization findByAuthorization(String authorization);

}
