package com.example.students_mvc;

import com.example.students_mvc.entities.Student;
import com.example.students_mvc.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
//@AllArgsConstructor
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    /*@AllArgsConstructor or a constructor can't replace @Autowired*/
    /*
    public StudentRepositoryTest(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }*/

    public Student saveAndGetNewStudent(){
        Student student=new Student();
        String random= String.valueOf(Math.round(Math.random()*10000));
        String randomEmail=random+"azd@gmail.com";
        student.setIdStudent(UUID.randomUUID().toString());
        student.setEmail(randomEmail);
        student.setFirstname("hamza"+random);
        student.setLastname("aitsi"+random);

        studentRepository.save(student);
        return student;
    }

    @Test
    public void testSaveStudent(){
        Student student=saveAndGetNewStudent();

        System.out.println(student);

        Student savedStudent=studentRepository.save(student);

        Assertions.assertThat(savedStudent).isNotNull();
    }

    @Test
    public void testDeleteStudent(){
        Student student=saveAndGetNewStudent();
        String idStudent=student.getIdStudent();

        Assertions.assertThat(student).isNotNull();

        studentRepository.delete(student);

        Long count =studentRepository.countByIdStudent(idStudent);

        org.junit.jupiter.api.Assertions.assertTrue(count == null || count == 0);
    }

    @Test
    public void test_update(){
        Student student=saveAndGetNewStudent();
        //student.setIdStudent(UUID.randomUUID().toString());
        studentRepository.save(student);
    }

    @Test
    public void test_findByFirstnameContainsOrLastnameContainsOrEmailContains(){
        /*case when a student isn't stored exist in database*/
        /*a user's name or email can't contain ','*/
        Page<Student> studentPage=studentRepository.findByFirstnameContainsOrLastnameContainsOrEmailContains(",",",",",", PageRequest.of(0,1));
        long totalElements=studentPage.getTotalElements();
        org.junit.jupiter.api.Assertions.assertEquals(0, totalElements);

        /*case when there at least a student stored in database*/
        Student student=saveAndGetNewStudent();
        Page<Student> studentPage1=studentRepository.findByFirstnameContainsOrLastnameContainsOrEmailContains(student.getFirstname(),student.getLastname(),student.getEmail(),PageRequest.of(0,1));
        totalElements=studentPage1.getTotalElements();
        org.junit.jupiter.api.Assertions.assertEquals(1,totalElements);
    }

    @Test
    public void test_countByIdStudent(){
        /*in case when a student isn't stored in database*/
        Long count = studentRepository.countByIdStudent("0");
        org.junit.jupiter.api.Assertions.assertTrue(count==0 || count == null);

        /*in case when there is at least one student*/
        count=studentRepository.countByIdStudent(saveAndGetNewStudent().getIdStudent());
        org.junit.jupiter.api.Assertions.assertTrue(count!=0 && count!=null);
    }

    @Test
    public void test_findById(){
        /*case when a student isn't stored in database*/
        Optional<Student> student=studentRepository.findById("0");
        org.junit.jupiter.api.Assertions.assertFalse(student.isPresent());

        /*case when at least a student is in database*/
        student=studentRepository.findById(saveAndGetNewStudent().getIdStudent());
        org.junit.jupiter.api.Assertions.assertTrue(student.isPresent());
    }

    @Test
    public void test_findAll(){
        Iterable<Student> students=studentRepository.findAll();
        Assertions.assertThat(students).hasSizeGreaterThan(0);

        for (Student s:students) {
            System.out.println(s);
        }

        Student student=saveAndGetNewStudent();
        students=studentRepository.findAll();

        Assertions.assertThat(students).contains(student);
    }
}
