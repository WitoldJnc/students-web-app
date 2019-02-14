package webstudents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import webstudents.models.User;
import webstudents.repo.UserRepo;

@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user) {
//        User usertFromDb = userRepo.findByUsername(user.getUsername());

        return "redirect:/login";
    }
}
