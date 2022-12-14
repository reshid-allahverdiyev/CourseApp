package Course.aspect;

import Course.constraints.Status;
import Course.response.ErrorResponse;
import Course.util.Translator;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse dataNotFound(Exception exception){
        return ErrorResponse.builder()
                .localDateTime(LocalDateTime.now())
                .status(Translator.toLocale(Status.ERROR.getMessage()))
                .build();
    }
}
