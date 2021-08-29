package cw;

import cw.entities.MyUser;
import cw.repositoryInterfaces.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoadDB {
    private static final Logger log = LoggerFactory.getLogger(LoadDB.class);

    @Bean
    CommandLineRunner initDB(UserRepo userRepo) {
        return args -> {
            if (!userRepo.existsByUsername("admin")) {
                userRepo.save(new MyUser("admin", getPasswordEncoder().encode("admin")));
            }
        };
    }


    private PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
