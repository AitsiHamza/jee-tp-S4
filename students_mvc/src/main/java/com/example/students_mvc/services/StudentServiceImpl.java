package com.example.students_mvc.services;

import com.example.students_mvc.entities.Student;
import com.example.students_mvc.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;

    @Override
    public Student addNewStudent(Student student) {
        //student.setIdStudent(UUID.randomUUID().toString());
        return studentRepository.save(student);
    }

    @Override
    public void removeStudent(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
