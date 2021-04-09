package eu.codecache.linko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import eu.codecache.linko.domain.UserEntity;
import eu.codecache.linko.domain.UserEntityRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserEntityRepository ueRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		UserEntity user = ueRepo.findByUsername(username);
		if (user == null) {
			// User not found
			throw new UsernameNotFoundException(username + " not found");
		}
		// ok, user was found
		UserDetails details = new User(user.getUsername(), user.getPasswordHash(),
				AuthorityUtils.createAuthorityList(user.getUserAuth().getAuthorization()));

		return details;
	}

}
