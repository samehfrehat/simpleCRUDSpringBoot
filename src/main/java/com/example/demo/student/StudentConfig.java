package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            var sameh = new Student(
              "Sameh",
              "samehfraihat@gmail.com",
                    LocalDate.of(1994, Month.NOVEMBER,19)
            );

            var ali = new Student(
                    "Ali",
                    "ali@gmail.com",
                    LocalDate.of(1996, Month.APRIL,19)
            );

            studentRepository.saveAll(List.of(sameh,ali));
        };
    }
}
