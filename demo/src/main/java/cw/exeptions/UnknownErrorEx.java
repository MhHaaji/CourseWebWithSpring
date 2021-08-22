package cw.exeptions;

public class UnknownErrorEx extends RuntimeException {
    public UnknownErrorEx() {
        super("UNKNOWN_ERROR");
    }
}
