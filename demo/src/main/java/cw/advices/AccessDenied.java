package cw.advices;

import cw.exeptions.AccessDeniedEx;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice

public class AccessDenied {
    @ResponseBody
    @ExceptionHandler(AccessDeniedEx.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String userNotFound(AccessDeniedEx ex) {
        return ex.getMessage();
    }
}
