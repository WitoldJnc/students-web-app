package webstudents.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

public class RoleAndUsername {

    protected void isAdmin(Model model) {
        boolean admin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"));
        model.addAttribute("isAdmin", admin);
    }

    protected void getUsername(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("user-login", name.equals("anonymousUser") ? "anonymous" : name);

    }

    protected void isAdminOrModer(Model model) {
        boolean adminOrModer = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .allMatch(grantedAuthority ->
                        (grantedAuthority.getAuthority().equals("ADMIN")
                                ||
                                (grantedAuthority.getAuthority().equals("MODER"))));

        model.addAttribute("isAdminOrModer", adminOrModer);
    }
}
