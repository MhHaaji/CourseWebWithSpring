package cw.exeptions;

public class UserNotFoundEx extends RuntimeException {
    public UserNotFoundEx(Long id) {
        super("user not found: " + id);
    }
}
