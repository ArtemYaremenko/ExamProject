package pro.sky.ExamProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundQuestionException extends RuntimeException {
    public NotFoundQuestionException() {
    }

    public NotFoundQuestionException(String message) {
        super(message);
    }
}
