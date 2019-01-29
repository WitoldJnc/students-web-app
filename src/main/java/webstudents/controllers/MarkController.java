package webstudents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import webstudents.repo.DisciplineRepo;
import webstudents.repo.MarkRepo;
import webstudents.repo.StudentRepo;

@Controller
public class MarkController {

    @Autowired
    private MarkRepo markRepo;

    @Autowired
    private DisciplineRepo disciplineRepo;

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/marks")
    private String findAllMarks(Model model) {
        model.addAttribute("marks", markRepo.findAll());
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("disciplines", disciplineRepo.findAll());
        return "marks";
    }
}
