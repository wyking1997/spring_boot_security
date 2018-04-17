package org.marius.training.mhp.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()                   // Neccessary to permit access to default document
                .antMatchers(HttpMethod.POST, "/login").permitAll()

//                .antMatchers("/food/{id}").authenticated()
//                .antMatchers("/food/all").authenticated()
//                .antMatchers("/food/update").hasRole("ADMIN")
//                .antMatchers("/food/update").hasAnyRole("","","")
//                .antMatchers("/food/increase/{foodId}").hasRole("ADMIN")
//                .antMatchers("/food/decrease/{foodId}").hasRole("ADMIN")
//                .antMatchers("/food/delete/{id}").hasRole("ADMIN")
                .anyRequest().authenticated().and()
                .addFilterBefore(new JwtLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }
}
