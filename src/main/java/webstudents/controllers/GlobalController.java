package webstudents.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import static webstudents.service.RoleAndUsername.*;

@ControllerAdvice
public final class GlobalController {

    @ModelAttribute
    public void addAttributes(Model model) {
        isAdminOrModer(model);
        isAdmin(model);
        getUsername(model);
    }
}
