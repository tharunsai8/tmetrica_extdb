package controller.util;

import model.domain.entity.User;
import model.domain.enums.Role;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Auth utils.
 */
public final class AuthUtils {
    private AuthUtils() {
    }

    /**
     * Is authenticated user.
     *
     * @param request the request
     * @return the user
     */
    public static User isAuthenticated(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }


    /**
     * Has authority boolean.
     *
     * @param request the request
     * @param role    the role
     * @return the boolean
     */
    public static boolean hasAuthority(HttpServletRequest request, Role role) {
        User user = (User) request.getSession().getAttribute("user");
        return (user != null) && user.getRoles().stream().anyMatch(userRole -> userRole.toString().equals(role.toString()));
    }
}
