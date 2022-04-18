package com.example.students_mvc.services;

import com.example.students_mvc.entities.Gender;
import com.example.students_mvc.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface StudentService {
    Student addNewStudent(Student student);
    Student addNewStudent(String firstname, String lastname, String email, Date birthday, Gender gender,boolean good);
    void removeStudent(Student student);
    Student updateStudent(Student student);
    List<Student> findAll();

}
