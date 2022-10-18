package za.ac.cput.security;

import org.apache.catalina.Manager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String USER_ROLE = "USER";
    private static final String ADMIN_ROLE = "ADMIN";


    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("userA")
                .password(encoder().encode("user"))
                .roles("USER")
                .and()
                .withUser("client")
                .password(encoder().encode("admin"))
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/save").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/delete").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/read").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/find-all").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/read").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/delete").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/update").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/save").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/find-all").hasRole("USER")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}

