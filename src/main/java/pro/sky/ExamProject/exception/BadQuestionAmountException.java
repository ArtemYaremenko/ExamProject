package pro.sky.ExamProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadQuestionAmountException extends RuntimeException {
    public BadQuestionAmountException() {
    }

    public BadQuestionAmountException(String message) {
        super(message);
    }
}
