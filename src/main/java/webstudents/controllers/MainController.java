package webstudents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import webstudents.service.RoleAndUsername;

@Controller
public class MainController extends RoleAndUsername {

    @GetMapping("/")
    public String index(Model model) {
        isAdmin(model);
        getUsername(model);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        isAdmin(model);
        getUsername(model);
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(Model model) {
        isAdmin(model);
        getUsername(model);
        return "logout";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(Model model) {
        isAdmin(model);
        getUsername(model);
        return "accessDenied";
    }

}
