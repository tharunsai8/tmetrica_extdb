package model.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.domain.enums.Role;

/**
 * The type User roles.
 */
@NoArgsConstructor
@Getter
@Setter
public class UserRoles {
    private long userId;
    private Role role;

    /**
     * Instantiates a new User roles.
     *
     * @param userId the user id
     * @param role   the role
     */
    public UserRoles(long userId, Role role) {
        this.userId = userId;
        this.role = role;
    }
}
