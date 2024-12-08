package pro.sky.ExamProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidStringException extends RuntimeException {
    public InvalidStringException() {
    }

    public InvalidStringException(String message) {
        super(message);
    }
}
