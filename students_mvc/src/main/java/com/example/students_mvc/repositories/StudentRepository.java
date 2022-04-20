package com.example.students_mvc.repositories;

import com.example.students_mvc.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
    Page<Student> findByFirstnameContainsOrLastnameContainsOrEmailContains(String firstname,String lastname,String email, Pageable pageable);
    Long countByIdStudent(String idStudent);
}
