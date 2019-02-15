package webstudents.controllers;

import lombok.val;
import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webstudents.models.Discipline;
import webstudents.models.SchoolGroup;
import webstudents.repo.DisciplineRepo;
import webstudents.repo.SchoolGroupRepo;
import webstudents.repo.StudentRepo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

@Controller
public class GroupController {

    @Autowired
    private SchoolGroupRepo schoolGroupRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private DisciplineRepo disciplineRepo;

    @GetMapping("/groups")
    public String showAll(Model model) {
        commonModelMapping(model);
        model.addAttribute("notChosenDiscipline", disciplineRepo.findAll());
        return "groups";
    }

    @GetMapping("/members/{id}")
    public String seeAllInGroup(@PathVariable("id") int id,
                                Model model) {

        model.addAttribute("students", studentRepo.findAllByGroupId(id));
        return "members";
    }

    @PostMapping("/addGroup")
    public String addGroup(@RequestParam Integer groupNumber, Model model,
                           @RequestParam(value = "disciplines", required = false) Discipline... discipline) {

        SchoolGroup schoolGroup = new SchoolGroup();
        val disciplines = new HashSet<>(Arrays.asList(discipline));

        schoolGroup.setDisciplines(disciplines);
        schoolGroup.setGroupNumber(groupNumber);

        schoolGroupRepo.save(schoolGroup);

        commonModelMapping(model);
        return "redirect:/groups";
    }

    @RequestMapping("/deleteGroup/{id}")
    public String deleteGroup(@PathVariable("id") int id) {
        schoolGroupRepo.deleteById(id);
        return "redirect:/groups";
    }

    @RequestMapping("editGroup/{id}")
    public String editGroupRender(@PathVariable("id") SchoolGroup schoolGroup, Model model) {

        commonModelMapping(model);

        val disciplineSet = new LinkedHashSet<>(disciplineRepo.findAll());

        model.addAttribute("editingGroup", schoolGroup);
        model.addAttribute("notChosenDiscipline", SetUtils
                .difference(disciplineSet, schoolGroup.getDisciplines()));
        return "groups";
    }

    @RequestMapping("editGroup/{id}/patch")
    public String editGroup(@PathVariable("id") SchoolGroup schoolGroup,
                            @RequestParam Integer groupNumber,
                            @RequestParam(value = "disciplines", required = false) Discipline... discipline) {

        val disciplines = new HashSet<>(Arrays.asList(discipline));

        schoolGroup.setDisciplines(disciplines);
        schoolGroup.setGroupNumber(groupNumber);
        schoolGroupRepo.save(schoolGroup);

        return String.format("redirect:/editGroup/%s", schoolGroup.getId());
    }

    private void commonModelMapping(Model model) {
        model.addAttribute("groups", schoolGroupRepo.findAll());
        model.addAttribute("allDisciplines", disciplineRepo.findAll());
        model.addAttribute("disciplines", disciplineRepo.findAll());
    }
}

