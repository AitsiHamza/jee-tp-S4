package com.example.students_mvc.web;

import com.example.students_mvc.entities.Student;
import com.example.students_mvc.exceptions.StudentNotFoundException;
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

    @GetMapping("/user/students")
    public String students(Model model,
                            @RequestParam(name = "page",defaultValue = "0") int page,
                            @RequestParam(name = "size",defaultValue = "5") int size,
                            @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Student> students=studentRepository
                .findByFirstnameContainsOrLastnameContainsOrEmailContains(keyword,keyword,keyword, PageRequest.of(page,size));
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

    @GetMapping(path = "/admin/allStudents")
    @ResponseBody
    public List<Student> studentsJson(){
        return studentService.findAll();
    }

    @GetMapping(path = "/admin/delete")
    public String delete(String id,String keyword,int page, Model model){
        try {
            studentService.removeStudent(id);
            model.addAttribute("message","The Student("+id+") has been deleted!");
            model.addAttribute("style","alert-success");
        }catch(StudentNotFoundException e){
            model.addAttribute("message","The user hasn't been saved!");
            model.addAttribute("style","alert-danger");
        }
        return "redirect:/user/students?keyword="+keyword+"&page="+page;
    }

    @GetMapping(path = "/admin/newStudent")
    public String newStudent(Model model){
        model.addAttribute("student",new Student());
        model.addAttribute("pageTitle","Add new Student");
        return "saveStudent";
    }

    @PostMapping(path = "/admin/saveStudent")
    public String saveStudent(Model model, @Valid Student student,
                         BindingResult bindingResult,
                         @RequestParam(defaultValue = "")String keyword,
                         @RequestParam(defaultValue = "0")int page){
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        if(bindingResult.hasErrors()){
            model.addAttribute("message","This student ("+student.getIdStudent()+") wasn't updated correctly!");
            model.addAttribute("style","alert-danger");
            return "saveStudent";
        }
        studentService.updateStudent(student);
        model.addAttribute("message","This student ("+student.getIdStudent()+") was updated correctly!");
        model.addAttribute("style","alert-success");
        //return "/admin/saveStudent";
        return "saveStudent";
    }

    @GetMapping(path="/admin/updateStudent")
    public String updateStudent(Model model,String id,String keyword,int page){
        try {
            Student student = studentService.getCountStudent(id);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
            model.addAttribute("student", student);
            model.addAttribute("style", "alert-success");
            model.addAttribute("message", "Fortunately the student ("+id+") was found!");
            model.addAttribute("pageTitle", "Update Student");
            return "saveStudent";
        }catch(StudentNotFoundException e){
            model.addAttribute("page", page);
            model.addAttribute("keyword", keyword);
            model.addAttribute("message","Unfortunately the student wasn't found!");
            model.addAttribute("style", "alert-danger");
            return "redirect:/user/students?keyword="+keyword+"&page="+page;
        }
    }
}
