package pro.sky.ExamProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalQuestionAmountException extends RuntimeException {
    public IllegalQuestionAmountException() {
    }

    public IllegalQuestionAmountException(String message) {
        super(message);
    }
}
