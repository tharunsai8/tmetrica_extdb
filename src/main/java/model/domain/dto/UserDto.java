package model.domain.dto;

/**
 * The type User dto.
 */
public class UserDto {
    private String email;
    private String password;

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Instantiates a new User dto.
     *
     * @param email    the email
     * @param password the password
     */
    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
