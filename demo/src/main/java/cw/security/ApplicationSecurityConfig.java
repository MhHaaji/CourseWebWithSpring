package cw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static cw.security.UserRoles.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final PasswordEncoder passwordEncoder;


    @Autowired
    private final UserDetailsService userDetailsService;
    @Value("#{passwordEncoder.encode('${haaji.admin-password}')}")
    private String adminPassword;
    @Value("${haaji.admin-username}")
    private String adminUsername;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole(ADMIN.name())
                .antMatchers("/staff/**").hasAnyRole(ADMIN.name(), STAFF.name())
                .antMatchers("/instructor/**").hasAnyRole(ADMIN.name(), INSTRUCTOR.name(), STAFF.name())
                .antMatchers("/student/**").hasAnyRole(ADMIN.name(), INSTRUCTOR.name(), STAFF.name(), STUDENT.name())
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .anyRequest()
                .authenticated()
                .and().httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .and().inMemoryAuthentication()
                .withUser(adminUsername)
                .password(adminPassword)
                .roles(ADMIN.name());
    }

}
