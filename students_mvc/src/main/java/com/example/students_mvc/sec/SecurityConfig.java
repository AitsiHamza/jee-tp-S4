package com.example.students_mvc.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();//.loginPage("/login");
        http.authorizeRequests()
                .antMatchers("/").permitAll();

        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasAuthority("ADMIN");
        http.authorizeRequests()
                .antMatchers("/user/**")
                .hasAuthority("USER");

        http.authorizeRequests()
                .antMatchers("/webjars/**")
                .permitAll();

        http.authorizeRequests().anyRequest().authenticated();

        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //PasswordEncoder passwordEncoder=passwordEncoder();
        /*
        String encodedPassword=passwordEncoder.encode("1111");
        System.out.println();
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password(encodedPassword)
                .roles("USER")
                .and()
                .withUser("admin")
                .password(encodedPassword)
                .roles("ADMIN","USER");
        auth.inMemoryAuthentication()
                .withUser("user2")
                .password(encodedPassword)
                .roles("USER");
         */
        /*
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
                .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);
         */
        auth.userDetailsService(userDetailsService);
    }

}
