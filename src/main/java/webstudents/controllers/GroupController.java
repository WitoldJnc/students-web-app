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
public class GroupController {

    @Autowired
    private SchoolGroupRepo schoolGroupRepo;

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("groups", schoolGroupRepo.findAll());
        return "groups";
    }

    @GetMapping("/members/{id}")
    public String seeAllInGroup(@PathVariable("id") int id,
                                Model model) {

        model.addAttribute("students", studentRepo.findAllInGroup(id));
        return "members";
    }

    @PostMapping("/addGroup")
    public String addGroup(@RequestParam Integer groupNumber,
                           Model model) {

        if(groupNumber != null){
            model.addAttribute("groups",
                    schoolGroupRepo.save(new SchoolGroup(groupNumber)));
        }
        return "redirect:/groups";
    }

    @RequestMapping("/deleteGroup/{id}")
    public String deleteGroup(@PathVariable("id") int id) {
        schoolGroupRepo.deleteById(id);
        return "redirect:/groups";
    }

    @RequestMapping("editGroup/{id}")
    public String editGroup(@PathVariable("id") int id,
                            @RequestParam(required = false) Integer groupNumber,
                            @RequestParam(value = "newNumb", required = false) SchoolGroup schoolGroup,
                            Model model) {
        model.addAttribute("anygroup", schoolGroupRepo.getOne(id));
        model.addAttribute("groups", schoolGroupRepo.findAll());
        if (schoolGroup != null) {
            schoolGroup.setGroupNumber(groupNumber);
            schoolGroupRepo.save(schoolGroup);

        }
        return "groups";
    }
}

