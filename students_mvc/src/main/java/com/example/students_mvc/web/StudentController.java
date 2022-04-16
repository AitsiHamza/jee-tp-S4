package com.example.students_mvc.web;

import com.example.students_mvc.entities.Student;
import com.example.students_mvc.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {
    private StudentRepository studentRepository;

    @GetMapping("/allStudents")
    public String allStudents(Model model,
                              @RequestParam(name = "keyword",defaultValue = "")String keyword){
        List<Student> students=studentRepository.findByFirstnameContains(keyword);
        model.addAttribute("students",students);
        return "students1";
    }

    @GetMapping("/students")
    public String students(Model model,
                           @RequestParam(name = "keyword",defaultValue = "")String keyword,
                           @RequestParam(name="size",defaultValue = "15")int size){
        Page<Student> students=studentRepository
                .findByFirstnameContains(keyword, Pageable.ofSize(size));
        model.addAttribute("students",students.getContent());
        model.addAttribute("keyword",keyword);
        model.addAttribute("pages",new int[students.getTotalPages()]);
        return "students";
    }

    @GetMapping("/students2")
    public String students2(Model model,
                            @RequestParam(name = "page",defaultValue = "0") int page,
                            @RequestParam(name = "size",defaultValue = "5") int size,
                            @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Student> students=studentRepository
                .findByFirstnameContains(keyword, PageRequest.of(page,size));
        model.addAttribute("students",students.getContent());
        model.addAttribute("pages",new int[students.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "students2";
    }

    @GetMapping("/")
    String home(){
        return "home";
    }

    @GetMapping(path = "/index")
    public List<Student> studentsJson(){
        return studentRepository.findAll();
    }

    @DeleteMapping(path = "/delete")
    public String delete(Long id,String keyword){
        studentRepository.deleteById(id);
        return "redirect:/students?keyword="+keyword;
    }
}
