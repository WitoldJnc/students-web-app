package webstudents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webstudents.models.Discipline;
import webstudents.models.SchoolGroup;
import webstudents.repo.DisciplineRepo;

@Controller
public class DisciplineController {

    @Autowired
    private DisciplineRepo disciplineRepo;

    @GetMapping("/disciplines")
    public String showAllDisc(Model model) {
        model.addAttribute("disciplines", disciplineRepo.findAll());
        return "disciplines";
    }

    @PostMapping("/addDiscipline")
    public String addDiscipline(@RequestParam String disciplineName,
                                Model model) {

        if (disciplineName != null) {
            model.addAttribute("disciplines",
                    disciplineRepo.save(new Discipline(disciplineName)));
        }
        return "redirect:/disciplines";
    }

    @RequestMapping("/deleteDiscipline/{id}")
    public String deleDicipline(@PathVariable("id") int id) {
        disciplineRepo.deleteById(id);
        return "redirect:/disciplines";
    }

    @RequestMapping("editDiscipline/{id}")
    public String editDiscipline(@PathVariable("id") int id,
                                 @RequestParam(required = false) String disciplineName,
                                 @RequestParam(value = "id", required = false) Discipline discipline,
                                 Model model) {
        model.addAttribute("anydiscipline", disciplineRepo.getOne(id));
        model.addAttribute("disciplines", disciplineRepo.findAll());
        if (discipline != null) {
            discipline.setDisciplineName(disciplineName);
            disciplineRepo.save(discipline);
        }
        return "disciplines";
    }

}


