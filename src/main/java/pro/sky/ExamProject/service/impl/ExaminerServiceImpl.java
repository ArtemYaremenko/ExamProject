package pro.sky.ExamProject.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.ExamProject.exception.BadQuestionAmountException;
import pro.sky.ExamProject.exception.IllegalQuestionAmountException;
import pro.sky.ExamProject.model.Question;
import pro.sky.ExamProject.service.ExaminerService;
import pro.sky.ExamProject.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    @Qualifier("java")
    private QuestionService javaQuestionService;

    @Qualifier("math")
    private QuestionService mathQuestionService;


    public ExaminerServiceImpl(QuestionService javaQuestionService, QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getJavaQuestions(int amount) {
        if (amount < 0) {
            throw new BadQuestionAmountException("Число должно быть больше нуля!");
        }
        if (amount > javaQuestionService.getAll().size()) {
            throw new IllegalQuestionAmountException("Такого количества вопросов нет в списке!");
        }
        Set<Question> javaQuestionsSet = new HashSet<>();
        while (javaQuestionsSet.size() < amount) {
            javaQuestionsSet.add(javaQuestionService.getRandomQuestion());
        }
        return javaQuestionsSet;
    }

    @Override
    public Collection<Question> getMathQuestions(int amount) {
        if (amount < 0) {
            throw new BadQuestionAmountException("Число должно быть больше нуля!");
        }
        if (amount > mathQuestionService.getAll().size()) {
            throw new IllegalQuestionAmountException("Такого количества вопросов нет в списке!");
        }
        Set<Question> mathQuestionsSet = new HashSet<>();
        while (mathQuestionsSet.size() < amount) {
            mathQuestionsSet.add(mathQuestionService.getRandomQuestion());
        }
        return mathQuestionsSet;
    }
}
