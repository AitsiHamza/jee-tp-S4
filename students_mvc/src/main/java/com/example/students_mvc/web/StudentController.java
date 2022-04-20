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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                            @RequestParam(name = "keyword",defaultValue = "") String keyword,
                           RedirectAttributes redirectAttributes){
        Page<Student> students=studentRepository
                .findByFirstnameContainsOrLastnameContainsOrEmailContains(keyword,keyword,keyword, PageRequest.of(page,size));
        model.addAttribute("students",students.getContent());
        model.addAttribute("pages",new int[students.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        redirectAttributes.addFlashAttribute("message","Those are all the students");
        redirectAttributes.addFlashAttribute("style","alert-success");
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
    public String delete(String id,String keyword,int page, Model model,RedirectAttributes redirectAttributes){
        try {
            studentService.removeStudent(id);
            redirectAttributes.addFlashAttribute("message","The Student("+id+") has been deleted!");
            redirectAttributes.addFlashAttribute("style","alert-success");
        }catch(StudentNotFoundException e){
            redirectAttributes.addFlashAttribute("message","The Student("+id+") hasn't been found!");
            redirectAttributes.addFlashAttribute("style","alert-danger");
        }
        return "redirect:/user/students?keyword="+keyword+"&page="+page;
    }

    @GetMapping(path = "/admin/newStudent")
    public String newStudent(Model model){
        model.addAttribute("student",new Student());
        model.addAttribute("pageTitle","Add new Student");
        model.addAttribute("message","Add new Student!");
        model.addAttribute("style","alert-dark");
        return "saveStudent";
    }

    @PostMapping(path = "/admin/saveStudent")
    public String saveStudent(Model model, @Valid Student student,
                         BindingResult bindingResult,
                         @RequestParam String id,
                         @RequestParam(defaultValue = "")String keyword,
                         @RequestParam(defaultValue = "0")int page,
                              RedirectAttributes redirectAttributes){
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("message", "You didn't create or update a student correctly!");
                model.addAttribute("style", "alert-danger");
                return "saveStudent";
            }
            student.setIdStudent(id);
            Student savedStudent=studentService.updateStudent(student);
            model.addAttribute("message", "This student (" + savedStudent.getIdStudent() + ") was updated correctly!");
            model.addAttribute("style", "alert-success");
            return "saveStudent";
        }catch(StudentNotFoundException e){
            redirectAttributes.addFlashAttribute("style", "alert-info");
            redirectAttributes.addFlashAttribute("message", "This student (" + student.getIdStudent() + ") wasn't found! You can create a new one!");
            return "redirect:/admin/newStudent";
        }
    }

    @GetMapping(path="/admin/updateStudent")
    public String updateStudent(Model model, String id, String keyword, int page,
                                RedirectAttributes redirectAttributes){
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        try {
            Student student = studentService.getCountStudent(id);
            model.addAttribute("student", student);
            model.addAttribute("pageTitle", "Update Student");
            return "saveStudent";
        }catch(StudentNotFoundException e){
            redirectAttributes.addFlashAttribute("message","Unfortunately the student wasn't found!");
            redirectAttributes.addFlashAttribute("style", "alert-danger");
            return "redirect:/user/students?keyword="+keyword+"&page="+page;
        }
    }
}
