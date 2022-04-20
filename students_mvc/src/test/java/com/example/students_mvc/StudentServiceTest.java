package com.example.students_mvc;

import com.example.students_mvc.entities.Student;
import com.example.students_mvc.services.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void testAddNewStudentByStudentService(){
        Student student=new Student();
        String random= String.valueOf(Math.round(Math.random()*10000));
        String randomEmail=random+"111@gmail.com";

        student.setIdStudent(UUID.randomUUID().toString());
        student.setEmail(randomEmail);
        student.setFirstname("hamza"+random);
        student.setLastname("aitsi"+random);

        System.out.println(student);

        Student savedStudent1=studentService.addNewStudent(student.getFirstname(),
                student.getLastname(),student.getEmail(),student.getBirthday(),
                student.getGender(),student.isGood());

        //student.setIdStudent(Math.round(Math.random()*10000)+"222@gmail.com");

        //Student savedStudent2=studentService.addNewStudent(student);

        Assertions.assertThat(savedStudent1).isNotNull();
        //Assertions.assertThat(savedStudent2).isNotNull();
    }
}
