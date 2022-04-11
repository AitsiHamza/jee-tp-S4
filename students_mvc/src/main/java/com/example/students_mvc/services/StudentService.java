package com.example.students_mvc.services;

import com.example.students_mvc.entities.Student;

public interface StudentService {
    Student findStudentByLastname(Student student);
    Student addNewStudent(Student student);
    Student removeStudent(Student student);
    Student updateStudent(Student student);
}
