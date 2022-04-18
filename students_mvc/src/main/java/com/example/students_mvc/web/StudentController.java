package com.example.students_mvc.web;

import com.example.students_mvc.entities.Gender;
import com.example.students_mvc.entities.Student;
import com.example.students_mvc.repositories.StudentRepository;
import com.example.students_mvc.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public String students(Model model,
                            @RequestParam(name = "page",defaultValue = "0") int page,
                            @RequestParam(name = "size",defaultValue = "5") int size,
                            @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Student> students=studentRepository
                .findByFirstnameContainsOrLastnameContains(keyword,keyword, PageRequest.of(page,size));
        model.addAttribute("students",students.getContent());
        model.addAttribute("pages",new int[students.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "students";
    }

    @GetMapping("/")
    String home(){
        return "home";
    }

    @GetMapping(path = "/allStudents")
    @ResponseBody
    public List<Student> studentsJson(){
        return studentService.findAll();
    }

    @GetMapping(path = "/delete")
    public String delete(Long id,String keyword,int page){
        studentRepository.deleteById(id);
        //studentService.removeStudent(student);
        return "redirect:/students?keyword="+keyword+"&page="+page;
    }

    @GetMapping(path = "/newStudent")
    public String newStudent(Model model){
        model.addAttribute("student",new Student());
        return "saveStudent";
    }

    @PostMapping(path = "/saveStudent")
    public String saveStudent(Model model, @Valid Student student,
                         BindingResult bindingResult,
                         @RequestParam(defaultValue = "")String keyword,
                         @RequestParam(defaultValue = "0")int page){
        if(bindingResult.hasErrors()){
            return "saveStudent";
        }
        //studentService.updateStudent(student);
        studentRepository.save(student);
        return "redirect:/students?keyword="+keyword+"&page="+page;
    }

    @GetMapping(path="/updateStudent")
    public String updateStudent(Model model,Long id,String keyword,int page){
        Student student=studentRepository.findById(id).orElse(null);
        if (student==null)throw new RuntimeException("Student doesn't exist!!");
        model.addAttribute("student",student);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "saveStudent";
    }
}
