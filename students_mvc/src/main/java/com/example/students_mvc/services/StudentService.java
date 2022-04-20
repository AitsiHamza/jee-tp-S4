package com.example.students_mvc.services;

import com.example.students_mvc.entities.Gender;
import com.example.students_mvc.entities.Student;
import com.example.students_mvc.exceptions.StudentNotFoundException;

import java.util.Date;
import java.util.List;

public interface StudentService {
    Student addNewStudent(Student student);
    Student addNewStudent(String firstname, String lastname, String email, Date birthday, Gender gender,boolean good);
    Student updateStudent(Student student) throws StudentNotFoundException;
    List<Student> findAll();
    void removeStudent(Student student) throws StudentNotFoundException;
    void removeStudent(String idStudent) throws StudentNotFoundException;
    Student getCountStudent(String idStudent) throws StudentNotFoundException;
}
