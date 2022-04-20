package com.example.students_mvc.services;

import com.example.students_mvc.entities.Gender;
import com.example.students_mvc.entities.Student;
import com.example.students_mvc.exceptions.StudentNotFoundException;
import com.example.students_mvc.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    public void removeStudent(Student student){
        studentRepository.delete(student);
    }
    @Override
    public void removeStudent(String idStudent) throws StudentNotFoundException {
        Long count =studentRepository.countByIdStudent(idStudent);
        if(count==null || count ==0){
            throw new StudentNotFoundException("User id = "+idStudent+" Couldn't be Found!");
        }
        studentRepository.deleteById(idStudent);
    }

    @Override
    public Student updateStudent(Student student) {
        System.out.println(student.getIdStudent());
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getCountStudent(String idStudent) throws StudentNotFoundException {
        Optional<Student> result= studentRepository.findById(idStudent);
        if(result.isPresent()){
            return result.get();
        }
        throw new StudentNotFoundException("Could not find any student with ID "+idStudent);
    }
}
