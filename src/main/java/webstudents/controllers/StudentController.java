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
    public String add(@RequestParam(value = "firstName") String firstName,
                      @RequestParam(value = "lastName") String lastName,
                      @RequestParam(value = "group") SchoolGroup schoolGroup) {
        Student student = new Student(firstName, lastName);
        student.setGroup(schoolGroup);
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            studentRepo.save(student);
        }
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
    public String editRender(@PathVariable("id") Student student, Model model) {
        model.addAttribute("groups", schoolGroupRepo.findAll());
        model.addAttribute("student", student);
        model.addAttribute("students", studentRepo.findAll());
        return "students";
    }

    @RequestMapping("/editStudent/{id}/patch")
    public String edit(@PathVariable("id") Student student,
                       @RequestParam(value = "firstName") String firstName,
                       @RequestParam(value = "lastName") String lastName,
                       @RequestParam(value = "group") SchoolGroup schoolGroup) {
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setGroup(schoolGroup);

        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            studentRepo.save(student);
        }
        return String.format("redirect:/editStudent/%s", student.getId());
    }

    @RequestMapping("/filter")
    public String filter(@RequestParam String filter,
                         Model model) {

        model.addAttribute("students", studentRepo.findByFirstName(filter));

        return (filter != null && !filter.isEmpty()) ?
                "students" : "redirect:/students";
    }

    @RequestMapping("/filterLastName")
    public String filterLastName(@RequestParam String filterLastName,
                                 Model model) {

        model.addAttribute("students", studentRepo.findByLastName(filterLastName));

        return (filterLastName != null && !filterLastName.isEmpty()) ?
                "students" : "redirect:/students";

    }

}


