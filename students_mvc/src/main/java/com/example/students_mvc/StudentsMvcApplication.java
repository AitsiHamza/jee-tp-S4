package com.example.students_mvc;

import com.example.students_mvc.entities.Gender;
import com.example.students_mvc.entities.Student;
import com.example.students_mvc.repositories.StudentRepository;
import com.example.students_mvc.sec.service.SecurityService;
import com.example.students_mvc.services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;


@SpringBootApplication
public class StudentsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentsMvcApplication.class, args);
    }

    //@Bean
    CommandLineRunner start(StudentRepository studentRepository){
        return args->{
            Student student1=new Student(null,"hamza1","aitsi1","hamzaaitsi56@gmail.com", new Date(), Gender.MALE,true);
            studentRepository.save(student1);

            Student student2=new Student(null,"amine1","aitsi1","amineaitsi@gmail.com", new Date(), Gender.MALE,true);
            studentRepository.save(student2);

            Student student3=new Student(null,"meriem1","aitsi1","meriemaitsi@gmail.com", new Date(), Gender.FEMALE,true);
            studentRepository.save(student3);

            studentRepository.findAll().forEach(p->{
                System.out.println(p.getFirstname());
            });
        };
    }

    //@Bean
    CommandLineRunner start(StudentService studentService){
        return args->{
            for (int i = 0; i < 100; i++) {
                String email=i+"hamzaaitsi@gmail.com";
                studentService.addNewStudent("hamza1","aitsi",email, new Date(), Gender.MALE,true);
            }
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("hamza20","1111","1111");
            securityService.saveNewUser("mohammed20","1111","1111");
            securityService.saveNewRole("USER");
            securityService.saveNewRole("ADMIN");

            securityService.addRoleToUser("hamza20","ADMIN");
            securityService.addRoleToUser("hamza20","USER");
            securityService.addRoleToUser("mohammed20","USER");
        };
    }

}
