package cw;

import cw.repositoryInterfaces.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDB {
    private static final Logger log = LoggerFactory.getLogger(LoadDB.class);

    @Bean
    CommandLineRunner initDB(UserRepo userRepo) {
        return args -> {
            userRepo.findAll().forEach(user -> {
                log.info("preloaded" + user);
            });
        };
    }
}
