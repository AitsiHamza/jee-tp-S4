package com.example.students_mvc;

import com.example.students_mvc.entities.Gender;
import com.example.students_mvc.entities.Student;
import com.example.students_mvc.repositories.StudentRepository;
import com.example.students_mvc.services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

    @Bean
    CommandLineRunner start(StudentService studentService){
        return args->{
            Student student1=new Student(null,"hamza1","aitsi1","hamzaaitsi56@gmail.com", new Date(), Gender.MALE,true);
            studentService.addNewStudent(student1);

            Student student2=new Student(null,"amine1","aitsi1","amineaitsi@gmail.com", new Date(), Gender.MALE,true);
            studentService.addNewStudent(student2);

            Student student3=new Student(null,"meriem1","aitsi1","meriemaitsi@gmail.com", new Date(), Gender.FEMALE,true);
            studentService.addNewStudent(student3);


        };
    }

}
