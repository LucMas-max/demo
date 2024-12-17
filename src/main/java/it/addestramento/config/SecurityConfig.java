package it.addestramento.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;


@EnableWebSecurity
@Configuration
//@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private Logger log = LogManager.getLogger(SecurityConfig.class.getName());
	
	private static final String[] AUTH_WHITELIST = {
		"/", "/assets/**", "/libs/**", "/vendor/**", "/params", "/salva", "/cancella", "/registrazione", "/salvaUtente"
	};
	
	private static final String[] AUTH_USER = {
			 "/rubrica", "/admin/**"
		};
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info(">>> Check Spring Security <<<");
		http.anonymous().and()
		.csrf().disable().cors()
        .and()
			.authorizeRequests()
				.antMatchers(AUTH_WHITELIST).permitAll()
				.antMatchers(AUTH_USER).hasAuthority("USER") 
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.usernameParameter("email")
				.defaultSuccessUrl("/conti", true)
				.failureUrl("/login?error")
				.permitAll()
			.and()
				.logout().logoutSuccessUrl("/")
				.deleteCookies("JSESSIONID")
		           
				.and()
		          .exceptionHandling()
		          .accessDeniedPage("/403");
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
	    StrictHttpFirewall firewall = new StrictHttpFirewall();
	   // firewall.setAllowedHttpMethods(Arrays.asList("GET", "POST"));
        firewall.setAllowSemicolon(true);
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowBackSlash(true);
        firewall.setAllowUrlEncodedPercent(true);
        firewall.setAllowUrlEncodedPeriod(true);   
	    return firewall;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
	    SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
	    handler.setUseReferer(true);
	    return handler;
	}
	
}