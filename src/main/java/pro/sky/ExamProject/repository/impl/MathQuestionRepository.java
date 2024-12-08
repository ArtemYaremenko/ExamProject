package pro.sky.ExamProject.repository.impl;

import org.springframework.stereotype.Repository;
import pro.sky.ExamProject.exception.NotFoundQuestionException;
import pro.sky.ExamProject.model.Question;
import pro.sky.ExamProject.repository.QuestionRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Repository
public class MathQuestionRepository implements QuestionRepository {

    private Set<Question> mathQuestions;

    public MathQuestionRepository(Set<Question> mathQuestions) {
        this.mathQuestions = mathQuestions;
    }

    @Override
    public Question add(Question newQuestion) {
        mathQuestions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question remove(Question removedQuestion) {
        if (!mathQuestions.contains(removedQuestion)) {
            throw new NotFoundQuestionException("Вопрос не найден!");
        }
        mathQuestions.remove(removedQuestion);
        return removedQuestion;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(mathQuestions);
    }
}
