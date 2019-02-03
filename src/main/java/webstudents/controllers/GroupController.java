package webstudents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webstudents.models.SchoolGroup;
import webstudents.repo.SchoolGroupRepo;
import webstudents.repo.StudentRepo;

@Controller
public class GroupController {

    @Autowired
    private SchoolGroupRepo schoolGroupRepo;

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/groups")
    public String showAll(Model model) {
        model.addAttribute("groups", schoolGroupRepo.findAll());
        return "groups";
    }

    @GetMapping("/members/{id}")
    public String seeAllInGroup(@PathVariable("id") int id,
                                Model model) {

        model.addAttribute("students", studentRepo.findAllByGroupId(id));
        return "members";
    }

    @PostMapping("/addGroup")
    public String addGroup(@RequestParam Integer groupNumber,
                           Model model) {

        if (groupNumber != null) {
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
    public String editGroupRender(@PathVariable("id") SchoolGroup schoolGroup, Model model) {
        model.addAttribute("anygroup", schoolGroup);
        model.addAttribute("groups", schoolGroupRepo.findAll());
        return "groups";
    }

    @RequestMapping("editGroup/{id}/patch")
    public String editGroup(@PathVariable("id") SchoolGroup schoolGroup,
                            @RequestParam Integer groupNumber) {
        schoolGroup.setGroupNumber(groupNumber);

        if (groupNumber != null) {
            schoolGroupRepo.save(schoolGroup);
        }
        return String.format("redirect:/editGroup/%s", schoolGroup.getId());
    }
}

