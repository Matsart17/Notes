package com.app.notes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotesConfiguration {
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("Command line runner...");
        };
    }

}
