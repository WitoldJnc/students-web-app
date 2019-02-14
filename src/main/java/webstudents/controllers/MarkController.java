package webstudents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webstudents.models.Discipline;
import webstudents.models.Mark;
import webstudents.models.SchoolGroup;
import webstudents.models.Student;
import webstudents.repo.DisciplineRepo;
import webstudents.repo.MarkRepo;
import webstudents.repo.SchoolGroupRepo;
import webstudents.repo.StudentRepo;

@Controller
public class MarkController {

    @Autowired
    private MarkRepo markRepo;

    @Autowired
    private DisciplineRepo disciplineRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private SchoolGroupRepo schoolGroupRepo;

    @GetMapping("/marks")
    public String findAllMarks(Model model) {
        commonModelMapping(model);

        return "marks";
    }

    @PostMapping("/addMarks")
    public String addMarks(@RequestParam(value = "students", required = false) Student student,
                           @RequestParam(value = "disciplines", required = false) Discipline discipline,
                           @RequestParam(value = "mark", required = false) Integer mark,
                           @RequestParam(value = "groups", required = false) SchoolGroup group, Model model) {

        if (student != null) {
            markRepo.save(new Mark(discipline, student, mark));

            return "redirect:/marks";
        }

        model.addAttribute("selectedGroup", group);
        model.addAttribute("disciplines", group.getDisciplines());
        model.addAttribute("studentInGroup", studentRepo.findAllByGroupId(group.getId()));
        commonModelMapping(model);
        return "marks";
    }

    @RequestMapping("/deleteMark/{id}")
    public String deleteMark(@PathVariable("id") int id) {

        markRepo.deleteById(id);

        return "redirect:/marks";
    }

    @RequestMapping("/editMark/{id}")
    public String editMarkRendeer(@PathVariable("id") Mark mark, Model model) {

        model.addAttribute("mark", mark);

        commonModelMapping(model);
        return "marks";
    }

    @RequestMapping("/editMark/{id}/patch")
    public String editMark(@PathVariable("id") Mark markmodel,
                           @RequestParam(value = "mark") Integer mark) {

        markmodel.setMark(mark);
        markRepo.save(markmodel);

        return String.format("redirect:/editMark/%s", markmodel.getId());
    }

    private void commonModelMapping(Model model) {
        model.addAttribute("marks", markRepo.findAll());
        model.addAttribute("groups", schoolGroupRepo.findAll());
        model.addAttribute("disciplines", disciplineRepo.findAll());
    }
}
