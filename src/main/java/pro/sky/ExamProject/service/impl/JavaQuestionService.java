package pro.sky.ExamProject.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.ExamProject.exception.*;
import pro.sky.ExamProject.model.Question;
import pro.sky.ExamProject.repository.QuestionRepository;
import pro.sky.ExamProject.service.QuestionService;

import java.util.Collection;

import static pro.sky.ExamProject.utils.QuestionsUtils.*;

@Service
@Qualifier("java")
public class JavaQuestionService implements QuestionService {

    private QuestionRepository javaQuestions;

    public JavaQuestionService(QuestionRepository javaQuestions) {
        this.javaQuestions = javaQuestions;
    }

    @Override
    public Question add(String question, String answer) {
        checkString(question);
        checkString(answer);
        Question newQuestion = new Question(question, answer);
        return javaQuestions.add(newQuestion);
    }


    @Override
    public Question remove(String question, String answer) {
        checkString(question);
        checkString(answer);
        Question removedQuestion = new Question(question, answer);
        return javaQuestions.remove(removedQuestion);
    }


    @Override
    public Collection<Question> getAll() {
        return javaQuestions.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (javaQuestions.getAll().isEmpty()) {
            throw new JavaQuestionsSetIsEmptyException("Пустой список вопросов!");
        }
        return javaQuestions.getAll().stream()
                .skip(RANDOM.nextInt(javaQuestions.getAll().size()))
                .findFirst()
                .orElseThrow(NotFoundQuestionException::new);
    }
}
