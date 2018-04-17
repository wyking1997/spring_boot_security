package org.marius.training.mhp.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/api/users/login").permitAll()    // Permit access for all to login REST service
                .antMatchers("/").permitAll()                   // Neccessary to permit access to default document
                .antMatchers("/food/{id}").authenticated()
                .antMatchers("/food/all").authenticated()
                .antMatchers("/food/update").hasRole("ADMIN")
                .antMatchers("/food/increase/{foodId}").hasRole("ADMIN")
                .antMatchers("/food/decrease/{foodId}").hasRole("ADMIN")
                .antMatchers("/food/delete/{id}").hasRole("ADMIN")
                .anyRequest().authenticated().and()                        // All other requests require authentication
                .httpBasic().and()
                .logout().and()
                .csrf().disable();
    }
}
