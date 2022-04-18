package com.example.students_mvc.repositories;

import com.example.students_mvc.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Page<Student> findByFirstnameContainsOrLastnameContains(String firstname,String lastname, Pageable pageable);
}
