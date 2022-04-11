package com.example.students_mvc.services;

import com.example.students_mvc.entities.Student;

public interface StudentService {
    Student findStudentByLastname(String lastname);
    Student addNewStudent(Student student);
    void removeStudent(Student student);
    Student updateStudent(Student student);
}
