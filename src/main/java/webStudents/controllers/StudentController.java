package webStudents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webStudents.models.Student;
import webStudents.repo.StudentRepo;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping
    public String main(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        return "students";
    }

    @PostMapping
    public String add(@RequestParam String fname,
                      @RequestParam String lname,
                      Model model){
        studentRepo.save(new Student(fname, lname));
        model.addAttribute("students", studentRepo.findAll());
        return "students";
    }

}