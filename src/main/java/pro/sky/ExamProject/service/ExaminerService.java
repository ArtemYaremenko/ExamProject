package pro.sky.ExamProject.service;

import pro.sky.ExamProject.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getJavaQuestions(int amount);

    Collection<Question> getMathQuestions(int amount);
}
