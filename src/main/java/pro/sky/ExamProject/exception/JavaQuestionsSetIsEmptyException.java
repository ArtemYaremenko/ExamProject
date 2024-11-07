package pro.sky.ExamProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JavaQuestionsSetIsEmptyException extends RuntimeException {
    public JavaQuestionsSetIsEmptyException() {
    }

    public JavaQuestionsSetIsEmptyException(String message) {
        super(message);
    }
}
