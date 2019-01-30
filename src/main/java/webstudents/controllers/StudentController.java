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
    public String add(@RequestParam(value = "firstName", required = false) String firstName,
                      @RequestParam(value = "lastName", required = false) String lastName,
                      @RequestParam(value = "group", required = false) SchoolGroup schoolGroup,
                      Model model) {

        Student student = new Student(firstName, lastName);
        student.setGroup(schoolGroup);
        studentRepo.save(student);


        return "redirect:/students";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id,
                         RedirectAttributes redirect) {
        studentRepo.deleteById(id);
        redirect.addFlashAttribute("success", "Deleted student successfully!");
        return "redirect:/students";
    }

    @RequestMapping("/editStudent/{id}")
    public String edit(@PathVariable("id") int id,
                       @RequestParam(value = "firstName", required = false) String firstName,
                       @RequestParam(value = "lastName", required = false) String lastName,
                       @RequestParam(value = "group", required = false) SchoolGroup schoolGroup,
                       @RequestParam(value = "studentId", required = false) Student student,
                       Model model) {
        model.addAttribute("groups", schoolGroupRepo.findAll());
        model.addAttribute("student", studentRepo.getOne(id));
        model.addAttribute("students", studentRepo.findAll());
        if (student != null) {
            student.setFirstName(firstName);
            student.setLastName(lastName);
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
            return "redirect:/students";
        }
        model.addAttribute("students", lastName);
        return "students";
    }
}


