package com.example.students_mvc.services;

import com.example.students_mvc.entities.Student;

import java.util.List;

public interface StudentService {
    Student findStudentByLastname(String lastname);
    Student addNewStudent(Student student);
    void removeStudent(Student student);
    Student updateStudent(Student student);
    List<Student> findStudentsByFirstnameOrLastname(String firstname,String lastname);

}
