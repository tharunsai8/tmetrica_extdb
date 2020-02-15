package controller.util;

import model.domain.entity.User;
import model.domain.enums.Role;

import javax.servlet.http.HttpServletRequest;

public final class AuthUtils {
    private AuthUtils() {
    }

    public static User isAuthenticated(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }


    public static boolean hasAuthority(HttpServletRequest request, Role role) {
        User user = (User) request.getSession().getAttribute("user");
        return (user != null) && user.getRoles().stream().anyMatch(userRole -> userRole.toString().equals(role.toString()));
    }
}
