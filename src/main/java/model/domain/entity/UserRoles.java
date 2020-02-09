package model.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.domain.enums.Role;

@NoArgsConstructor
@Getter
@Setter
public class UserRoles {
    private long userId;
    private Role role;

    public UserRoles(long userId, Role role) {
        this.userId = userId;
        this.role = role;
    }
}
