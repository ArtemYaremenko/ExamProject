package pro.sky.ExamProject.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.ExamProject.exception.JavaQuestionsSetIsEmptyException;
import pro.sky.ExamProject.exception.NotFoundQuestionException;
import pro.sky.ExamProject.model.Question;
import pro.sky.ExamProject.repository.QuestionRepository;
import pro.sky.ExamProject.service.QuestionService;

import java.util.Collection;

import static pro.sky.ExamProject.utils.QuestionsUtils.*;

@Service
@Qualifier("math")
public class MathQuestionService implements QuestionService {

    private QuestionRepository mathQuestions;

    public MathQuestionService(QuestionRepository mathQuestions) {
        this.mathQuestions = mathQuestions;
    }

    @Override
    public Question add(String question, String answer) {
        checkString(question);
        checkString(answer);
        Question newQuestion = new Question(question, answer);
        return mathQuestions.add(newQuestion);
    }


    @Override
    public Question remove(String question, String answer) {
        checkString(question);
        checkString(answer);
        Question removedQuestion = new Question(question, answer);
        return mathQuestions.remove(removedQuestion);
    }


    @Override
    public Collection<Question> getAll() {
        return mathQuestions.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (mathQuestions.getAll().isEmpty()) {
            throw new JavaQuestionsSetIsEmptyException("Пустой список вопросов!");
        }
        return mathQuestions.getAll().stream()
                .skip(RANDOM.nextInt(mathQuestions.getAll().size()))
                .findFirst()
                .orElseThrow(NotFoundQuestionException::new);
    }
}
