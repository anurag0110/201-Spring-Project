package com.mindtree.cms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Anurag Srivastava
 *
 */
@Configuration
@EnableWebSecurity
@ComponentScan({ "com.mindtree.cms" })
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment environment;

	/**
	 * @param authenticationMgr
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr)
			throws Exception {
		authenticationMgr.inMemoryAuthentication()
				.withUser(environment.getRequiredProperty("admin.user_name"))
				.password(environment.getRequiredProperty("admin.password"))
				.authorities("ROLE_ADMIN");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter
	 * #configure(org.springframework.security.config
	 * .annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/secure/**")
				.access("hasRole('ROLE_ADMIN')")
				.and()
				.formLogin()
				. // login
				loginPage("/customLogin").loginProcessingUrl("/appLogin")
				.usernameParameter("app_username")
				.passwordParameter("app_password")
				.defaultSuccessUrl("/secure/home").permitAll().and().logout()
				. // logout
				logoutUrl("/appLogout").logoutSuccessUrl("/customLogin")
				.permitAll().and().csrf().disable();

	}

}
