package com.example.students_mvc.services;

import com.example.students_mvc.entities.Gender;
import com.example.students_mvc.entities.Student;
import com.example.students_mvc.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;

    @Override
    public Student addNewStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student addNewStudent(String firstname, String lastname, String email, Date birthday, Gender gender, boolean good) {
        Student student=new Student();
        student.setIdStudent(UUID.randomUUID().toString());
        student.setFirstname(firstname);
        student.setLastname(lastname);
        student.setEmail(email);
        student.setGender(gender);
        return studentRepository.save(student);
    }

    @Override
    public void removeStudent(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Student updateStudent(Student student) {
        student.setIdStudent(UUID.randomUUID().toString());
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
