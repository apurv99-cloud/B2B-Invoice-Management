package com.example.demo.Config;

import com.example.demo.Models.User;
import com.example.demo.Models.UserRole;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdReSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if(userRepository.findByEmail("admin@gmail.com").isEmpty()) {

            User admin = User.builder()
                    .fullName("Super Admin")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(UserRole.ADMIN)
                    .active(true)
                    .build();

            userRepository.save(admin);
        }
        if (userRepository.findByEmail("admin2@gmail.com").isEmpty()) {

            User admin2 = User.builder()
                    .fullName("Admin Two")
                    .email("admin2@gmail.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(UserRole.ADMIN)
                    .active(true)
                    .build();

            userRepository.save(admin2);
        }

        if (userRepository.findByEmail("admin3@gmail.com").isEmpty()) {

            User admin3 = User.builder()
                    .fullName("Admin Three")
                    .email("admin3@gmail.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(UserRole.ADMIN)
                    .active(true)
                    .build();

            userRepository.save(admin3);
        }
        if (userRepository.findByEmail("reviewer@gmail.com").isEmpty()) {

            User reviewer = User.builder()
                    .fullName("Finance Reviewer")
                    .email("reviewer@gmail.com")
                    .password(passwordEncoder.encode("review123"))
                    .role(UserRole.REVIEWER)
                    .active(true)
                    .build();

            userRepository.save(reviewer);
        }
    }
}
