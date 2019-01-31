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
    public String editDisciplineRender(@PathVariable("id") Discipline discipline,
                                       Model model) {
        model.addAttribute("disciplineForEdit", discipline);
        model.addAttribute("disciplines", disciplineRepo.findAll());

        return "disciplines";
    }

    @RequestMapping("editDiscipline/{id}/path")
    public String editDiscipline(@PathVariable("id") Discipline discipline,
                                 @RequestParam String disciplineName) {
        discipline.setDisciplineName(disciplineName);
        disciplineRepo.save(discipline);
        return String.format("redirect:/editDiscipline/%s", discipline.getId());
    }

}


