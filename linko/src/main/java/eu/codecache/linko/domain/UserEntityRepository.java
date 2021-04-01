package eu.codecache.linko.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, String> {
	
	UserEntity findByUsername(String username);

}
