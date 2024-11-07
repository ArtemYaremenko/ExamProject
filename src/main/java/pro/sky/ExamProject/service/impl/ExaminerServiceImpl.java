package pro.sky.ExamProject.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.ExamProject.exception.BadQuestionAmountException;
import pro.sky.ExamProject.exception.IllegalQuestionAmountException;
import pro.sky.ExamProject.model.Question;
import pro.sky.ExamProject.service.ExaminerService;
import pro.sky.ExamProject.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;


    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount < 0) {
            throw new BadQuestionAmountException("Число должно быть больше нуля!");
        }
        if (amount > questionService.getAll().size()) {
            throw new IllegalQuestionAmountException("Такого количества вопросов нет в списке!");
        }
        Set<Question> javaQuestionsSet = new HashSet<>();
        while (javaQuestionsSet.size() < amount) {
            javaQuestionsSet.add(questionService.getRandomQuestion());
        }
        return javaQuestionsSet;
    }
}
