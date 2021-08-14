package cw.advices;

import cw.exeptions.UserNotFoundEx;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserIDNotFound {
    @ResponseBody
    @ExceptionHandler(UserNotFoundEx.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFound(UserNotFoundEx ex){
        return ex.getMessage();
    }
}
