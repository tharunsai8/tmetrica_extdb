package model.domain.exceptions;

public class NotUniqueEmailException extends RuntimeException {
    public NotUniqueEmailException(String email) {
        super("Email \"" + email + "\" is already exist");
    }
}
