package pro.sky.ExamProject.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.ExamProject.exception.*;
import pro.sky.ExamProject.model.Question;
import pro.sky.ExamProject.service.QuestionService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Random RANDOM = new Random();

    private  final Set<Question> javaQuestions;


    public JavaQuestionService() {
        this.javaQuestions = new HashSet<>();
    }

    @Override
    public Question add(String question, String answer) {
        checkString(question);
        checkString(answer);
        Question newQuestion = new Question(question, answer);
        return add(newQuestion);
    }

    @Override
    public Question add(Question newQuestion) {
        javaQuestions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question remove(String question, String answer) {
        checkString(question);
        checkString(answer);
        Question removedQuestion = new Question(question, answer);
        return remove(removedQuestion);
    }

    @Override
    public Question remove(Question removedQuestion) {
        if (!javaQuestions.contains(removedQuestion)) {
            throw new NotFoundQuestionException("Вопрос не найден!");
        }
        javaQuestions.remove(removedQuestion);
        return removedQuestion;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(javaQuestions);
    }

    @Override
    public Question getRandomQuestion() {
        if (javaQuestions.isEmpty()) {
            throw new JavaQuestionsSetIsEmptyException("Пустой список вопросов!");
        }
        return javaQuestions.stream()
                .skip(RANDOM.nextInt(javaQuestions.size()))
                .findFirst()
                .orElseThrow(NotFoundQuestionException::new);
    }

    private void checkString(String string) {
        if (StringUtils.isBlank(string)) {
            throw new InvalidStringException("Строка пуста!");
        }
    }

}
