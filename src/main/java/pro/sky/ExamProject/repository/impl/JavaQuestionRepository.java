package pro.sky.ExamProject.repository.impl;

import org.springframework.stereotype.Repository;
import pro.sky.ExamProject.exception.NotFoundQuestionException;
import pro.sky.ExamProject.model.Question;
import pro.sky.ExamProject.repository.QuestionRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {

    private Set<Question> javaQuestions;

    public JavaQuestionRepository(Set<Question> javaQuestions) {
        this.javaQuestions = javaQuestions;
    }


    @Override
    public Question add(Question newQuestion) {
        javaQuestions.add(newQuestion);
        return newQuestion;
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
}
