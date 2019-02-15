package webstudents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webstudents.models.Discipline;
import webstudents.repo.DisciplineRepo;

@Controller
public class DisciplineController {

    @Autowired
    private DisciplineRepo disciplineRepo;

    private static final String ONLY_ADMIN_AND_MODER = "hasAuthority('ADMIN') or hasAuthority('MODER')";

    @GetMapping("/disciplines")
    public String showAllDisc(Model model) {

        commonModelMapping(model);
        return "disciplines";
    }

    @PreAuthorize(ONLY_ADMIN_AND_MODER)
    @PostMapping("/addDiscipline")
    public String addDiscipline(@RequestParam String disciplineName,
                                Model model) {

        disciplineRepo.save(new Discipline(disciplineName));

        return "redirect:/disciplines";
    }

    @PreAuthorize(ONLY_ADMIN_AND_MODER)
    @RequestMapping("/deleteDiscipline/{id}")
    public String deleDicipline(@PathVariable("id") int id) {

        disciplineRepo.deleteById(id);
        return "redirect:/disciplines";
    }

    @PreAuthorize(ONLY_ADMIN_AND_MODER)
    @RequestMapping("editDiscipline/{id}")
    public String editDisciplineRender(@PathVariable("id") Discipline discipline,
                                       Model model) {
        model.addAttribute("disciplineForEdit", discipline);
        commonModelMapping(model);

        return "disciplines";
    }

    @PreAuthorize(ONLY_ADMIN_AND_MODER)
    @RequestMapping("editDiscipline/{id}/patch")
    public String editDiscipline(@PathVariable("id") Discipline discipline,
                                 @RequestParam String disciplineName) {
        discipline.setDisciplineName(disciplineName);
        disciplineRepo.save(discipline);

        return String.format("redirect:/editDiscipline/%s", discipline.getId());
    }

    private void commonModelMapping(Model model) {
        model.addAttribute("disciplines", disciplineRepo.findAll());
    }
}


