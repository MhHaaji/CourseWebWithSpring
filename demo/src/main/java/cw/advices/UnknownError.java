package cw.advices;

import cw.exeptions.UnknownErrorEx;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UnknownError {
    @ResponseBody
    @ExceptionHandler(UnknownErrorEx.class)
    String userNotFound(UnknownErrorEx ex) {
        return ex.getMessage();
    }

}
