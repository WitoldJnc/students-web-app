package webstudents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webstudents.models.SchoolGroup;
import webstudents.models.Student;
import webstudents.repo.SchoolGroupRepo;
import webstudents.repo.StudentRepo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Controller

public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private SchoolGroupRepo schoolGroupRepo;

    @GetMapping("/students")
    public String index(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("groups", schoolGroupRepo.findAll());

        return "students";
    }

    @PostMapping("/studentAdd")
    public String add(@RequestParam(required = false) String fname,
                      @RequestParam(required = false) String lname,
                      @RequestParam(required =false, value = "group") SchoolGroup schoolGroup,
                      Model model) {

        Student student = new Student(fname, lname);
        student.setGroup(schoolGroup);

        model.addAttribute("students", studentRepo.save(student));

        return "redirect:/students";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id,
                         RedirectAttributes redirect) {
        studentRepo.deleteById(id);
        redirect.addFlashAttribute("success", "Deleted student successfully!");
        return "redirect:/students";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id,
                       @RequestParam String fname,
                       @RequestParam String lname,
                       @RequestParam(value = "group") SchoolGroup schoolGroup,
                       @RequestParam(value = "studentId", required = false) Student student,
                       Model model) {
        model.addAttribute("groups", schoolGroupRepo.findAll());
        model.addAttribute("student", studentRepo.getOne(id));
        model.addAttribute("students", studentRepo.findAll());
        if (student != null) {
            student.setFirstName(fname);
            student.setLastName(lname);
            student.setGroup(schoolGroup);
            studentRepo.save(student);
            return "redirect:/students";
        }
        return "students";
    }

    @RequestMapping("/filter")
    public String filter(@RequestParam String filter,
                         Model model) {
        List<Student> byFirstName;

        if (filter != null && !filter.isEmpty()) {
            byFirstName = studentRepo.findByFirstName(filter);
        } else {
            byFirstName = studentRepo.findAll();
            return "redirect:/students";
        }
        model.addAttribute("students", byFirstName);
        return "students";
    }

    @RequestMapping("/filterLastName")
    public String filterLastName(@RequestParam String filterLastName,
                                 Model model) {
        List<Student> lastName;

        if (filterLastName != null && !filterLastName.isEmpty()) {
            lastName = studentRepo.findByLastName(filterLastName);
        } else {
            lastName = studentRepo.findAll();
            return "redirect:/students";
        }
        model.addAttribute("students", lastName);
        return "students";
    }
}


