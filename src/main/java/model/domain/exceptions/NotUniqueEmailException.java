package model.domain.exceptions;

/**
 * The type Not unique email exception.
 */
public class NotUniqueEmailException extends RuntimeException {
    /**
     * Instantiates a new Not unique email exception.
     *
     * @param email the email
     */
    public NotUniqueEmailException(String email) {
        super("Email \"" + email + "\" is already exist");
    }
}
