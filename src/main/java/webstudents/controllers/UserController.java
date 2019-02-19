package webstudents.controllers;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webstudents.models.User;
import webstudents.repo.RoleRepo;
import webstudents.repo.UserRepo;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration(Model model) {
        commonModelMapping(model);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam(value = "username") String username,
                          @RequestParam(value = "password") String password,
                          User user, Model model) {
        commonModelMapping(model);
        val userFromDb = Optional.ofNullable(userRepo.findByUsername(user.getUsername()));

        if (userFromDb.isPresent()) {
            return "registration";
        }
        String encode = passwordEncoder.encode(password);
        user.setPassword(encode);
        user.setUsername(username);
        user.setRoles(roleRepo.getOne(2));

        userRepo.save(user);

        return "redirect:/login";
    }

    private void commonModelMapping(Model model) {

    }
}
