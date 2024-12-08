package pro.sky.ExamProject.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.ExamProject.model.Question;
import pro.sky.ExamProject.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("exam/math")
public class MathQuestionController {

    @Qualifier("math")
    private final QuestionService questionService;

    public MathQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    Question addQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping("/remove")
    Question removeQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return questionService.remove(question, answer);
    }

    @GetMapping("/")
    Collection<Question> getQuestions() {
        return questionService.getAll();
    }
}
