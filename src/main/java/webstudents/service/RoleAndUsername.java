package webstudents.service;

import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

public class RoleAndUsername {

    public static void isAdmin(Model model) {
        boolean admin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"));
        model.addAttribute("isAdmin", admin);
    }

    public static void getUsername(Model model) {
        val name = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("user-login", name.equals("anonymousUser") ? "anonymous" : name);

    }

    public static void isAdminOrModer(Model model) {
        boolean adminOrModer = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .allMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals("ADMIN") ||
                                grantedAuthority.getAuthority().equals("MODER"));

        model.addAttribute("isAdminOrModer", adminOrModer);
    }

    public static final String ONLY_ADMIN_AND_MODER = "hasAuthority('ADMIN') or hasAuthority('MODER')";

}
