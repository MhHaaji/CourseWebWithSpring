package cw.exeptions;

public class AccessDeniedEx extends RuntimeException {
    public AccessDeniedEx(String s) {
        super(s);
    }
}
