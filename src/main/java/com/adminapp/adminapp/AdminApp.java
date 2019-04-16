package com.adminapp.adminapp;

import com.adminapp.adminapp.entity.User;
import com.adminapp.adminapp.repositories.UserRepository;
import com.adminapp.adminapp.storage.StorageProperties;
import com.adminapp.adminapp.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class AdminApp implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AdminApp.class, args);
	}


//	to-remove
    @Override
    public void run(String... args) throws Exception {
        if (this.userRepository.findByUsername("app-admin") == null) {
            User user = new User(1L, "app-admin", passwordEncoder.encode("app-admin"), "ROLE_ADMIN");

            this.userRepository.save(user);
        }
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
//            storageService.deleteAll();
            storageService.init();
        };
    }


}


