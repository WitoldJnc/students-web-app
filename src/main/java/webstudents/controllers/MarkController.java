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
    private String findAllMarks(Model model) {
        model.addAttribute("marks", markRepo.findAll());
        model.addAttribute("disciplines", disciplineRepo.findAll());
        return "marks";
    }

    @PostMapping("/addMarks")
    public String addMarks(@RequestParam(value = "students", required = false) Student student,
                           @RequestParam(value = "disciplines", required = false) Discipline discipline,
                           @RequestParam(value = "mark", required = false) Integer mark,
                           Model model) {
        Mark mark1 = new Mark(mark);
        mark1.setDiciplineId(discipline);
        mark1.setStudentId(student);
        markRepo.save(mark1);
        return "redirect:/marks";
    }

    @RequestMapping("/deleteMark/{id}")
    public String deleteMark(@PathVariable("id") int id) {
        markRepo.deleteById(id);
        return "redirect:/marks";
    }

    @RequestMapping("/editMark/{id}")
    public String editMarkRendeer(@PathVariable("id") Mark mark, Model model) {
        model.addAttribute("disciplines", disciplineRepo.findAll());
        model.addAttribute("mark", mark);
        model.addAttribute("marks", markRepo.findAll());

        return "marks";
    }

    @RequestMapping("/editMark/{id}/path")
    public String editMark(@PathVariable("id") Mark markmodel,
                           @RequestParam(value = "students") Student student,
                           @RequestParam(value = "disciplines") Discipline discipline,
                           @RequestParam(value = "mark") Integer mark, Model model) {

        markmodel.setStudentId(student);
        markmodel.setDiciplineId(discipline);
        markmodel.setMark(mark);
        markRepo.save(markmodel);

        return String.format("redirect:/editMark/%s", markmodel.getId());
    }
}
