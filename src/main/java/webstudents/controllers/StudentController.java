package webstudents.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webstudents.models.SchoolGroup;
import webstudents.models.Student;
import webstudents.repo.SchoolGroupRepo;
import webstudents.repo.StudentRepo;
import webstudents.service.RoleAndUsername;

@Controller
public class StudentController extends RoleAndUsername {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private SchoolGroupRepo schoolGroupRepo;

    private static final String ONLY_ADMIN_AND_MODER = "hasAuthority('ADMIN') or hasAuthority('MODER')";

    @GetMapping("/students")
    public String index(Model model) {
        commonModelMapprin(model);
        return "students";
    }

    @PreAuthorize(ONLY_ADMIN_AND_MODER)
    @PostMapping("/studentAdd")
    public String add(@RequestParam(value = "firstName") String firstName,
                      @RequestParam(value = "lastName") String lastName,
                      @RequestParam(value = "group") SchoolGroup schoolGroup) {
        Student student = new Student(firstName, lastName);
        student.setGroup(schoolGroup);

        studentRepo.save(student);

        return "redirect:/students";
    }

    @PreAuthorize(ONLY_ADMIN_AND_MODER)
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {

        studentRepo.deleteById(id);

        return "redirect:/students";
    }

    @PreAuthorize(ONLY_ADMIN_AND_MODER)
    @RequestMapping("/editStudent/{id}")
    public String editRender(@PathVariable("id") Student student, Model model) {

        model.addAttribute("student", student);
        commonModelMapprin(model);
        return "students";
    }

    @PreAuthorize(ONLY_ADMIN_AND_MODER)
    @RequestMapping("/editStudent/{id}/patch")
    public String edit(@PathVariable("id") Student student,
                       @RequestParam(value = "firstName") String firstName,
                       @RequestParam(value = "lastName") String lastName,
                       @RequestParam(value = "group") SchoolGroup schoolGroup) {

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setGroup(schoolGroup);
        studentRepo.save(student);

        return String.format("redirect:/editStudent/%s", student.getId());
    }

    @RequestMapping("/filter")
    public String filter(@RequestParam String filter,
                         Model model) {

        model.addAttribute("students", studentRepo.findByFirstName(filter));

        return StringUtils.isNotEmpty(filter)
                ? "students"
                : "redirect:/students";
    }

    @RequestMapping("/filterLastName")
    public String filterLastName(@RequestParam String filterLastName,
                                 Model model) {

        model.addAttribute("students", studentRepo.findByLastName(filterLastName));

        return StringUtils.isNotEmpty(filterLastName)
                ? "students"
                : "redirect:/students";

    }

    private void commonModelMapprin(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("groups", schoolGroupRepo.findAll());
        isAdminOrModer(model);
        isAdmin(model);
        getUsername(model);
    }

}


