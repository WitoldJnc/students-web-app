package webstudents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webstudents.models.Role;
import webstudents.models.User;
import webstudents.repo.RoleRepo;
import webstudents.repo.UserRepo;


@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @GetMapping("/users")
    public String userList(Model model) {
        commonModelMapprin(model);
        return "userList";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {

        userRepo.deleteById(id);
        return "redirect:/users";
    }

    @RequestMapping("/editUser/{id}")
    public String editRender(@PathVariable("id") User user, Model model) {

        model.addAttribute("user", user);
        commonModelMapprin(model);

        return "userList";
    }

    @RequestMapping("/editUser/{id}/patch")
    public String edit(@PathVariable("id") User user,
                       @RequestParam(value = "username") String username,
                       @RequestParam(value = "role") Role role) {

        user.setUsername(username);
        user.setRoles(role);
        userRepo.save(user);

        return String.format("redirect:/editUser/%s", user.getId());
    }

    private void commonModelMapprin(Model model) {
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("edit-role", roleRepo.findAll());
    }

}
