package pro.sky.ExamProject.repository;

import pro.sky.ExamProject.model.Question;

import java.util.Collection;

public interface QuestionRepository {

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();
}
