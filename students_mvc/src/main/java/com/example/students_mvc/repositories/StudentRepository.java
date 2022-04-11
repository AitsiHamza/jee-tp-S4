package com.example.students_mvc.repositories;

import com.example.students_mvc.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
}
