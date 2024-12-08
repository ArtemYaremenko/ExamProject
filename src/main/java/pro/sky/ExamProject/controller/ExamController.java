package pro.sky.ExamProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.ExamProject.model.Question;
import pro.sky.ExamProject.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService service) {
        this.examinerService = service;
    }

    @GetMapping("/java/{amount}")
    public Collection<Question> getJavaQuestions(@PathVariable("amount") int amount) {
        return examinerService.getJavaQuestions(amount);
    }

    @GetMapping("/math/{amount}")
    public Collection<Question> getMathQuestions(@PathVariable("amount") int amount) {
        return examinerService.getMathQuestions(amount);
    }
}
