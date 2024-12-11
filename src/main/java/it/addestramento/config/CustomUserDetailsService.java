package it.addestramento.config;

import java.time.LocalDate;
import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.addestramento.model.User;
import it.addestramento.repository.UserRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	private static Logger log = LogManager.getLogger(CustomUserDetailsService.class.getName());
	
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName){
    	
    	log.info("Utente sta tentando di loggarsi con questa email >>>>> "+ userName);
    	User user = userRepository.getUserByEmail(userName);
    	
    	if(user == null)
    	{
    		log.info("Email "+ userName + " non trovata");
    		throw new UsernameNotFoundException("Email " + userName + " non trovata");
    	}
    	
    	
    	
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getNome()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}