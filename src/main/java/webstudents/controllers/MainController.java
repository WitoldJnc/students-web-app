package webstudents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage() {

        return "logout";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {

        return "accessDenied";
    }

}
